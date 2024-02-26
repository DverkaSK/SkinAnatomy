package ru.dverkask.skinanatomy.api;

import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public enum SkinAnatomyAPI implements SkinAnatomy {
    INSTANCE;
    private final Map<UUID, String> skins = new HashMap<>();

    public void addCustomSkin(@NonNull UUID uuid,
                              @NonNull String url) {
        skins.put(uuid, url);
    }

    public SkinDecomposer createSkinDecomposer(@NonNull String url) throws IOException {
        return new SkinDecomposer(url);
    }
}
