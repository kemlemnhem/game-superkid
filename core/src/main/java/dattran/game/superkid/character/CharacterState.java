package dattran.game.superkid.character;

public interface CharacterState<C extends GameCharacter<S, C>, S extends CharacterState<C, S>> {

    void enter(C character);

    void update(C character, float delta);

    void exit(C character);
}
