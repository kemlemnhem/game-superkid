package dattran.game.superkid.character.base.state;

import dattran.game.superkid.character.base.type.GameCharacter;

public abstract class ActionState<
    C extends GameCharacter<S, C>,
    S extends State<C, S>>
    extends BaseState<C, S> {

    protected float actionDuration;

    public ActionState(float actionDuration) {
        this.actionDuration = actionDuration;
    }

    @Override
    public void enter(C character) {
        super.enter(character);
        stateTime = 0;
    }

    @Override
    public void update(C character, float delta) {
        super.update(character, delta);

        if (stateTime >= actionDuration) {
            onActionFinished(character);
        }
    }

    protected abstract void onActionFinished(C character);
}
