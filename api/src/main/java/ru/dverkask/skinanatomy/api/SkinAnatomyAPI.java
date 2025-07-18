package ru.dverkask.skinanatomy.api;

import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Deprecated
public enum SkinAnatomyAPI implements SkinAnatomy {

    @Deprecated
    INSTANCE;

    @Deprecated
    private final Map<UUID, String> skins = new HashMap<>();

    @Deprecated
    public void addCustomSkin(@NonNull UUID uuid,
                              @NonNull String url) {
        skins.put(uuid, url);
    }

    @Deprecated
    public SkinDecomposer createSkinDecomposer(@NonNull String url) throws IOException {
        return new SkinDecomposer(url);
    }
}
