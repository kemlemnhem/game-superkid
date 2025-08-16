package dattran.game.superkid.loader.graphic.kid;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.loader.LoadedResource;

public class KidAnimation implements LoadedResource {
    private Animation<TextureRegion> dead, hurt, idle, jump, fall, kick, punch, run, shield, thump, walk;
    private int maxWidth, maxHeight;

    public Animation<TextureRegion> getDead() {
        return dead;
    }

    public Animation<TextureRegion> getHurt() {
        return hurt;
    }

    public Animation<TextureRegion> getIdle() {
        return idle;
    }

    public Animation<TextureRegion> getJump() {
        return jump;
    }

    public Animation<TextureRegion> getKick() {
        return kick;
    }

    public Animation<TextureRegion> getPunch() {
        return punch;
    }

    public Animation<TextureRegion> getRun() {
        return run;
    }

    public Animation<TextureRegion> getShield() {
        return shield;
    }

    public Animation<TextureRegion> getThump() {
        return thump;
    }

    public Animation<TextureRegion> getWalk() {
        return walk;
    }

    public Animation<TextureRegion> getFall() {
        return fall;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public static final class KidAnimationBuilder {
        private Animation<TextureRegion> dead;
        private Animation<TextureRegion> hurt;
        private Animation<TextureRegion> idle;
        private Animation<TextureRegion> jump;
        private Animation<TextureRegion> fall;
        private Animation<TextureRegion> kick;
        private Animation<TextureRegion> punch;
        private Animation<TextureRegion> run;
        private Animation<TextureRegion> shield;
        private Animation<TextureRegion> thump;
        private Animation<TextureRegion> walk;
        private int maxHeight;
        private int maxWidth;

        private KidAnimationBuilder() {
        }

        public static KidAnimationBuilder aKidAnimation() {
            return new KidAnimationBuilder();
        }

        public KidAnimationBuilder withDead(Animation<TextureRegion> dead) {
            this.dead = dead;
            return this;
        }

        public KidAnimationBuilder withHurt(Animation<TextureRegion> hurt) {
            this.hurt = hurt;
            return this;
        }

        public KidAnimationBuilder withIdle(Animation<TextureRegion> idle) {
            this.idle = idle;
            return this;
        }

        public KidAnimationBuilder withJump(Animation<TextureRegion> jump) {
            this.jump = jump;
            return this;
        }

        public KidAnimationBuilder withFall(Animation<TextureRegion> fall) {
            this.fall = fall;
            return this;
        }

        public KidAnimationBuilder withKick(Animation<TextureRegion> kick) {
            this.kick = kick;
            return this;
        }

        public KidAnimationBuilder withPunch(Animation<TextureRegion> punch) {
            this.punch = punch;
            return this;
        }

        public KidAnimationBuilder withRun(Animation<TextureRegion> run) {
            this.run = run;
            return this;
        }

        public KidAnimationBuilder withShield(Animation<TextureRegion> shield) {
            this.shield = shield;
            return this;
        }

        public KidAnimationBuilder withThump(Animation<TextureRegion> thump) {
            this.thump = thump;
            return this;
        }

        public KidAnimationBuilder withWalk(Animation<TextureRegion> walk) {
            this.walk = walk;
            return this;
        }

        public KidAnimationBuilder withMaxHeight(int maxHeight) {
            this.maxHeight = maxHeight;
            return this;
        }

        public KidAnimationBuilder withMaxWidth(int maxWidth) {
            this.maxWidth = maxWidth;
            return this;
        }

        public KidAnimation build() {
            KidAnimation kidAnimation = new KidAnimation();
            kidAnimation.idle = this.idle;
            kidAnimation.hurt = this.hurt;
            kidAnimation.shield = this.shield;
            kidAnimation.dead = this.dead;
            kidAnimation.kick = this.kick;
            kidAnimation.walk = this.walk;
            kidAnimation.thump = this.thump;
            kidAnimation.punch = this.punch;
            kidAnimation.run = this.run;
            kidAnimation.jump = this.jump;
            kidAnimation.fall = this.fall;
            kidAnimation.maxWidth = this.maxWidth;
            kidAnimation.maxHeight = this.maxHeight;
            return kidAnimation;
        }
    }
}
