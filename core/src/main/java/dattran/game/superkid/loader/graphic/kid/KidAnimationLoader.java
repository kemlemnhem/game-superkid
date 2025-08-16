package dattran.game.superkid.loader.graphic.kid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import dattran.game.superkid.loader.Loader;
import dattran.game.superkid.loader.graphic.AtlasWrapper;

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

            AtlasWrapper atlas = new AtlasWrapper("graphic/kid/animation/Kid.atlas");


            Animation<TextureRegion> deadAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Dead"));
            deadAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withDead(deadAni);

            Animation<TextureRegion> hurtAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Hurt"));
            hurtAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withHurt(hurtAni);



            Animation<TextureRegion> idleAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Idle"));
            idleAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withIdle(idleAni);

            Animation<TextureRegion> jump = new Animation<>(FRAME_DURATION, atlas.findRegions("Jump"));
            jump.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withJump(jump);

            Animation<TextureRegion> fallAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Fall"));
            fallAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withFall(fallAni);

            Animation<TextureRegion> kickAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Kick"));
            kickAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withKick(kickAni);

            Animation<TextureRegion> punchAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Punch"));
            punchAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withPunch(punchAni);

            Animation<TextureRegion> runAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Run"));
            runAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withRun(runAni);

            Animation<TextureRegion> shieldAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Shield"));
            shieldAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withShield(shieldAni);

            Animation<TextureRegion> thumpAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Thump"));
            thumpAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withThump(thumpAni);

            Animation<TextureRegion> walkAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Walk"));
            walkAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withWalk(walkAni);

            builder.withMaxHeight(atlas.getMaxHeight());
            builder.withMaxWidth(atlas.getMaxWidth());

            this.kidAnimation = builder.build();
            LOGGER.log(Level.INFO, "Ende-Loading Kid Animation");
        }
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
