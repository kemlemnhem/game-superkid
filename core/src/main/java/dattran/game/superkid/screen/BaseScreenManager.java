package dattran.game.superkid.screen;

import com.badlogic.gdx.graphics.g2d.Batch;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.kid.type.KidCharacter;

import java.util.HashSet;
import java.util.Set;

public class BaseScreenManager implements ScreenManager {
    private KidCharacter kid;
    private Set<GameCharacter<?,?>> enemies = new HashSet<>();
    private float mapWidth, mapHeight;
    @Override
    public void addKid(KidCharacter kid) {
        this.kid = kid;
    }

    @Override
    public void addEnemy(GameCharacter<?, ?> enemy) {
        enemies.add(enemy);
    }

    @Override
    public KidCharacter getKid() {
        return kid;
    }

    @Override
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
            if (character.getPhysic().isReadyToRemove()) {
                enemiesToRemove.add(character);
            }
        }
        for(GameCharacter<?,?> character : enemiesToRemove) {
            character.getPhysic().dispose();
            enemies.remove(character);
        }

        if (kid.getPhysic().isReadyToRemove()) {
            kid.getPhysic().dispose();
            kid = null;
        }
    }

    @Override
    public float getMapWidth() {
        return mapWidth;
    }

    @Override
    public float getMapHeight() {
        return mapHeight;
    }

    @Override
    public void setMapWidth(float mapWidth) {
        this.mapWidth = mapWidth;
    }

    @Override
    public void setMapHeight(float mapHeight) {
        this.mapHeight = mapHeight;
    }
}
