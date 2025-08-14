package dattran.game.superkid.character.kid.hitbox;


import com.badlogic.gdx.math.Vector2;
import dattran.game.superkid.character.base.type.Enemy;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.base.hitbox.BaseHitBox;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.config.Flag;
import dattran.game.superkid.config.GameConfig;
import dattran.game.superkid.config.UserData;

public final class KidHitBox extends BaseHitBox<Enemy> {

    public static KidHitBox createKick(KidCharacter kid) {
        return new KidHitBox(kid, UserData.KID_KICK, new Flag[]{Flag.KID_ATTACK}, new Flag[]{Flag.ENEMY}, new Vector2(0.3f,0f), new Vector2(-0.3f,0f), 0.15f, 0.13f, 10);
    }

    public static KidHitBox createPunch(KidCharacter kid) {
        return new KidHitBox(kid, UserData.KID_PUNCH, new Flag[]{Flag.KID_ATTACK}, new Flag[]{Flag.ENEMY}, new Vector2(0.2f,0f), new Vector2(-0.2f,0f), 0.15f, 0.13f, 2);

    }

    public static KidHitBox createThump(KidCharacter kid) {
        return new KidHitBox(kid, UserData.KID_THUMP, new Flag[]{Flag.KID_ATTACK}, new Flag[]{Flag.ENEMY}, new Vector2(0.2f,0f), new Vector2(-0.2f,0f), 0.15f, 0.13f, 2);

    }

    private KidHitBox(GameCharacter<?, ?> character, String userData, Flag[] categoryFlags, Flag[] maskFlags, Vector2 offsetRight, Vector2 offsetLeft, float maxAllowDistanceBody, float maxAllowDistanceBox, int damage) {
        super(GameConfig.KID_WIDTH/2, GameConfig.KID_HEIGHT/2, UserData.ENEMY, character, userData, categoryFlags, maskFlags, offsetRight, offsetLeft, maxAllowDistanceBody, maxAllowDistanceBox, damage);
    }
}
