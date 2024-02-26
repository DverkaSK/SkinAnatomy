package ru.dverkask.skinanatomy.api;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public interface SkinAnatomy {
    Map<UUID, String> getSkins();
    void addCustomSkin(UUID uuid, String url);
    SkinDecomposer createSkinDecomposer(String url) throws IOException;

}
