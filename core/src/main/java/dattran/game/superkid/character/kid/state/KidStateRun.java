package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.character.kid.input.KidInput;

public class KidStateRun extends KidStateBase {
    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        inMovingState(kid, input);
    }
}
