package ru.dverkask.skinanatomy.api;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Map;

public class SkinAnatomyWrapper implements ISkinAnatomyAPI {
    @Override public Map<Player, String> getSkins() {
        return SkinAnatomyAPI.getSkins();
    }

    @Override public void addCustomSkin(Player player, String url) {
        SkinAnatomyAPI.addCustomSkin(player, url);
    }

    @Override public SkinDecomposer createSkinDecomposer(String url) throws IOException {
        return SkinAnatomyAPI.createSkinDecomposer(url);
    }
}
