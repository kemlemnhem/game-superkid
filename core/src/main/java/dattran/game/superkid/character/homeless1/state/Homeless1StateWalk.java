package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.config.GameConfig;

import java.security.SecureRandom;
import java.util.Random;

public class Homeless1StateWalk extends HomeLess1StateBase {
    private final Random random = new SecureRandom();
    private final float walkDuration = 2f + random.nextFloat() * 3;

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
}
