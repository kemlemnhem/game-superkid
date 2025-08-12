package dattran.game.superkid.character.base.type;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.character.base.state.State;
import dattran.game.superkid.config.GameConfig;
import dattran.game.superkid.screen.GameScreen;

public interface GameCharacter<S extends State<C, S>, C extends GameCharacter<S, C>> {
    Animation<TextureRegion> getAnimation(S state);

    void changeState(S state);

    void update(float delta);

    int getHp();

    void setHp(int hp);

    boolean isDead();

    Physic getPhysic();

    void render(Batch batch);

    GameScreen getGameScreen();

    default boolean hitWall() {
        if (getPhysic() != null && getPhysic().getBody() != null) {
            float x = getPhysic().getBody().getPosition().x;
            return (x <= GameConfig.KID_WIDTH/2 + 0.02f || x >= getGameScreen().getScreenManager().getMapWidth() - GameConfig.KID_WIDTH/2 -0.02f);
        }
        return false;
    }
}
