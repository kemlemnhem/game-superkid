package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;

public class Homeless1StateAttack1 extends Homeless1StateAttack {
    @Override
    public void enter(Homeless1Character character) {
        super.enter(character.getAttack1HitBoxManager());
    }

    @Override
    public void exit(Homeless1Character character) {
        super.exit(character.getAttack1HitBoxManager(), character);
    }
}
