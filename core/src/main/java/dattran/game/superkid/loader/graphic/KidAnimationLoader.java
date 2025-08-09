package dattran.game.superkid.loader.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.loader.LoadedResource;
import dattran.game.superkid.loader.Loader;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class KidAnimationLoader implements Loader<KidAnimation> {
    private final static Logger LOGGER = Logger.getLogger(KidAnimationLoader.class.getName());
    private static final float FRAME_DURATION = 0.1f;

    public final static KidAnimationLoader instance = new KidAnimationLoader();
    private KidAnimation kidAnimation;
    private KidAnimationLoader() {

    }
    @Override
    public void load() {
        if (kidAnimation != null) {
            LOGGER.log(Level.WARNING, "Kid Animation is loaded");
        }
        else {
            LOGGER.log(Level.INFO, "Start-Loading Kid Animation");
            KidAnimation.KidAnimationBuilder builder = KidAnimation.KidAnimationBuilder.aKidAnimation();

            Animation<TextureRegion> deadAni = new Animation<>(FRAME_DURATION, textureRegions("Dead.png"));
            deadAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withDead(deadAni);

            Animation<TextureRegion> hurtAni = new Animation<>(FRAME_DURATION, textureRegions("Hurt.png"));
            hurtAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withHurt(hurtAni);

            Animation<TextureRegion> idleAni = new Animation<>(FRAME_DURATION, textureRegions("Idle.png"));
            idleAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withIdle(idleAni);

            TextureRegion [] jumpRegions = textureRegions("Jump.png");
            TextureRegion [] jumpFrames = new TextureRegion[5];
            System.arraycopy(jumpRegions,0,jumpFrames,0,5);
            TextureRegion [] fallFrames = new TextureRegion[5];
            System.arraycopy(jumpRegions,5,fallFrames,0,5);

            Animation<TextureRegion> jump = new Animation<>(FRAME_DURATION, jumpFrames);
            jump.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withJump(jump);

            Animation<TextureRegion> fallAni = new Animation<>(FRAME_DURATION, fallFrames);
            fallAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withFall(fallAni);

            Animation<TextureRegion> kickAni = new Animation<>(FRAME_DURATION, textureRegions("Kick.png"));
            kickAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withKick(kickAni);

            Animation<TextureRegion> punchAni = new Animation<>(FRAME_DURATION, textureRegions("Punch.png"));
            punchAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withPunch(punchAni);

            Animation<TextureRegion> runAni = new Animation<>(FRAME_DURATION, textureRegions("Run.png"));
            runAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withRun(runAni);

            Animation<TextureRegion> shieldAni = new Animation<>(FRAME_DURATION, textureRegions("Shield.png"));
            shieldAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withShield(shieldAni);

            Animation<TextureRegion> thumpAni = new Animation<>(FRAME_DURATION, textureRegions("Thump.png"));
            thumpAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withThump(thumpAni);

            Animation<TextureRegion> walkAni = new Animation<>(FRAME_DURATION, textureRegions("Walk.png"));
            walkAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withWalk(walkAni);


            this.kidAnimation = builder.build();
            LOGGER.log(Level.INFO, "Ende-Loading Kid Animation");
        }
    }

    private TextureRegion[] textureRegions(String imageName) {
        Texture texture  = new Texture("graphic/kid/animation/" + imageName);
        return TextureRegion.split(texture, 128, 128)[0];
    }

    @Override
    public void dispose() {

    }

    @Override
    public KidAnimation loadedResource() {
        if (kidAnimation == null) {
            load();
        }
        return kidAnimation;
    }
}
