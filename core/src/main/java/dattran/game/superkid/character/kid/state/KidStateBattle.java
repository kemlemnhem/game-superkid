package dattran.game.superkid.character.kid.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.kid.type.KidCharacter;

public abstract class KidStateBattle extends KidStateBase {

    @Override
    public void update(KidCharacter kid, float delta) {
        super.update(kid, delta);
        Animation<TextureRegion> animation = kid.getAnimation(this);
        if (animation.isAnimationFinished(stateTime)) {
            kid.changeState(new KidStateIdle());
        }
    }
}
