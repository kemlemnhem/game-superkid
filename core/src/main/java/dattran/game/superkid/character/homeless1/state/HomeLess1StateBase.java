package dattran.game.superkid.character.homeless1.state;

import com.badlogic.gdx.math.Vector2;
import dattran.game.superkid.character.base.state.BaseState;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.homeless1.Homeless1Character;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.config.GameConfig;

import java.security.SecureRandom;
import java.util.Random;

public abstract class HomeLess1StateBase extends BaseState<Homeless1Character, Homeless1State> implements Homeless1State {
    private final Random random = new SecureRandom();
    protected boolean attackKid(Homeless1Character homeless1) {
        if (homeless1.getCurrentState() instanceof Homeless1StateAttack) {
            return false;
        }
        if (homeless1.getAttackCoolDown() > 0) {
            System.out.println("Cool Down");
            return false;
        }
        KidCharacter kid = homeless1.getGameScreen().getScreenManager().getKid();
        if (kid != null && !kid.isDead()) {
            Vector2 kidPos = kid.getPhysic().getBody().getPosition();
            Vector2 homelessPos = homeless1.getPhysic().getBody().getPosition();
            if (homelessPos.dst(kidPos) < GameConfig.HOMELESS_1_ATTACK_RANGE && !kid.isDead()) {
                int number = random.nextInt(3);
                switch (number) {
                    case 0: homeless1.changeState(new Homeless1StateSpecial()); break;
                    case 1: homeless1.changeState(new Homeless1StateAttack1()); break;
                    case 2: homeless1.changeState(new Homeless1StateAttack2()); break;
                }
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    protected boolean huntKid(Homeless1Character homeless1) {
        KidCharacter kid = homeless1.getGameScreen().getScreenManager().getKid();
        if (kid != null && !kid.isDead() && isVisible(homeless1, kid, GameConfig.HOMELESS_1_VIEW_RANGE, GameConfig.HOMELESS_1_DETECT_RANGE)) {
            homeless1.changeState(new Homeless1StateRun());
            return true;
        }
        return false;
    }

    protected boolean isKidVisible(Homeless1Character homeless1) {
        KidCharacter kid = homeless1.getGameScreen().getScreenManager().getKid();
        if (kid == null || kid.isDead()) {
            return false;
        }
        return(isVisible(homeless1, kid, GameConfig.HOMELESS_1_VIEW_RANGE, GameConfig.HOMELESS_1_DETECT_RANGE));
    }

    private static boolean isVisible(GameCharacter<?,?> ch1, GameCharacter<?,?> ch2, float visionRadius, float detectRadius) {
        float dst = ch1.getPhysic().getBody().getPosition().dst(ch2.getPhysic().getBody().getPosition());
        if (dst < detectRadius) {
            return true;
        }
        if (dst > visionRadius) {
            return false;
        }
        float dx = ch1.getPhysic().getBody().getPosition().x - ch2.getPhysic().getBody().getPosition().x;
        if (ch1.getPhysic().isFacingRight() && dx > 0) {
            return false;
        }
        if (!ch1.getPhysic().isFacingRight() && dx < 0) {
            return false;
        }
        return true;
    }
}
