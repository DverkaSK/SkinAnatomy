package ru.dverkask.skinanatomy.api;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SkinAnatomyAPI {
    @Getter
    private final static Map<UUID, String> skins = new HashMap<>();
    public static void addCustomSkin(@NonNull UUID uuid,
                                     @NonNull String url) {
        skins.put(uuid, url);
    }

    public static SkinDecomposer createSkinDecomposer(@NonNull String url) throws IOException {
        return new SkinDecomposer(url);
    }
}
