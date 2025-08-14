package dattran.game.superkid.character.homeless1.hitbox;


import com.badlogic.gdx.math.Vector2;
import dattran.game.superkid.character.base.hitbox.BaseHitBox;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.homeless1.Homeless1;
import dattran.game.superkid.character.kid.type.Kid;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.GameConfig;
import dattran.game.superkid.config.UserData;

public final class Homeless1HitBox extends BaseHitBox<Kid> {

    private Homeless1HitBox(GameCharacter<?, ?> character, String userData, Flag[] categoryFlags, Flag[] maskFlags, Vector2 offsetRight, Vector2 offsetLeft, float maxAllowDistanceBody, float maxAllowDistanceBox, int damage) {
        super(GameConfig.HOMELESS1_WIDTH/2, GameConfig.HOMELESS1_HEIGHT/2, UserData.KID, character, userData, categoryFlags, maskFlags, offsetRight, offsetLeft, maxAllowDistanceBody, maxAllowDistanceBox, damage);
    }

    public static Homeless1HitBox createAttack1(Homeless1 homeless1) {
        return new Homeless1HitBox(homeless1, UserData.HOMELESS_1_ATTACK_1, new Flag[]{Flag.ENEMY_ATTACK}, new Flag[]{Flag.KID}, new Vector2(0.1f,0), new Vector2(-0.1f, 0), GameConfig.HOMELESS_1_ATTACK_RANGE, 0.13f, 3);
    }

    public static Homeless1HitBox createAttack2(Homeless1 homeless1) {
        return new Homeless1HitBox(homeless1, UserData.HOMELESS_1_ATTACK_1, new Flag[]{Flag.ENEMY_ATTACK}, new Flag[]{Flag.KID}, new Vector2(0.1f,0), new Vector2(-0.1f, 0), GameConfig.HOMELESS_1_ATTACK_RANGE, 0.13f, 1);
    }

    public static Homeless1HitBox createSpecial(Homeless1 homeless1) {
        return new Homeless1HitBox(homeless1, UserData.HOMELESS_1_ATTACK_1, new Flag[]{Flag.ENEMY_ATTACK}, new Flag[]{Flag.KID}, new Vector2(0.1f,0), new Vector2(-0.1f, 0), GameConfig.HOMELESS_1_ATTACK_RANGE, 0.13f, 5);
    }
}
