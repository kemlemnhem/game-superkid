package dattran.game.superkid.character;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public interface Character<S extends CharacterState> {
    boolean isFacingRight();

    Animation<TextureRegion> getAnimation(S state);

    void changeState(S state);

    void setCurrentFrame(TextureRegion frame);

    void update(float delta);

    void render(Batch batch);

    Body defineBody(Vector2 position);

    Body getBody();

    void setFacingRight(boolean facingRight);
}
