package dattran.game.superkid.character.kid;

import dattran.game.superkid.character.GameCharacter;
import dattran.game.superkid.character.kid.hitbox.KidHitBoxManager;
import dattran.game.superkid.character.kid.state.KidState;

public interface KidCharacter extends GameCharacter<KidState, KidCharacter> {
    KidHitBoxManager getKickHitBoxManager();
    KidHitBoxManager getPunchHitBoxManager();
    KidHitBoxManager getThumpHitBoxManager();
}
