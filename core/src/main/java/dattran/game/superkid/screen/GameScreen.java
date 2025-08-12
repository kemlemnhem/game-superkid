package dattran.game.superkid.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;

public interface GameScreen extends Screen {
    World getWorld();
    ScreenManager getScreenManager();
}
