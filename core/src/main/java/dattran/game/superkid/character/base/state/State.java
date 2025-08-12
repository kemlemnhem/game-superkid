package dattran.game.superkid.character.base.state;

import dattran.game.superkid.character.base.type.GameCharacter;

public interface State<C extends GameCharacter<S, C>, S extends State<C, S>> {

    void enter(C character);

    void update(C character, float delta);

    void exit(C character);
}
