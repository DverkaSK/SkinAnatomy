package ru.dverkask.skinanatomy.skin;

import lombok.Getter;
import lombok.Setter;
import net.skinsrestorer.api.connections.MineSkinAPI;
import net.skinsrestorer.api.connections.model.MineSkinResponse;
import net.skinsrestorer.api.exception.DataRequestException;
import net.skinsrestorer.api.exception.MineSkinException;
import net.skinsrestorer.api.property.SkinApplier;
import net.skinsrestorer.api.property.SkinProperty;
import net.skinsrestorer.api.property.SkinVariant;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import ru.dverkask.skinanatomy.SkinAnatomyPlugin;

@Getter
@Setter
public class CustomSkinApplier {
    private static SkinApplier<Player> skinApplier = SkinAnatomyPlugin.getSkinsRestorerAPI().getSkinApplier(Player.class);
    private static MineSkinAPI mineSkinAPI = SkinAnatomyPlugin.getSkinsRestorerAPI().getMineSkinAPI();

    public static void apply(@NonNull Player player,
                             @NonNull String url) throws MineSkinException, DataRequestException {
        MineSkinResponse response =
                mineSkinAPI.genSkin(url, SkinVariant.CLASSIC);

        SkinProperty property = response.getProperty();
        skinApplier.applySkin(player, property);
    }
}
