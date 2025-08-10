package dattran.game.superkid.listener;

import com.badlogic.gdx.physics.box2d.*;
import dattran.game.superkid.character.Character;

public class CharacterContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Character<?,?> character = getCharacter(contact);
        if (character != null && isCharacterGroundContact(contact)) {
            character.incrementGroundContacts();
        }
    }

    @Override
    public void endContact(Contact contact) {
        Character<?,?> character = getCharacter(contact);
        if (character != null && isCharacterGroundContact(contact)) {
            character.decrementGroundContacts();
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }


    private boolean isGround(Fixture fixture) {
        return "ground".equals(fixture.getUserData());
    }

    private boolean isCharacter(Fixture fixture) {
        return fixture.getBody() != null && fixture.getBody().getUserData() instanceof Character;
    }

    private boolean isCharacterGroundContact(Contact contact) {
        return (isCharacter(contact.getFixtureA()) && isGround(contact.getFixtureB())) ||
            (isCharacter(contact.getFixtureB()) && isGround(contact.getFixtureA()));
    }

    private Character<?,?> getCharacter(Contact contact) {
        if (isCharacter(contact.getFixtureA())) {
            return (Character<?,?>) contact.getFixtureA().getBody().getUserData();
        }
        if (isCharacter(contact.getFixtureB())) {
           return (Character<?,?>) contact.getFixtureB().getBody().getUserData();
        }
        return null;
    }
}
