package ru.dverkask.skinanatomy;

import lombok.Getter;
import net.skinsrestorer.api.SkinsRestorer;
import net.skinsrestorer.api.SkinsRestorerProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.dverkask.skinanatomy.api.SkinAnatomyAPI;
import ru.dverkask.skinanatomy.api.SkinAnatomyProvider;
import ru.dverkask.skinanatomy.api.SkinAnatomyWrapper;
import ru.dverkask.skinanatomy.command.SkinAnatomyCommand;
import ru.dverkask.skinanatomy.tabcompleter.SkinAnatomyTabCompleter;

import java.util.UUID;

public final class SkinAnatomyPlugin extends JavaPlugin {
    @Getter
    private static SkinsRestorer      skinsRestorerAPI;
    @Getter
    private static SkinAnatomyPlugin  instance;
    @Getter
    private static SkinAnatomyWrapper skinAnatomyAPI;

    @Override
    public void onEnable() {
        SkinAnatomyProvider.register(new SkinAnatomyWrapper());

        instance = this;
        skinsRestorerAPI = SkinsRestorerProvider.get();
        skinAnatomyAPI = (SkinAnatomyWrapper) SkinAnatomyProvider.get();

        saveDefaultConfig();

        this.getCommand("skinanatomy").setExecutor(new SkinAnatomyCommand());
        this.getCommand("skinanatomy").setTabCompleter(new SkinAnatomyTabCompleter());
    }
}
