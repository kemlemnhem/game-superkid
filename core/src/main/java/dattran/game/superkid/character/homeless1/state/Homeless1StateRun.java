package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.config.GameConfig;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Homeless1StateRun extends HomeLess1StateBase {
    private final Random random = new SecureRandom();
    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);
        KidCharacter kid = homeless1.getGameScreen().getScreenManager().getKid();
        if (kid == null || kid.isDead()) {
            homeless1.changeState(random.nextBoolean() ? new Homeless1StateIdle1() : new Homeless1StateIdle2());
            return;
        }
        float dist = homeless1.getPhysic().getBody().getPosition().dst(kid.getPhysic().getBody().getPosition());
        if (dist <= GameConfig.HOMELESS_1_ATTACK_RANGE && homeless1.getAttackCoolDown() <= 0) {
            attackKid(homeless1);
            return;
        }
        if (dist <= GameConfig.HOMELESS_1_ATTACK_RANGE) {
            return;
        }

        homeless1.getPhysic().setFacingRight(homeless1.getPhysic().getBody().getPosition().x < homeless1.getGameScreen().getScreenManager().getKid().getPhysic().getBody().getPosition().x);
        if (homeless1.getPhysic().isFacingRight()) {
            homeless1.getPhysic().moveForward(GameConfig.HOMELESS_1_WALK_SPEED);
        }
        else {
            homeless1.getPhysic().moveBackward(GameConfig.HOMELESS_1_WALK_SPEED);
        }
    }
}
