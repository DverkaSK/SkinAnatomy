package ru.dverkask.skinanatomy.utils;

import com.google.gson.JsonObject;
import lombok.NonNull;
import org.bukkit.entity.Player;
import ru.dverkask.skinanatomy.SkinAnatomyPlugin;
import ru.dverkask.skinanatomy.api.ResultSkin;
import ru.dverkask.skinanatomy.api.SkinAnatomyAPI;
import ru.dverkask.skinanatomy.api.SkinDecomposer;
import ru.dverkask.skinanatomy.skin.SkinManager;

import java.io.IOException;

public class SkinUtils {
    private static final String DEFAULT_SKIN_URL = SkinAnatomyPlugin.getInstance().getConfig().getString("skinanatomy.defaultSkinURL");
    public static SkinDecomposer getPlayerSkin(@NonNull Player player) throws IOException {
        JsonObject playerSkinJson = SkinManager.getSkinByNickname(player.getName());

        if (DEFAULT_SKIN_URL == null) {
            player.sendMessage(MessageUtils.DEFAULT_SKIN_NOT_FOUND.getComponent());
            return null;
        }

        if (playerSkinJson == null || !playerSkinJson.has("textures"))
            return SkinAnatomyAPI.createSkinDecomposer(DEFAULT_SKIN_URL);

        return SkinAnatomyAPI.createSkinDecomposer(getSkinUrlFromJson(playerSkinJson));
    }
    public static SkinDecomposer getTargetSkin(@NonNull String arg) throws IOException {
        JsonObject targetSkinJson = SkinManager.getSkinByNickname(arg);

        if (targetSkinJson == null || !targetSkinJson.has("textures"))
            return null;

        return SkinAnatomyAPI.createSkinDecomposer(getSkinUrlFromJson(targetSkinJson));
    }

    public static ResultSkin getResultSkin(@NonNull Player player,
                                           @NonNull SkinDecomposer playerSkin) throws IOException {
        return SkinAnatomyAPI.getSkins().containsKey(player.getUniqueId()) ?
                new ResultSkin(SkinAnatomyAPI.getSkins().get(player.getUniqueId())) :
                playerSkin.getResultSkin();
    }

    private static String getSkinUrlFromJson(JsonObject json) {
        return json
                .getAsJsonObject("textures")
                .getAsJsonObject("SKIN")
                .get("url")
                .getAsString();
    }
}
