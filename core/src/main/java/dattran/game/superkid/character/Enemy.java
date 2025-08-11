package dattran.game.superkid.character;

import com.badlogic.gdx.physics.box2d.Body;
import dattran.game.superkid.character.kid.KidCharacter;

public interface Enemy {
    Body getBody();
    void setFacingRight(boolean facingRight);
    void gettingHurtByKid(KidCharacter kid);
}
