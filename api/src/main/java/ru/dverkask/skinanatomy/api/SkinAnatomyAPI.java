package ru.dverkask.skinanatomy.api;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SkinAnatomyAPI {
    @Getter
    private final static Map<Player, String> skins = new HashMap<>();
    public static void addCustomSkin(@NonNull Player player,
                                     @NonNull String url) {
        skins.put(player, url);
    }

    public static SkinDecomposer createSkinDecomposer(@NonNull String url) throws IOException {
        return new SkinDecomposer(url);
    }
}
