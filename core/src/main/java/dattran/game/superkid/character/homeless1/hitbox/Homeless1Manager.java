package dattran.game.superkid.character.homeless1.hitbox;


import com.badlogic.gdx.math.Vector2;
import dattran.game.superkid.character.base.hitbox.BaseManager;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.homeless1.Homeless1;
import dattran.game.superkid.character.kid.type.Kid;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.GameConfig;
import dattran.game.superkid.config.UserData;

public final class Homeless1Manager extends BaseManager<Kid> {

    private Homeless1Manager(GameCharacter<?, ?> character, String userData, Flag[] categoryFlags, Flag[] maskFlags, Vector2 offsetRight, Vector2 offsetLeft, float maxAllowDistanceBody, float maxAllowDistanceBox, int damage) {
        super(GameConfig.HOMELESS1_WIDTH/2, GameConfig.HOMELESS1_HEIGHT/2, UserData.KID, character, userData, categoryFlags, maskFlags, offsetRight, offsetLeft, maxAllowDistanceBody, maxAllowDistanceBox, damage);
    }

    public static Homeless1Manager createAttack1(Homeless1 homeless1) {
        return new Homeless1Manager(homeless1, UserData.HOMELESS_1_ATTACK_1, new Flag[]{Flag.ENEMY_ATTACK}, new Flag[]{Flag.KID}, new Vector2(0.1f,0), new Vector2(-0.1f, 0), 0.15f, 0.13f, 3);
    }
}
