package ru.dverkask.skinanatomy.api;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Deprecated
public interface SkinAnatomy {

    @Deprecated
    Map<UUID, String> getSkins();

    @Deprecated
    void addCustomSkin(UUID uuid, String url);

    @Deprecated
    SkinDecomposer createSkinDecomposer(String url) throws IOException;

}
