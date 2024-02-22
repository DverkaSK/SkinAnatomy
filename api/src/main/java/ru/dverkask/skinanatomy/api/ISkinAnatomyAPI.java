package ru.dverkask.skinanatomy.api;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public interface ISkinAnatomyAPI {
    Map<UUID, String> getSkins();
    void addCustomSkin(UUID uuid, String url);
    SkinDecomposer createSkinDecomposer(String url) throws IOException;

}
