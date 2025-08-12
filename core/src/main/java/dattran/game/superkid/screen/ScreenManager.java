package dattran.game.superkid.screen;

import com.badlogic.gdx.graphics.g2d.Batch;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.kid.type.KidCharacter;

public interface ScreenManager {
    void addKid(KidCharacter kid);
    void addEnemy(GameCharacter<?,?> enemy);
    KidCharacter getKid();
    void render(Batch batch);
    float getMapWidth();
    float getMapHeight();
    void setMapWidth(float mapWidth);
    void setMapHeight(float mapHeight);
}
