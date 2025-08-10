package dattran.game.superkid.character.homeless1;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import dattran.game.superkid.character.homeless1.state.Homeless1State;

public class Homeless1CharacterImpl implements Homeless1Character {
    @Override
    public boolean isFacingRight() {
        return false;
    }

    @Override
    public Animation<TextureRegion> getAnimation(Homeless1State state) {
        return null;
    }

    @Override
    public void changeState(Homeless1State state) {

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
