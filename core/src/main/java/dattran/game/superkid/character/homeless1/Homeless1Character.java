package dattran.game.superkid.character.homeless1;

import dattran.game.superkid.character.base.hitbox.HitBox;
import dattran.game.superkid.character.base.type.GameCharacter;
import dattran.game.superkid.character.base.type.HitableCharacter;
import dattran.game.superkid.character.homeless1.hitbox.Homeless1HitBox;
import dattran.game.superkid.character.homeless1.state.Homeless1State;

public interface Homeless1Character extends GameCharacter<Homeless1State, Homeless1Character> {
    Homeless1HitBox getAttack1HitBoxManager();

    Homeless1HitBox getAttack2HitBoxManager();

    Homeless1HitBox getSpecialHitBoxManager();

    Homeless1HitBox getCurrentAttackHitBox();
}
