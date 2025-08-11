package dattran.game.superkid.character.kid.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.kid.KidCharacter;

public abstract class KidStateGeneral implements KidState {
    protected float stateTime;

    @Override
    public void update(KidCharacter kid, float delta) {
        stateTime += delta;
        Animation<TextureRegion> animation = kid.getAnimation(this);
        TextureRegion frame = animation.getKeyFrame(stateTime);

        if (!kid.getPhysik().isFacingRight() && !frame.isFlipX()) {
            frame.flip(true, false);
        } else if (kid.getPhysik().isFacingRight() && frame.isFlipX()) {
            frame.flip(true, false);
        }
        kid.getPhysik().setCurrentFrame(frame);
    }

}
