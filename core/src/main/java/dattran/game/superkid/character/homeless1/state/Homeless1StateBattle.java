package dattran.game.superkid.character.homeless1.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.base.type.Player;
import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.character.kid.state.KidStateIdle;
import dattran.game.superkid.character.kid.type.KidCharacter;

public abstract class Homeless1StateBattle extends HomeLess1StateBase {

    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);
        Animation<TextureRegion> animation = homeless1.getAnimation(this);
        KidCharacter kid = homeless1.getGameScreen().getScreenManager().getKid();
        if (animation.isAnimationFinished(stateTime) || kid.isDead()) {
            homeless1.changeState(new Homeless1StateIdle1());
        }
    }
}
