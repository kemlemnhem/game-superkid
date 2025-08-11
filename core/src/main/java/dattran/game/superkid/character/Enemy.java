package dattran.game.superkid.character;

import dattran.game.superkid.character.kid.KidCharacter;

public interface Enemy {
    Physik getPhysik();
    void gettingHurtByKid(KidCharacter kid);

}
