package ru.dverkask.skinanatomy;

import lombok.Getter;
import net.skinsrestorer.api.SkinsRestorer;
import net.skinsrestorer.api.SkinsRestorerProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.dverkask.skinanatomy.command.SkinAnatomyCommand;
import ru.dverkask.skinanatomy.listener.PlayerSkinApplierListener;
import ru.dverkask.skinanatomy.tabcompleter.SkinAnatomyTabCompleter;

public final class SkinAnatomyPlugin extends JavaPlugin {
    @Getter
    private static SkinsRestorer skinsRestorerAPI;
    @Getter
    private static SkinAnatomyPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        skinsRestorerAPI = SkinsRestorerProvider.get();

        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new PlayerSkinApplierListener(), this);

        this.getCommand("skinanatomy").setExecutor(new SkinAnatomyCommand());
        this.getCommand("skinanatomy").setTabCompleter(new SkinAnatomyTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
