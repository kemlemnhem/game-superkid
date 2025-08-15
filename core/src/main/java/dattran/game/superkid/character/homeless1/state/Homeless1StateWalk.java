package dattran.game.superkid.character.homeless1.state;

import com.badlogic.gdx.math.Vector2;
import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.config.GameConfig;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateWalk extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateWalk.class.getName());
    private float walkDuration;
    private final Random random = new SecureRandom();
    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Entering WalkState");
        stateTime = 0;
        walkDuration = 2f + random.nextFloat() * 3f;
    }

    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);

        if (homeless1.getPhysic().isFacingRight()) {
            homeless1.getPhysic().moveForward(GameConfig.HOMELESS_1_WALK_SPEED);
        } else {
            homeless1.getPhysic().moveBackward(GameConfig.HOMELESS_1_WALK_SPEED);
        }

        if (attackKid(homeless1) || huntKid(homeless1)) return;

        if (stateTime >= walkDuration) {
            homeless1.changeState(random.nextBoolean() ? new Homeless1StateIdle1() : new Homeless1StateIdle2());
        }

    }

    @Override
    public void exit(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Exiting WalkState");
    }
}
