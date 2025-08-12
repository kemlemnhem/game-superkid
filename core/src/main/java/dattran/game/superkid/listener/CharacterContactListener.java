package dattran.game.superkid.listener;

import com.badlogic.gdx.physics.box2d.*;
import dattran.game.superkid.character.Enemy;
import dattran.game.superkid.character.kid.KidCharacter;
import dattran.game.superkid.character.Physic;
import dattran.game.superkid.config.UserData;

public class CharacterContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Physic physic = getPhysik(contact);
        if (physic != null && isPhysikGroundContact(contact)) {
            physic.incrementGroundContacts();
        }

      /*  if (isKidAttackHitsEnemy(contact)) {
           KidCharacter kid = getKid(contact);
           Enemy enemy = getEnemy(contact);
           if (kid != null && enemy != null) {
               if (kid.getPunchHitBoxManager().isEnableHitBox() && kid.getPunchHitBoxManager().shouldHit(enemy)) {
                   kid.getPunchHitBoxManager().enemyIsGettingHit(enemy);
               }
               else if(kid.getThumpHitBoxManager().isEnableHitBox() && kid.getThumpHitBoxManager().shouldHit(enemy)) {
                   kid.getThumpHitBoxManager().enemyIsGettingHit(enemy);
               }
               else if(kid.getKickHitBoxManager().isEnableHitBox() && kid.getKickHitBoxManager().shouldHit(enemy)) {
                   kid.getKickHitBoxManager().enemyIsGettingHit(enemy);
               }
           }
        }*/
    }

    private boolean isKidAttackHitsEnemy(Contact contact) {
        String udA = (String) contact.getFixtureA().getUserData();
        String udB = (String) contact.getFixtureB().getUserData();
        return (isKidAttackHitBox(udA) && isEnemy(udB)) || (isKidAttackHitBox(udB) && isEnemy(udA));
    }

    private boolean isKidAttackHitBox(String userData) {
        return UserData.KID_KICK.equals(userData) || UserData.KID_PUNCH.equals(userData) || UserData.KID_THUMP.equals(userData);
    }

    private boolean isEnemy(String userData) {
        return userData.startsWith("enemy_");
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
