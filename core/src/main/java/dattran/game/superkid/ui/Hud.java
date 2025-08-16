package dattran.game.superkid.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Hud {
    private final Stage stage;
    private final HealthBar healthBar;
    private final float margin = 20;

    public Hud(float maxHealth) {
        stage = new Stage(new ScreenViewport());
        healthBar = new HealthBar(maxHealth);

        stage.addActor(healthBar);
        updateSizeAndPosition(); // thiết lập kích thước và vị trí ban đầu
    }

    public void updateHealth(float currentHealth) {
        healthBar.setHealth(currentHealth);
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
        healthBar.setSize(barWidth, barHeight);
        healthBar.setPosition(margin, viewportHeight - barHeight - margin);
    }

    public void dispose() {
        stage.dispose();
    }

    private static class HealthBar extends Actor {
        private final Texture backgroundTexture;
        private final Texture barTexture;
        private final Texture borderTexture;
        private float maxHealth;
        private float currentHealth;
        private float displayedHealth; // thanh hiện tại để animation

        private final float lerpSpeed = 3f; // tốc độ giảm máu mượt

        public HealthBar(float maxHealth) {
            this.maxHealth = maxHealth;
            this.currentHealth = maxHealth;
            this.displayedHealth = maxHealth;

            backgroundTexture = new Texture("white_pixel.png");
            barTexture = new Texture("white_pixel.png");
            borderTexture = new Texture("white_pixel.png");
        }

        public void setHealth(float health) {
            this.currentHealth = Math.max(0, Math.min(maxHealth, health));
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            // giảm mượt thanh hiện tại về currentHealth
            if(displayedHealth > currentHealth) {
                displayedHealth -= lerpSpeed * maxHealth * delta;
                if(displayedHealth < currentHealth) displayedHealth = currentHealth;
            }
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            float x = getX();
            float y = getY();
            float width = getWidth();
            float height = getHeight();

            // Shadow (bóng mờ)
            batch.setColor(0, 0, 0, 0.5f);
            batch.draw(backgroundTexture, x + 3, y - 3, width, height);

            // Nền health bar
            batch.setColor(0.2f, 0.2f, 0.2f, 1f);
            batch.draw(backgroundTexture, x, y, width, height);

            // Thanh đỏ health với animation
            batch.setColor(1, 0, 0, 1f);
            batch.draw(barTexture, x, y, width * (displayedHealth / maxHealth), height);

            // Viền (border)
            batch.setColor(1, 1, 1, 1f);
            float borderThickness = 2f;
            batch.draw(borderTexture, x - borderThickness, y - borderThickness, width + 2 * borderThickness, borderThickness);
            batch.draw(borderTexture, x - borderThickness, y + height, width + 2 * borderThickness, borderThickness);
            batch.draw(borderTexture, x - borderThickness, y, borderThickness, height);
            batch.draw(borderTexture, x + width, y, borderThickness, height);
        }
    }
}
