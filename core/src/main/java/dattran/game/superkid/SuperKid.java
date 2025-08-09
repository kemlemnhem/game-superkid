package dattran.game.superkid;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dattran.game.superkid.loader.graphic.KidAnimationLoader;
import dattran.game.superkid.screen.SeaportScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SuperKid extends Game {
    private Batch batch;
    @Override
    public void create() {
        batch = new SpriteBatch();
        KidAnimationLoader.instance.load();
        setScreen(new SeaportScreen(batch));
    }

    @Override
    public void dispose() {
        batch.dispose();
        KidAnimationLoader.instance.dispose();
    }

}
