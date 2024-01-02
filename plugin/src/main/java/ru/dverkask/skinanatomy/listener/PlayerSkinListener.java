package ru.dverkask.skinanatomy.listener;

import net.skinsrestorer.api.exception.DataRequestException;
import net.skinsrestorer.api.exception.MineSkinException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.dverkask.skinanatomy.skin.CustomSkinApplier;
import ru.dverkask.skinanatomy.skin.SkinManager;

import java.io.IOException;

public class PlayerSkinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException, MineSkinException, DataRequestException {
        Player player = e.getPlayer();

        String skinUrl = SkinManager.getSkinByNickname("5opka")
                .getAsJsonObject("textures")
                .getAsJsonObject("SKIN")
                .get("url")
                .getAsString();

        CustomSkinApplier.apply(player, skinUrl);
    }
}
