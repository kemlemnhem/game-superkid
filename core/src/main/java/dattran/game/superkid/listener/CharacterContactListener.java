package dattran.game.superkid.listener;

import com.badlogic.gdx.physics.box2d.*;
import dattran.game.superkid.character.base.type.Enemy;
import dattran.game.superkid.character.kid.type.KidCharacter;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.config.UserData;

public class CharacterContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Physic physic = getPhysik(contact);
        if (physic != null && isPhysikGroundContact(contact)) {
            physic.incrementGroundContacts();
        }
    }

    @Override
    public void endContact(Contact contact) {
        Physic physic = getPhysik(contact);
        if (physic != null && isPhysikGroundContact(contact)) {
            physic.decrementGroundContacts();
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (isPhysik(fixtureA) && isPhysik(fixtureB)) {
            contact.setEnabled(false);
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }


    private boolean isGround(Fixture fixture) {
        return "ground".equals(fixture.getUserData());
    }

    private boolean isPhysik(Fixture fixture) {
        return fixture.getBody() != null && fixture.getBody().getUserData() instanceof Physic;
    }

    private boolean isPhysikGroundContact(Contact contact) {
        return (isPhysik(contact.getFixtureA()) && isGround(contact.getFixtureB())) ||
            (isPhysik(contact.getFixtureB()) && isGround(contact.getFixtureA()));
    }

    private Physic getPhysik(Contact contact) {
        if (isPhysik(contact.getFixtureA())) {
            return (Physic) contact.getFixtureA().getBody().getUserData();
        }
        if (isPhysik(contact.getFixtureB())) {
           return (Physic) contact.getFixtureB().getBody().getUserData();
        }
        return null;
    }

}
