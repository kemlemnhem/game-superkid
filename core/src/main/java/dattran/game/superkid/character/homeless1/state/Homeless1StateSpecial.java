package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.homeless1.Homeless1Character;

public class Homeless1StateSpecial extends Homeless1StateAttack {
    @Override
    public void enter(Homeless1Character character) {
        super.enter(character.getSpecialHitBoxManager());
    }

    @Override
    public void exit(Homeless1Character character) {
        super.exit(character.getSpecialHitBoxManager(), character);
    }
}
