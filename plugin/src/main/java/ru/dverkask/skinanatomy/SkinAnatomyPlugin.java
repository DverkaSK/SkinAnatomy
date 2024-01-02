package ru.dverkask.skinanatomy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.dverkask.skinanatomy.listener.PlayerSkinListener;

public final class SkinAnatomyPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PlayerSkinListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
