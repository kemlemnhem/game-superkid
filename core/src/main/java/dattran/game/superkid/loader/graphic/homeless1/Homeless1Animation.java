package dattran.game.superkid.loader.graphic.homeless1;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dattran.game.superkid.loader.LoadedResource;

public class Homeless1Animation implements LoadedResource {
    private Animation<TextureRegion> attack1, attack2, dead, hurt, idle1, idle2, jump, fall, run, special, walk;
    private int maxHeight;
    private int maxWidth;

    public Animation<TextureRegion> getAttack1() {
        return attack1;
    }

    public Animation<TextureRegion> getAttack2() {
        return attack2;
    }

    public Animation<TextureRegion> getDead() {
        return dead;
    }

    public Animation<TextureRegion> getFall() {
        return fall;
    }

    public Animation<TextureRegion> getHurt() {
        return hurt;
    }

    public Animation<TextureRegion> getIdle1() {
        return idle1;
    }

    public Animation<TextureRegion> getIdle2() {
        return idle2;
    }

    public Animation<TextureRegion> getJump() {
        return jump;
    }

    public Animation<TextureRegion> getRun() {
        return run;
    }

    public Animation<TextureRegion> getSpecial() {
        return special;
    }

    public Animation<TextureRegion> getWalk() {
        return walk;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public static final class Homeless1AnimationBuilder {
        private Animation<TextureRegion> attack1;
        private Animation<TextureRegion> attack2;
        private Animation<TextureRegion> dead;
        private Animation<TextureRegion> hurt;
        private Animation<TextureRegion> idle1;
        private Animation<TextureRegion> idle2;
        private Animation<TextureRegion> jump;
        private Animation<TextureRegion> fall;
        private Animation<TextureRegion> run;
        private Animation<TextureRegion> special;
        private Animation<TextureRegion> walk;

        private int maxHeight;
        private int maxWidth;


        private Homeless1AnimationBuilder() {
        }

        public static Homeless1AnimationBuilder aHomeless1Animation() {
            return new Homeless1AnimationBuilder();
        }

        public Homeless1AnimationBuilder withAttack1(Animation<TextureRegion> attack1) {
            this.attack1 = attack1;
            return this;
        }

        public Homeless1AnimationBuilder withAttack2(Animation<TextureRegion> attack2) {
            this.attack2 = attack2;
            return this;
        }

        public Homeless1AnimationBuilder withDead(Animation<TextureRegion> dead) {
            this.dead = dead;
            return this;
        }

        public Homeless1AnimationBuilder withHurt(Animation<TextureRegion> hurt) {
            this.hurt = hurt;
            return this;
        }

        public Homeless1AnimationBuilder withIdle1(Animation<TextureRegion> idle1) {
            this.idle1 = idle1;
            return this;
        }

        public Homeless1AnimationBuilder withIdle2(Animation<TextureRegion> idle2) {
            this.idle2 = idle2;
            return this;
        }

        public Homeless1AnimationBuilder withJump(Animation<TextureRegion> jump) {
            this.jump = jump;
            return this;
        }

        public Homeless1AnimationBuilder withFall(Animation<TextureRegion> fall) {
            this.fall = fall;
            return this;
        }

        public Homeless1AnimationBuilder withRun(Animation<TextureRegion> run) {
            this.run = run;
            return this;
        }

        public Homeless1AnimationBuilder withSpecial(Animation<TextureRegion> special) {
            this.special = special;
            return this;
        }

        public Homeless1AnimationBuilder withWalk(Animation<TextureRegion> walk) {
            this.walk = walk;
            return this;
        }

        public Homeless1AnimationBuilder withMaxHeight(int maxHeight) {
            this.maxHeight = maxHeight;
            return this;
        }
        public Homeless1AnimationBuilder withMaxWidth(int maxWidth) {
            this.maxWidth = maxWidth;
            return this;
        }

        public Homeless1Animation build() {
            Homeless1Animation homeless1Animation = new Homeless1Animation();
            homeless1Animation.fall = this.fall;
            homeless1Animation.run = this.run;
            homeless1Animation.attack2 = this.attack2;
            homeless1Animation.idle1 = this.idle1;
            homeless1Animation.idle2 = this.idle2;
            homeless1Animation.dead = this.dead;
            homeless1Animation.jump = this.jump;
            homeless1Animation.attack1 = this.attack1;
            homeless1Animation.hurt = this.hurt;
            homeless1Animation.walk = this.walk;
            homeless1Animation.special = this.special;
            homeless1Animation.maxHeight = this.maxHeight;
            homeless1Animation.maxWidth = this.maxWidth;
            return homeless1Animation;
        }
    }
}
