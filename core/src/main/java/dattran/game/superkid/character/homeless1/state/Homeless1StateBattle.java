package dattran.game.superkid.character.homeless1.state;

import dattran.game.superkid.character.base.type.Player;
import dattran.game.superkid.character.homeless1.Homeless1Character;

public abstract class Homeless1StateBattle extends HomeLess1StateBase {
    private final Player target;
    protected Homeless1StateBattle(Player target) {
        this.target = target;
    }
    @Override
    public void update(Homeless1Character homeless1, float delta) {
        super.update(homeless1, delta);
        if (delta >= 0.5f /**attacj duration*/) {
            if (target != null) {
                homeless1.changeState(new Homeless1ChasePlayerState(target));
            }
            else {
                homeless1.changeState(new Homeless1StateIdle1());
            }
        }
    }
}
