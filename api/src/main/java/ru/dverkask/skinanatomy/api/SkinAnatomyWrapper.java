package ru.dverkask.skinanatomy.api;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class SkinAnatomyWrapper implements ISkinAnatomyAPI {
    @Override public Map<UUID, String> getSkins() {
        return SkinAnatomyAPI.getSkins();
    }

    @Override public void addCustomSkin(UUID uuid, String url) {
        SkinAnatomyAPI.addCustomSkin(uuid, url);
    }

    @Override public SkinDecomposer createSkinDecomposer(String url) throws IOException {
        return SkinAnatomyAPI.createSkinDecomposer(url);
    }
}
