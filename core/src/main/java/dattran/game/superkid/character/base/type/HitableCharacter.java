package dattran.game.superkid.character.base.type;

import dattran.game.superkid.character.Physic;

public interface HitableCharacter {
    void gettingHurt(int damage);
    Physic getPhysic();
}
