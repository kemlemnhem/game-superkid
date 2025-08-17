package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.base.state.State;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.character.kid.input.KidInput;

public interface KidState extends State<KidCharacter, KidState> {
    void handleInput(KidCharacter kid, KidInput input);

}
