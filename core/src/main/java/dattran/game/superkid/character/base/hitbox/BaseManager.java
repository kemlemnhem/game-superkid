package dattran.game.superkid.character.base.hitbox;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import dattran.game.superkid.character.base.type.Enemy;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.character.base.type.HitableCharacter;
import dattran.game.superkid.config.Flag;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseManager<H extends HitableCharacter> implements Manager<H> {
    private Fixture hitbox;
    private final float width;
    private final float height;
    private final String enemyUserDataPrefix;
    private final GameCharacter<?,?> character;
    private final String userData;
    private final Flag[] categoryFlags;
    private final Flag[] maskFlags;
    private final Vector2 offsetRight;
    private final Vector2 offsetLeft;
    private final float maxAllowDistanceBody;
    private final float maxAllowDistanceBox;
    private final Set<H> enemiesHit = new HashSet<>();
    private final int damage;


    protected BaseManager(float width, float height, String enemyUserDataPrefix, GameCharacter<?,?> character, String userData, Flag[] categoryFlags, Flag[] maskFlags, Vector2 offsetRight, Vector2 offsetLeft, float maxAllowDistanceBody, float maxAllowDistanceBox, int damage) {
        this.width = width;
        this.height = height;
        this.enemyUserDataPrefix = enemyUserDataPrefix;
        this.character = character;
        this.userData = userData;
        this.categoryFlags = categoryFlags;
        this.maskFlags = maskFlags;
        this.offsetRight = offsetRight;
        this.offsetLeft = offsetLeft;
        this.maxAllowDistanceBody = maxAllowDistanceBody;
        this.maxAllowDistanceBox = maxAllowDistanceBox;
        this.damage = damage;
    }

    @Override
    public void add() {
        if (hitbox == null) {
            PolygonShape shape = new PolygonShape();

            Vector2 offset = new Vector2(character.getPhysic().isFacingRight() ? offsetRight : offsetLeft);
            shape.setAsBox(width, height, offset, 0);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.isSensor = true;
            fixtureDef.filter.categoryBits = Flag.combine(categoryFlags);
            fixtureDef.filter.maskBits = Flag.combine(maskFlags);

            hitbox = character.getPhysic().getBody().createFixture(fixtureDef);
            hitbox.setUserData(userData);

            shape.dispose();
        }
    }

    @Override
    public void remove() {
        if (hitbox != null) {
            character.getPhysic().getBody().destroyFixture(hitbox);
            hitbox = null;
        }
    }

    @Override
    public boolean shouldHit(H enemy) {
        Vector2 hitBoxOffset = new Vector2(character.getPhysic().isFacingRight() ? offsetRight : offsetLeft);
        Vector2 hitBoxCenter = character.getPhysic().getBody().getPosition().cpy().add(hitBoxOffset);
        Vector2 enemyPos = enemy.getPhysic().getBody().getPosition();

        float boxDistance = hitBoxCenter.dst(enemyPos);
        float bodyDistance = character.getPhysic().getBody().getPosition().dst(enemyPos);

        return bodyDistance < maxAllowDistanceBody || boxDistance < maxAllowDistanceBox;
    }

    @Override
    public void onHit() {
        Vector2 kickHitBoxOffset = new Vector2(character.getPhysic().isFacingRight() ? offsetRight : offsetLeft);
        Vector2 kickHitBoxCenter = character.getPhysic().getBody().getPosition().cpy().add(kickHitBoxOffset);
        character.getPhysic().getBody().getWorld().QueryAABB(fixture -> {
            if (fixture != hitbox && fixture.getUserData().toString().startsWith(enemyUserDataPrefix)) {
                @SuppressWarnings("unchecked")
                H enemy = (H) ((Physic) fixture.getBody().getUserData()).getCharacter();
                if (shouldHit(enemy)) {
                    enemyIsGettingHit(enemy);
                }
            }

            return true;
        },kickHitBoxCenter.x - width, kickHitBoxCenter.y - height,kickHitBoxCenter.x + width, kickHitBoxCenter.y + height );
    }

    @Override
    public void enemyIsGettingHit(H enemy) {
        if (enemiesHit.contains(enemy)) {
            return;
        }
        enemiesHit.add(enemy);
        enemy.getPhysic().setFacingRight(!character.getPhysic().isFacingRight());
        enemy.gettingHurt(damage);
    }


    @Override
    public void enterState() {
        enemiesHit.clear();
        add();
        onHit();
    }

    @Override
    public void exitState() {
        remove();
    }

}
