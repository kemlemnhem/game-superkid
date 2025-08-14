package dattran.game.superkid.character.kid.type;

import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.kid.hitbox.KidHitBox;
import dattran.game.superkid.character.kid.state.KidState;

public interface KidCharacter extends GameCharacter<KidState, KidCharacter> {
    KidHitBox getKickHitBoxManager();
    KidHitBox getPunchHitBoxManager();
    KidHitBox getThumpHitBoxManager();
}
