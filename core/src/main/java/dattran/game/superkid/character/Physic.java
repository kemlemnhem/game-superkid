package dattran.game.superkid.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.config.Flag;

public interface Physic {
    Body defineBody(Vector2 position, String userData, Flag[] categoryBits, Flag[] maskBits);

    void render(Batch batch);

    boolean isFacingRight();

    void setCurrentFrame(TextureRegion frame);

    Body getBody();

    void setFacingRight(boolean facingRight);

    boolean isOnGround();

    void incrementGroundContacts();

    void decrementGroundContacts();

    void dispose();

    GameCharacter<?,?> getCharacter();

    boolean isReadyToRemove();

    void markForRemoval();

    void moveForward(float speed);

    void moveBackward(float speed);
}
