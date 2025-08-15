package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.config.GameConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KidStateHurt extends KidStateBattle {

    @Override
    public void handleInput(KidCharacter kid, KidInput input) {
        KidStateIdle.fullControl(kid, input);
    }
}
