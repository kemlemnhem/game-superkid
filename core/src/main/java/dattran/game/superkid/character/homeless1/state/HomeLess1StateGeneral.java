package dattran.game.superkid.character.homeless1.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.character.kid.KidCharacter;
import dattran.game.superkid.character.kid.state.KidState;

public abstract class HomeLess1StateGeneral implements Homeless1State {
    protected float stateTime;

    @Override
    public void update(Homeless1Character homeless1, float delta) {
        stateTime += delta;
        Animation<TextureRegion> animation = homeless1.getAnimation(this);
        TextureRegion frame = animation.getKeyFrame(stateTime);

        if (!homeless1.getPhysik().isFacingRight() && !frame.isFlipX()) {
            frame.flip(true, false);
        } else if (homeless1.getPhysik().isFacingRight() && frame.isFlipX()) {
            frame.flip(true, false);
        }
        homeless1.getPhysik().setCurrentFrame(frame);
    }

}
