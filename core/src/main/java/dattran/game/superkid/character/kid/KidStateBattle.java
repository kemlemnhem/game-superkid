package dattran.game.superkid.character.kid;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class KidStateBattle extends KidStateGeneral {

    @Override
    public void update(KidCharacter kid, float delta) {
        super.update(kid, delta);
        Animation<TextureRegion> animation = kid.getAnimation(this);
        if (animation.isAnimationFinished(stateTime)) {
            kid.changeState(new KidStateIdle());
        }
    }
}
