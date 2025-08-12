package dattran.game.superkid.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;
import dattran.game.superkid.character.CharacterManager;

public interface GameScreen extends Screen {
    World getWorld();
    CharacterManager getCharacterManager();
}
