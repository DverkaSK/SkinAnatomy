package ru.dverkask.skinanatomy;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.profile.PlayerTextures;

import java.util.UUID;

public final class SkinAnatomy extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player                                  player      = e.getPlayer();
        CraftPlayer                             craftPlayer = (CraftPlayer) player;
        net.minecraft.server.level.ServerPlayer nmsPlayer   = craftPlayer.getHandle();
        GameProfile                             gameProfile = nmsPlayer.getGameProfile();

        Property property  = gameProfile.getProperties().get("textures").iterator().next();
        String   urlBase64 = property.getValue();
        UUID     signature = UUID.fromString(property.getSignature());

        player.sendMessage(urlBase64 + " " + signature + "123");
        player.sendMessage(gameProfile.getName());
    }
}
