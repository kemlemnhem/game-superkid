package dattran.game.superkid.character.kid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class KidInputKeyboard extends InputAdapter implements KidInput {
    private boolean moveLeftPressed;
    private boolean moveRightPressed;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                moveLeftPressed = true;
                break;
            case Input.Keys.RIGHT:
                moveRightPressed = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                moveLeftPressed = false;
                break;
            case Input.Keys.RIGHT:
                moveRightPressed = false;
                break;
        }
        return true;
    }

    public KidInputKeyboard() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean isMoveLeftPressed() {
        return moveLeftPressed;
    }

    @Override
    public boolean isMoveRightPressed() {
        return moveRightPressed;
    }
}
