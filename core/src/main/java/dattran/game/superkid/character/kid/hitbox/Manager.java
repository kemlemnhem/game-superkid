package dattran.game.superkid.character.kid.hitbox;

import dattran.game.superkid.character.Enemy;

public interface Manager {
    void add();
    void remove();
    boolean shouldHit(Enemy enemy);
    void onHit();
    void enemyIsGettingHit(Enemy enemy);
    void enterState();
    void exitState();
    boolean isEnableHitBox();
}
