package dattran.game.superkid.loader.graphic;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class AtlasWrapper {
    private final TextureAtlas atlas;
    private int maxWidth;
    private int maxHeight;

    public AtlasWrapper(String imgFile) {
        this.atlas = new TextureAtlas(imgFile);
    }

    public void dispose() {
        atlas.dispose();
    }

    public Array<TextureAtlas.AtlasRegion> findRegions(String prefix) {
        Array<TextureAtlas.AtlasRegion> matched = new Array<>(TextureAtlas.AtlasRegion.class);
        for (int i = 0, n = atlas.getRegions().size; i < n; i++) {
            TextureAtlas.AtlasRegion region = atlas.getRegions().get(i);
            if (region.name.startsWith(prefix)){
                TextureAtlas.AtlasRegion newRegion = new TextureAtlas.AtlasRegion(region);
                matched.add(newRegion);
                maxWidth = Math.max(maxWidth, newRegion.getRegionWidth());
                maxHeight = Math.max(maxHeight, newRegion.getRegionHeight());
            }
        }
        return matched;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }
}
