package ru.dverkask.skinanatomy.events;

import com.google.gson.JsonObject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.dverkask.skinanatomy.SkinAnatomyPlugin;
import ru.dverkask.skinanatomy.api.SkinAnatomyAPI;
import ru.dverkask.skinanatomy.skin.SkinManager;
import ru.dverkask.skinanatomy.utils.MessageUtils;

import java.io.IOException;

public class PlayerJoinSkinHandler implements Listener {
    private static final String DEFAULT_SKIN_URL = SkinAnatomyPlugin.getInstance().getConfig().getString("skinanatomy.defaultSkinURL");

    @EventHandler public void onPlayerJoin(PlayerJoinEvent e) throws IOException {
        Player player = e.getPlayer();

        if (SkinAnatomyAPI.getSkins().get(player.getUniqueId()) != null)
            return;

        JsonObject playerSkinJson = SkinManager.getSkinByNickname(player.getName());

        if (playerSkinJson == null || !playerSkinJson.has("textures")) {
            if (DEFAULT_SKIN_URL != null) {
                SkinAnatomyAPI.addCustomSkin(player.getUniqueId(), DEFAULT_SKIN_URL);
            } else {
                player.sendMessage(MessageUtils.DEFAULT_SKIN_NOT_FOUND.getComponent());
            }
        } else {
            SkinAnatomyAPI.addCustomSkin(player.getUniqueId(), playerSkinJson
                    .getAsJsonObject("textures")
                    .getAsJsonObject("SKIN")
                    .get("url")
                    .getAsString());
        }
    }
}
