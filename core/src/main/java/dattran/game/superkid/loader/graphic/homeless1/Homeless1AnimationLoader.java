package dattran.game.superkid.loader.graphic.homeless1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.loader.Loader;
import dattran.game.superkid.loader.graphic.AtlasWrapper;
import dattran.game.superkid.loader.graphic.kid.KidAnimation;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Homeless1AnimationLoader implements Loader<Homeless1Animation> {
    private final static Logger LOGGER = Logger.getLogger(Homeless1AnimationLoader.class.getName());
    private static final float FRAME_DURATION = 0.1f;

    public final static Homeless1AnimationLoader instance = new Homeless1AnimationLoader();
    private Homeless1Animation homeless1Animation;
    private Homeless1AnimationLoader() {

    }
    @Override
    public void load() {
        if (homeless1Animation != null) {
            LOGGER.log(Level.WARNING, "Homeless1 Animation is loaded");
        }
        else {
            LOGGER.log(Level.INFO, "Start-Loading Homeless1 Animation");
            Homeless1Animation.Homeless1AnimationBuilder builder = Homeless1Animation.Homeless1AnimationBuilder.aHomeless1Animation();

            AtlasWrapper atlas = new AtlasWrapper("graphic/homeless1/animation/Homeless1.atlas");


            Animation<TextureRegion> attack1Ani = new Animation<>(FRAME_DURATION, atlas.findRegions("Attack_1"));
            attack1Ani.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withAttack1(attack1Ani);

            Animation<TextureRegion> attack2Ani = new Animation<>(FRAME_DURATION, atlas.findRegions("Attack_2"));
            attack2Ani.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withAttack2(attack2Ani);

            Animation<TextureRegion> deadAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Dead"));
            deadAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withDead(deadAni);

            Animation<TextureRegion> hurtAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Hurt"));
            hurtAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withHurt(hurtAni);

            Animation<TextureRegion> idle1Ani = new Animation<>(FRAME_DURATION, atlas.findRegions("Idle_1"));
            idle1Ani.setPlayMode(Animation.PlayMode.LOOP);
            builder.withIdle1(idle1Ani);

            Animation<TextureRegion> idle2Ani = new Animation<>(FRAME_DURATION, atlas.findRegions("Idle_2"));
            idle2Ani.setPlayMode(Animation.PlayMode.LOOP);
            builder.withIdle2(idle2Ani);

            Animation<TextureRegion> jump = new Animation<>(FRAME_DURATION, atlas.findRegions("Jump"));
            jump.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withJump(jump);

            Animation<TextureRegion> fallAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Fall"));
            fallAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withFall(fallAni);

            Animation<TextureRegion> runAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Run"));
            runAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withRun(runAni);

            Animation<TextureRegion> specAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Special"));
            specAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withSpecial(specAni);

            Animation<TextureRegion> walkAni = new Animation<>(FRAME_DURATION, atlas.findRegions("Walk"));
            walkAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withWalk(walkAni);

            builder.withMaxHeight(atlas.getMaxHeight());
            builder.withMaxWidth(atlas.getMaxWidth());
            this.homeless1Animation = builder.build();
            LOGGER.log(Level.INFO, "Ende-Loading HomeLess1 Animation");
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public Homeless1Animation loadedResource() {
        if (homeless1Animation == null) {
            load();
        }
        return homeless1Animation;
    }
}
