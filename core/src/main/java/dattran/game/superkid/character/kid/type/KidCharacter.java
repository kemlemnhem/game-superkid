package dattran.game.superkid.character.kid.type;

import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.kid.hitbox.KidManager;
import dattran.game.superkid.character.kid.state.KidState;

public interface KidCharacter extends GameCharacter<KidState, KidCharacter> {
    KidManager getKickHitBoxManager();
    KidManager getPunchHitBoxManager();
    KidManager getThumpHitBoxManager();
}
