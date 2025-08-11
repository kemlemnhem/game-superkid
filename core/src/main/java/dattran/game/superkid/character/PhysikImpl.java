package dattran.game.superkid.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.GameConfig;

public class PhysikImpl implements Physik {
    private final GameCharacter<?,?> character;
    private TextureRegion currentFrame;
    private boolean facingRight = true;
    private int groundContacts = 0;
    private final World world;
    private Body body;

    private PhysikImpl(GameCharacter<?,?> character, World world, Vector2 startPosition, String userData, Flag[] categoryBits, Flag[] maskBits) {
        this.character = character;
        this.world = world;
        this.body = defineBody(startPosition, userData, categoryBits, maskBits);
    }

    @Override
    public Body defineBody(Vector2 position, String userData, Flag[] categoryBits, Flag[] maskBits) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position.x, position.y);
        bodyDef.bullet = true;
        body = world.createBody(bodyDef);
        body.setUserData(this);

        PolygonShape bodyShape = new PolygonShape();
        float bodyWidth = GameConfig.KID_WIDTH/2;
        float bodyHeight = GameConfig.KID_HEIGHT /2;
        bodyShape.setAsBox(bodyWidth, bodyHeight);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.2f;
        fixtureDef.filter.categoryBits = Flag.combine(categoryBits);
        fixtureDef.filter.maskBits = Flag.combine(maskBits);

        Fixture mainFixture = body.createFixture(fixtureDef);
        mainFixture.setUserData(userData);
        bodyShape.dispose();
        return body;
    }

    @Override
    public void render(Batch batch) {
        if (currentFrame != null) {
            float width = currentFrame.getRegionWidth() / GameConfig.PPM;
            float height = currentFrame.getRegionHeight() / GameConfig.PPM;
            float spriteX = body.getPosition().x - width / 2;
            float spriteY = body.getPosition().y - height / 2;
            batch.draw(currentFrame, spriteX, spriteY, width, height);
        }
    }

    @Override
    public boolean isFacingRight() {
        return facingRight;
    }

    @Override
    public void setCurrentFrame(TextureRegion frame) {
        this.currentFrame = frame;
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
    public void dispose() {
        world.destroyBody(body);
    }

    @Override
    public GameCharacter<?, ?> getCharacter() {
        return character;
    }

    public static final class PhysikImplBuilder {
        private GameCharacter character;
        private World world;
        private Vector2 startPosition;
        private String userData;
        private Flag[] categoryBits;
        private Flag[] maskBits;

        private PhysikImplBuilder() {
        }

        public static PhysikImplBuilder aPhysikImpl() {
            return new PhysikImplBuilder();
        }

        public PhysikImplBuilder setCharacter(GameCharacter character) {
            this.character = character;
            return this;
        }

        public PhysikImplBuilder setWorld(World world) {
            this.world = world;
            return this;
        }

        public PhysikImplBuilder setStartPosition(Vector2 startPosition) {
            this.startPosition = startPosition;
            return this;
        }

        public PhysikImplBuilder setUserData(String userData) {
            this.userData = userData;
            return this;
        }

        public PhysikImplBuilder setCategoryBits(Flag ... categoryBits) {
            this.categoryBits = categoryBits;
            return this;
        }

        public PhysikImplBuilder setMaskBits(Flag ... maskBits) {
            this.maskBits = maskBits;
            return this;
        }

        public PhysikImpl build() {
            return new PhysikImpl(character, world, startPosition, userData, categoryBits, maskBits);
        }
    }
}
