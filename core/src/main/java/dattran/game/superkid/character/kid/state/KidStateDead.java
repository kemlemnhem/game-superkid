package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.base.state.BaseDeadState;
import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.character.kid.type.KidCharacter;

public class KidStateDead extends BaseDeadState<KidCharacter,KidState> implements KidState {
    @Override
    public void handleInput(KidCharacter kid, KidInput input) {

    }
}
