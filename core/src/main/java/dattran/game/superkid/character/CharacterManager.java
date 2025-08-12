package dattran.game.superkid.character;

import com.badlogic.gdx.graphics.g2d.Batch;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.kid.type.KidCharacter;

import java.util.HashSet;
import java.util.Set;

public final class CharacterManager {
    private KidCharacter kid;
    private Set<GameCharacter<?,?>> enemies = new HashSet<>();

    public void addKid(KidCharacter kid) {
        this.kid = kid;
    }

    public void addEnemy(GameCharacter<?,?> enemy) {
        enemies.add(enemy);
    }

    public KidCharacter getKid() {
        return kid;
    }

    public void render(Batch batch) {
        update();
        for(GameCharacter<?,?> character : enemies) {
            character.render(batch);
        }
        if (kid != null) {
            kid.render(batch);
        }
    }

    private void update() {
        Set<GameCharacter<?,?>> enemiesToRemove = new HashSet<>();
        for (GameCharacter<?,?> character : enemies) {
            if (character.isDead()) {
                enemiesToRemove.add(character);
            }
        }
        for(GameCharacter<?,?> character : enemiesToRemove) {
            character.getPhysic().dispose();
            enemies.remove(character);
        }

        if (kid.isDead()) {
            kid.getPhysic().dispose();
            kid = null;
        }
    }
}
