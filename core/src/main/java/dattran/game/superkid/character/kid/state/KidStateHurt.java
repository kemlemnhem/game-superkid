package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.character.kid.type.KidCharacter;

public class KidStateHurt extends KidStateBattle {

    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        inNoActionState(kid, input);
    }
}
