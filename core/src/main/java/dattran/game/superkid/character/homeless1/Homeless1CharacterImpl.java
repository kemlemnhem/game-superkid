package dattran.game.superkid.character.homeless1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dattran.game.superkid.character.Enemy;
import dattran.game.superkid.character.homeless1.state.Homeless1State;
import dattran.game.superkid.character.homeless1.state.Homeless1StateDead;
import dattran.game.superkid.character.homeless1.state.Homeless1StateHurt;
import dattran.game.superkid.character.homeless1.state.Homeless1StateIdle1;
import dattran.game.superkid.character.kid.KidCharacter;
import dattran.game.superkid.config.GameConfig;
import dattran.game.superkid.loader.graphic.homeless1.Homeless1AnimationLoader;

public class Homeless1CharacterImpl implements Homeless1Character, Enemy {
    private Homeless1State currentState;
    private TextureRegion frame;
    private boolean facingRight = true;
    private int groundContacts = 0;


    private final World world;
    private Body body;

    private int hp = 20;


    public Homeless1CharacterImpl(World world, Vector2 startPosition, Homeless1State startState) {
        this.world = world;
        this.body = defineBody(startPosition);
        this.changeState(startState);
    }

    @Override
    public boolean isFacingRight() {
        return facingRight;
    }

    @Override
    public Animation<TextureRegion> getAnimation(Homeless1State state) {
        if (state instanceof Homeless1StateIdle1) {
            return Homeless1AnimationLoader.instance.loadedResource().getIdle1();
        }
        if (state instanceof Homeless1StateHurt) {
            return Homeless1AnimationLoader.instance.loadedResource().getHurt();
        }
        if (state instanceof Homeless1StateDead) {
            return Homeless1AnimationLoader.instance.loadedResource().getDead();
        }

        return Homeless1AnimationLoader.instance.loadedResource().getIdle1();
    }

    @Override
    public void changeState(Homeless1State state) {
        if (currentState != null) {
            currentState.exit(this);
        }
        if (currentState != state) {
            currentState = state;
            currentState.enter(this);
        }
    }

    @Override
    public void setCurrentFrame(TextureRegion frame) {
        this.frame = frame;
    }

    @Override
    public void update(float delta) {
        currentState.update(this, delta);
    }

    @Override
    public void render(Batch batch) {
        if (!isDead()) {
            update(Gdx.graphics.getDeltaTime());

            float width = frame.getRegionWidth() / GameConfig.PPM;
            float height = frame.getRegionHeight() / GameConfig.PPM;

            float spriteX = body.getPosition().x - width / 2;
            float spriteY = body.getPosition().y - height / 2;

            batch.draw(frame, spriteX, spriteY, width, height);
        }
    }

    @Override
    public Body defineBody(Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);
        bodyDef.bullet = true;
        body = world.createBody(bodyDef);
        body.setUserData(this);

        PolygonShape bodyShape = new PolygonShape();
        float bodyWidth = GameConfig.HOMELESS1_WIDTH/2;
        float bodyHeight = GameConfig.HOMELESS1_HEIGHT /2;
        bodyShape.setAsBox(bodyWidth, bodyHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = GameConfig.ENEMY_BIT;
        fixtureDef.filter.maskBits = GameConfig.KID_ATTACK_BIT | GameConfig.GROUND_BIT;

        Fixture mainFixture = body.createFixture(fixtureDef);
        mainFixture.setUserData("enemy_homeless1");
        bodyShape.dispose();
        return body;
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    @Override
    public void gettingHurtByKid(KidCharacter kid) {
        if (getHp() <= 0) {
            die();
            return;
        }
        setHp(getHp() - kid.getKickPower());
        changeState(new Homeless1StateHurt());
    }

    private void die() {
        changeState(new Homeless1StateDead());
    }

    @Override
    public boolean isOnGround() {
        return groundContacts > 0;
    }

    @Override
    public void incrementGroundContacts() {
        groundContacts++;
    }

    @Override
    public void decrementGroundContacts() {
        if (groundContacts > 0) {
            groundContacts--;
        }
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public boolean isDead() {
        return getHp() == Integer.MIN_VALUE;
    }

    @Override
    public void dispose() {
        world.destroyBody(body);
    }

}
