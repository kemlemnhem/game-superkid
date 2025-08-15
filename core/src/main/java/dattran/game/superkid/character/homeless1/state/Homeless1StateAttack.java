package dattran.game.superkid.character.homeless1.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.config.GameConfig;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Homeless1StateAttack extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateAttack.class.getName());
    private boolean hitTriggered = false;
    private final Random random = new SecureRandom();

    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Starting Attack State");
        hitTriggered = false;
    }

    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);
        // avoid spamt hitbox
        if (!hitTriggered && stateTime >= 0.4f ) {
            homeless1.getCurrentAttackHitBox().enterState();
            hitTriggered = true;
        }

        KidCharacter kid = homeless1.getGameScreen().getScreenManager().getKid();

        Animation<TextureRegion> animation = homeless1.getAnimation(this);
        if (animation.isAnimationFinished(stateTime) || kid == null || kid.isDead()) {
            homeless1.changeState(random.nextBoolean() ? new Homeless1StateIdle1() : new Homeless1StateIdle2());
        }

    }

    @Override
    public void exit(Homeless1Character homeless1) {
        homeless1.setAttackCoolDown(GameConfig.HOMELESS_1_ATTACK_DELAY);
        LOGGER.log(Level.INFO, "Homeless1-Existing Attack State");

        homeless1.getCurrentAttackHitBox().exitState();
    }
}
