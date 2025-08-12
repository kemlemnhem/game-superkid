package dattran.game.superkid.character.base.hitbox;

import dattran.game.superkid.character.base.type.HitableCharacter;

public interface Manager<H extends HitableCharacter> {
    void add();
    void remove();
    boolean shouldHit(H enemy);
    void onHit();
    void enemyIsGettingHit(H enemy);
    void enterState();
    void exitState();
}
