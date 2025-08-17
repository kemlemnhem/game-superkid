package dattran.game.superkid.character.base.state;

import dattran.game.superkid.character.base.type.GameCharacter;

public abstract class MoveState<
    C extends GameCharacter<S, C>,
    S extends State<C, S>>
    extends BaseState<C, S> {

    @Override
    public void enter(C character) {
        super.enter(character);
    }

    @Override
    public void update(C character, float delta) {
        super.update(character, delta);
    }

    @Override
    public void exit(C character) {
        super.exit(character);
    }
}
