package ru.dverkask.skinanatomy.api;

import org.bukkit.entity.Player;
import ru.dverkask.skinanatomy.api.SkinDecomposer;

import java.io.IOException;
import java.util.Map;

public interface ISkinAnatomyAPI {
    Map<Player, String> getSkins();
    void addCustomSkin(Player player, String url);
    SkinDecomposer createSkinDecomposer(String url) throws IOException;

}
