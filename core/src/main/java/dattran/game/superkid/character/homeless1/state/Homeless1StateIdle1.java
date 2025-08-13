package dattran.game.superkid.character.homeless1.state;

import com.badlogic.gdx.math.Vector2;
import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.character.kid.type.Kid;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.screen.GameScreen;

import java.security.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateIdle1 extends HomeLess1StateBase {
    private static final Logger LOGGER = Logger.getLogger(Homeless1StateIdle1.class.getName());
    private final Random random = new SecureRandom();
    private float idleDuration;

    @Override
    public void enter(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Entering Idle State");
        stateTime = 0;
        idleDuration = 1f + random.nextFloat() * 2f;
        if (homeless1.getPhysic().getBody() != null) {
            homeless1.getPhysic().getBody().setLinearVelocity(0, homeless1.getPhysic().getBody().getLinearVelocity().y);
        }
    }

    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);

        KidCharacter kid = homeless1.getGameScreen().getScreenManager().getKid();
        if (kid != null && !kid.isDead()) {
            Vector2 kidPos = kid.getPhysic().getBody().getPosition();
            Vector2 homelessPos = homeless1.getPhysic().getBody().getPosition();
            if (homelessPos.dst(kidPos) < 0.3f && !kid.isDead()) {
                homeless1.changeState(new Homeless1StateAttack1());
                return;
            }
        }

        if (stateTime > idleDuration) {
            homeless1.getPhysic().setFacingRight(random.nextBoolean());
            homeless1.changeState(new Homeless1StateWalk());
        }

    }

    @Override
    public void exit(Homeless1Character homeless1) {
        LOGGER.log(Level.INFO, "Homeless1-Exiting Idle State");
    }
}
