package dattran.game.superkid.character.kid.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class KidInputKeyboard extends InputAdapter implements KidInput {
    private boolean moveLeftPressed;
    private boolean moveRightPressed;
    private boolean moveUpPressed;
    private boolean moveDownPressed;
    private boolean runKeyHeld;
    private boolean kickPressed;
    private boolean punchPressed;
    private boolean thumpPressed;
    private boolean shieldPressed;
    private boolean jumpHeld;

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                moveLeftPressed = true;
                break;
            case Input.Keys.RIGHT:
                moveRightPressed = true;
                break;
            case Input.Keys.UP:
                moveUpPressed = true;
                break;
            case Input.Keys.DOWN:
                moveDownPressed = true;
                break;
            case Input.Keys.CONTROL_LEFT:
                runKeyHeld = true;
                break;
            case Input.Keys.F:
                kickPressed = true;
                break;
            case Input.Keys.J:
                punchPressed = true;
                break;
            case Input.Keys.K:
                thumpPressed = true;
                break;
            case Input.Keys.D:
                shieldPressed = true;
                break;
            case Input.Keys.SPACE:
                jumpHeld = true;
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
            case Input.Keys.UP:
                moveUpPressed = false;
                break;
            case Input.Keys.DOWN:
                moveDownPressed = false;
                break;
            case Input.Keys.CONTROL_LEFT:
                runKeyHeld = false;
                break;
            case Input.Keys.F:
                kickPressed = false;
                break;
            case Input.Keys.J:
                punchPressed = false;
                break;
            case Input.Keys.K:
                thumpPressed = false;
                break;
            case Input.Keys.D:
                shieldPressed = false;
                break;
            case Input.Keys.SPACE:
                jumpHeld = false;
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

    @Override
    public boolean isMoveUpPressed() {
        return moveUpPressed;
    }

    @Override
    public boolean isMoveDownPressed() {
        return moveDownPressed;
    }

    @Override
    public boolean isRunKeyHeld() {
        return runKeyHeld;
    }

    @Override
    public boolean isKickPressed() {
        return kickPressed;
    }

    @Override
    public boolean isPunchPressed() {
        return punchPressed;
    }

    @Override
    public boolean isThumpPressed() {
        return thumpPressed;
    }

    @Override
    public boolean isShieldPressed() {
        return shieldPressed;
    }

    @Override
    public boolean isJumpHeld() {
        return jumpHeld;
    }
}
