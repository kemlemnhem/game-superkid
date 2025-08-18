package dattran.game.superkid.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dattran.game.superkid.config.GameConfig;

public class Hud {
    private final Stage stage;
    private final HUDActor hudActor;
    private final float margin = 20;
    private int currentHp;

    public Hud() {
        stage = new Stage(new ScreenViewport());
        hudActor = new HUDActor();
        stage.addActor(hudActor);
        updateSizeAndPosition();
    }

    public void draw() {
        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        updateSizeAndPosition();
    }

    private void updateSizeAndPosition() {
        float viewportWidth = stage.getViewport().getWorldWidth();
        float viewportHeight = stage.getViewport().getWorldHeight();

        float barWidth = viewportWidth * 0.25f;
        float barHeight = viewportHeight * 0.03f;
        hudActor.setSize(barWidth, barHeight);
        hudActor.setPosition(margin, viewportHeight - barHeight - margin);
    }

    public void dispose() {
        stage.dispose();
    }

    public void updateHealth(int hp) {
        this.currentHp = hp;
    }


    private class HUDActor extends Group {
        private final Image avatar;
        private final Texture redHeart;
        private final Texture silberHeart;
        private final Texture halbRedHeart;
        private final Texture halbSilberHeart;

        private HUDActor() {
            this.avatar = new Image(new Texture(Gdx.files.internal("graphic/kid/avatar/24x24.png")));
            this.redHeart = new Texture(Gdx.files.internal("graphic/other/Red_Heart_12x11.png"));
            this.silberHeart = new Texture(Gdx.files.internal("graphic/other/Silber_Heart_12x11.png"));

            this.halbRedHeart = new Texture(Gdx.files.internal("graphic/other/Halb_Red_Heart_6x11.png"));
            this.halbSilberHeart = new Texture(Gdx.files.internal("graphic/other/Halb_Silber_Heart_6x11.png"));

            avatar.setPosition(10,10);
            addActor(avatar);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);

            int heartWidth = 12;
            int heartHeight = 11;

            int startX = (int)(getX() + 24 + 20);
            int startY = (int)(getY() + 20);

            int hp = Hud.this.currentHp;
            int maxHp = GameConfig.KID_MAX_HP;
            int hearts = maxHp / 10;

            for (int i = 0; i < hearts; i++) {
                if (hp >= 10) {
                    batch.draw(redHeart, startX + i * (heartWidth + 2), startY, heartWidth, heartHeight);
                } else if (hp >= 5) {
                    batch.draw(halbRedHeart, startX + i * (heartWidth + 2), startY, heartWidth / 2f, heartHeight);
                    batch.draw(halbSilberHeart, startX + i * (heartWidth + 2) + heartWidth / 2f, startY, heartWidth / 2f, heartHeight);
                } else {
                    batch.draw(silberHeart, startX + i * (heartWidth + 2), startY, heartWidth, heartHeight);
                }
                hp -= 10;
            }
        }


        @Override
        public void clear() {
            super.clear();
            avatar.clear();
            redHeart.dispose();
            silberHeart.dispose();
            halbSilberHeart.dispose();
            halbRedHeart.dispose();
        }

    }

}
