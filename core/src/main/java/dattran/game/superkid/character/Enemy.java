package dattran.game.superkid.character;

import dattran.game.superkid.character.kid.KidCharacter;

public interface Enemy {
    Physic getPhysic();
    void gettingHurt(int damage);

}
