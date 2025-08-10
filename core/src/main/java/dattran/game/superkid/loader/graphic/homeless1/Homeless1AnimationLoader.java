package dattran.game.superkid.loader.graphic.homeless1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.loader.Loader;
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


            Animation<TextureRegion> attack1Ani = new Animation<>(FRAME_DURATION, textureRegions("Attack_1.png"));
            attack1Ani.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withAttack1(attack1Ani);

            Animation<TextureRegion> attack2Ani = new Animation<>(FRAME_DURATION, textureRegions("Attack_2.png"));
            attack2Ani.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withAttack2(attack2Ani);

            Animation<TextureRegion> deadAni = new Animation<>(FRAME_DURATION, textureRegions("Dead.png"));
            deadAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withDead(deadAni);

            Animation<TextureRegion> hurtAni = new Animation<>(FRAME_DURATION, textureRegions("Hurt.png"));
            hurtAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withHurt(hurtAni);

            Animation<TextureRegion> idle1Ani = new Animation<>(FRAME_DURATION, textureRegions("Idle_1.png"));
            idle1Ani.setPlayMode(Animation.PlayMode.LOOP);
            builder.withIdle1(idle1Ani);

            Animation<TextureRegion> idle2Ani = new Animation<>(FRAME_DURATION, textureRegions("Idle_2.png"));
            idle2Ani.setPlayMode(Animation.PlayMode.LOOP);
            builder.withIdle2(idle2Ani);

            TextureRegion [] jumpRegions = textureRegions("Jump.png");
            TextureRegion [] jumpFrames = new TextureRegion[8];
            System.arraycopy(jumpRegions,0,jumpFrames,0,8);
            TextureRegion [] fallFrames = new TextureRegion[9];
            System.arraycopy(jumpRegions,7,fallFrames,0,9);

            Animation<TextureRegion> jump = new Animation<>(FRAME_DURATION, jumpFrames);
            jump.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withJump(jump);

            Animation<TextureRegion> fallAni = new Animation<>(FRAME_DURATION, fallFrames);
            fallAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withFall(fallAni);

            Animation<TextureRegion> runAni = new Animation<>(FRAME_DURATION, textureRegions("Run.png"));
            runAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withRun(runAni);

            Animation<TextureRegion> specAni = new Animation<>(FRAME_DURATION, textureRegions("Special.png"));
            runAni.setPlayMode(Animation.PlayMode.NORMAL);
            builder.withSpecial(specAni);

            Animation<TextureRegion> walkAni = new Animation<>(FRAME_DURATION, textureRegions("Walk.png"));
            walkAni.setPlayMode(Animation.PlayMode.LOOP);
            builder.withWalk(walkAni);


            this.homeless1Animation = builder.build();
            LOGGER.log(Level.INFO, "Ende-Loading HomeLess1 Animation");
        }
    }

    private TextureRegion[] textureRegions(String imageName) {
        Texture texture  = new Texture("graphic/homeless1/animation/" + imageName);
        return TextureRegion.split(texture, 128, 128)[0];
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
