package dattran.game.superkid.character.kid;

import dattran.game.superkid.character.GameCharacter;
import dattran.game.superkid.character.Enemy;
import dattran.game.superkid.character.kid.state.KidState;

import java.util.Set;

public interface KidCharacter extends GameCharacter<KidState, KidCharacter> {
    void addKickHitBox();
    void removeKickHitBox();
    void onKickHit();
    boolean shouldKickHitEnemy(Enemy enemy);
    void onKickHitEnemy(Enemy enemy);
    Set<Enemy> getEnemiesKickHit();
    int getKickPower();
}
