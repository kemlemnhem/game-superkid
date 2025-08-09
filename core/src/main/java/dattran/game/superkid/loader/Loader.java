package dattran.game.superkid.loader;

public interface Loader<R extends LoadedResource> {
    void load();
    void dispose();
    R loadedResource();
}
