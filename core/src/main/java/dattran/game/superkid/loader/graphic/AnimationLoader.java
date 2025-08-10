package dattran.game.superkid.loader.graphic;

import dattran.game.superkid.loader.LoadedResource;
import dattran.game.superkid.loader.Loader;
import dattran.game.superkid.loader.graphic.homeless1.Homeless1AnimationLoader;
import dattran.game.superkid.loader.graphic.kid.KidAnimationLoader;

public final class AnimationLoader implements Loader<LoadedResource> {
    public static final AnimationLoader instance = new AnimationLoader();
    private AnimationLoader() {}
    @Override
    public void load() {
        KidAnimationLoader.instance.load();
        Homeless1AnimationLoader.instance.load();
    }

    @Override
    public void dispose() {
        KidAnimationLoader.instance.dispose();
        Homeless1AnimationLoader.instance.dispose();
    }

    @Override
    public LoadedResource loadedResource() {
        return null;
    }
}
