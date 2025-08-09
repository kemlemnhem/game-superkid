package dattran.game.superkid.character.kid;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class KidStateGeneral implements KidState {
    protected float stateTime;

    @Override
    public void update(KidCharacter kid, float delta) {
        stateTime += delta;
        Animation<TextureRegion> animation = kid.getAnimation(this);
        TextureRegion frame = animation.getKeyFrame(stateTime);

        if (!kid.isFacingRight() && !frame.isFlipX()) {
            frame.flip(true, false);
        } else if (kid.isFacingRight() && frame.isFlipX()) {
            frame.flip(true, false);
        }
        kid.setCurrentFrame(frame);
    }
}
