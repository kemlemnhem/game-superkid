package dattran.game.superkid.character.kid.state;

import dattran.game.superkid.character.base.state.BaseState;
import dattran.game.superkid.character.kid.input.KidInput;
import dattran.game.superkid.character.kid.type.KidCharacter;

public abstract class KidStateBase extends BaseState<KidCharacter, KidState> implements KidState {
    protected void inNoActionState(KidCharacter kid, KidInput input) {
        if (input.isMoveDownPressed()) {
            kid.changeState(new KidStateWalk());
        }
        else if (input.isMoveUpPressed()) {
            kid.changeState(new KidStateWalk());
        }
        else if (input.isMoveLeftPressed()) {
            kid.getPhysic().setFacingRight(false);
            kid.changeState(new KidStateWalk());
        } else if (input.isMoveRightPressed()) {
            kid.getPhysic().setFacingRight(true);
            kid.changeState(new KidStateWalk());
        } else if(input.isKickPressed()) {
            kid.changeState(new KidStateKick());
        }
        else if (input.isPunchPressed()) {
            kid.changeState(new KidStatePunch());
        }
        else if (input.isThumpPressed()) {
            kid.changeState(new KidStateThump());
        } else if (input.isShieldPressed()) {
            kid.changeState(new KidStateShield());
        }
        else if (input.isJumpHeld()) {
            kid.changeState(new KidStateJump());
        }
    }

    protected void inMovingState(KidCharacter kid, KidInput input) {
        if (input.isMoveUpPressed()) {
            kid.getPhysic().moveUp();
        } else if (input.isMoveDownPressed()) {
            kid.getPhysic().moveDown();
        } else {
            kid.getPhysic().stopY();
        }

        if (input.isMoveLeftPressed()) {
            kid.getPhysic().setFacingRight(false);
            kid.getPhysic().moveBackward();
        } else if (input.isMoveRightPressed()) {
            kid.getPhysic().setFacingRight(true);
            kid.getPhysic().moveForward();
        } else {
            kid.getPhysic().stopX();
        }

        if (!input.isMoveLeftPressed() && !input.isMoveRightPressed() &&
            !input.isMoveUpPressed() && !input.isMoveDownPressed()) {
            kid.changeState(new KidStateIdle());
        }
    }
}
