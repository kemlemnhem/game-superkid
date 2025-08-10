package dattran.game.superkid.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class AbstractCharacter<
    S extends CharacterState<? super Character<S>>
    > implements Character<S>  {
    private S currentState;
    private TextureRegion frame;
    private boolean facingRight = true;
    private int groundContacts = 0;
    private World world;
    private Body body;


    @Override
    public boolean isFacingRight() {
        return facingRight;
    }


    @Override
    public void changeState(S state) {
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

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public Body defineBody(Vector2 position) {
        return null;
    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public void setFacingRight(boolean facingRight) {

    }

    @Override
    public boolean isOnGround() {
        return false;
    }

    @Override
    public void incrementGroundContacts() {

    }

    @Override
    public void decrementGroundContacts() {

    }
}
