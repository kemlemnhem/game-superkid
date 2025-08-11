package dattran.game.superkid.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dattran.game.superkid.character.Character;
import dattran.game.superkid.character.Enemy;
import dattran.game.superkid.character.kid.KidCharacter;
import dattran.game.superkid.config.GameConfig;

public class CharacterContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Character<?,?> character = getCharacter(contact);
        if (character != null && isCharacterGroundContact(contact)) {
            character.incrementGroundContacts();
        }

        if (isKidAttackHitsEnemy(contact)) {
           KidCharacter kid = getKid(contact);
           Enemy enemy = getEnemy(contact);
           if (kid != null && enemy != null) {
               if (kid.shouldKickHitEnemy(enemy)) {
                   kid.onKickHitEnemy(enemy);
               }
           }
        }
    }

    private boolean isKidAttackHitsEnemy(Contact contact) {
        String udA = (String) contact.getFixtureA().getUserData();
        String udB = (String) contact.getFixtureB().getUserData();
        return (isKidAttackHitBox(udA) && isEnemy(udB)) || (isKidAttackHitBox(udB) && isEnemy(udA));
    }

    private boolean isKidAttackHitBox(String userData) {
        return "kid_kick".equals(userData);
    }

    private boolean isEnemy(String userData) {
        return userData.startsWith("enemy_");
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
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (isCharacter(fixtureA) && isCharacter(fixtureB)) {
            contact.setEnabled(false);
        }
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

    private KidCharacter getKid(Contact contact) {
        Object userData = contact.getFixtureA().getBody().getUserData();
        if (userData instanceof KidCharacter) {
            return (KidCharacter) userData;
        } else {
            userData = contact.getFixtureB().getBody().getUserData();
            if (userData instanceof KidCharacter) {
                return (KidCharacter) userData;
            }
        }
        return null;
    }

    private Enemy getEnemy(Contact contact) {
        Object userData = contact.getFixtureA().getBody().getUserData();
        if (userData instanceof Enemy) {
            return (Enemy) userData;
        } else {
            userData = contact.getFixtureB().getBody().getUserData();
            if (userData instanceof Enemy) {
                return (Enemy) userData;
            }
        }
        return null;
    }

}
