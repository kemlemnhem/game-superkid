package dattran.game.superkid.character.kid;

import dattran.game.superkid.character.CharacterState;
import dattran.game.superkid.character.kid.input.KidInput;

public interface KidState extends CharacterState<KidCharacter, KidState> {

    void handleInput(KidCharacter kid, KidInput input);

}
