package dattran.game.superkid.character.kid.hitbox;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import dattran.game.superkid.character.Enemy;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.character.kid.KidCharacter;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.GameConfig;
import dattran.game.superkid.config.UserData;

import java.util.HashSet;
import java.util.Set;

public class KidHitBoxManager implements Manager {
    private Fixture hitbox;
    private final KidCharacter kid;
    private final String userData;
    private final Flag[] categoryFlags;
    private final Flag[] maskFlags;
    private final Vector2 offsetRight;
    private final Vector2 offsetLeft;
    private final float maxAllowDistanceBody;
    private final float maxAllowDistanceBox;
    private final Set<Enemy> enemiesHit = new HashSet<>();
    private final int damage;

    public static KidHitBoxManager createKick(KidCharacter kid) {
        return new KidHitBoxManager(kid, UserData.KID_KICK, new Flag[]{Flag.KID_ATTACK}, new Flag[]{Flag.ENEMY}, new Vector2(0.3f,0f), new Vector2(-0.3f,0f), 0.15f, 0.13f, 10);
    }

    public static KidHitBoxManager createPunch(KidCharacter kid) {
        return new KidHitBoxManager(kid, UserData.KID_PUNCH, new Flag[]{Flag.KID_ATTACK}, new Flag[]{Flag.ENEMY}, new Vector2(0.2f,0f), new Vector2(-0.2f,0f), 0.15f, 0.13f, 2);

    }

    public static KidHitBoxManager createThump(KidCharacter kid) {
        return new KidHitBoxManager(kid, UserData.KID_THUMP, new Flag[]{Flag.KID_ATTACK}, new Flag[]{Flag.ENEMY}, new Vector2(0.2f,0f), new Vector2(-0.2f,0f), 0.15f, 0.13f, 2);

    }

    private KidHitBoxManager(KidCharacter kid, String userData, Flag[] categoryFlags, Flag[] maskFlags, Vector2 offsetRight, Vector2 offsetLeft, float maxAllowDistanceBody, float maxAllowDistanceBox, int damage) {
        this.kid = kid;
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
        if (hitbox != null) {
            PolygonShape shape = new PolygonShape();
            float halfWidth = GameConfig.KID_KICK_WIDTH / 2;
            float halfHeight = GameConfig.KID_KICK_HEIGHT / 2;
            Vector2 offset = new Vector2(kid.getPhysic().isFacingRight() ? offsetRight : offsetLeft);
            shape.setAsBox(halfWidth, halfHeight, offset, 0);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.isSensor = true;
            fixtureDef.filter.categoryBits = Flag.combine(categoryFlags);
            fixtureDef.filter.maskBits = Flag.combine(maskFlags);

            hitbox = kid.getPhysic().getBody().createFixture(fixtureDef);
            hitbox.setUserData(userData);

            shape.dispose();
        }
    }

    @Override
    public void remove() {
        if (hitbox != null) {
            kid.getPhysic().getBody().destroyFixture(hitbox);
            hitbox = null;
        }
    }

    @Override
    public boolean shouldHit(Enemy enemy) {
        Vector2 hitBoxOffset = new Vector2(kid.getPhysic().isFacingRight() ? offsetRight : offsetLeft);
        Vector2 hitBoxCenter = kid.getPhysic().getBody().getPosition().cpy().add(hitBoxOffset);
        Vector2 enemyPos = enemy.getPhysic().getBody().getPosition();

        float boxDistance = hitBoxCenter.dst(enemyPos);
        float bodyDistance = kid.getPhysic().getBody().getPosition().dst(enemyPos);

        return bodyDistance < maxAllowDistanceBody || boxDistance < maxAllowDistanceBox;
    }

    @Override
    public void onHit() {
        Vector2 kickHitBoxOffset = new Vector2(kid.getPhysic().isFacingRight() ? offsetRight : offsetLeft);
        Vector2 kickHitBoxCenter = kid.getPhysic().getBody().getPosition().cpy().add(kickHitBoxOffset);
        float halfWidth = GameConfig.KID_KICK_WIDTH / 2;
        float halfHeight = GameConfig.KID_KICK_HEIGHT / 2;
        kid.getPhysic().getBody().getWorld().QueryAABB(fixture -> {
            if (fixture != hitbox && fixture.getUserData().toString().startsWith("enemy_")) {

                Enemy enemy = (Enemy) ((Physic) fixture.getBody().getUserData()).getCharacter();
                if (shouldHit(enemy)) {
                    enemyIsGettingHit(enemy);
                }
            }
            return true;
        },kickHitBoxCenter.x - halfWidth, kickHitBoxCenter.y - halfHeight,kickHitBoxCenter.x + halfWidth, kickHitBoxCenter.y + halfHeight );
    }

    @Override
    public void enemyIsGettingHit(Enemy enemy) {
        if (enemiesHit.contains(enemy)) {
            return;
        }
        enemiesHit.add(enemy);
        enemy.getPhysic().setFacingRight(!kid.getPhysic().isFacingRight());
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

    @Override
    public boolean isEnableHitBox() {
        return hitbox != null;
    }
}
