package ru.dverkask.skinanatomy.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.dverkask.skinanatomy.SkinAnatomyPlugin;
import ru.dverkask.skinanatomy.api.*;
import ru.dverkask.skinanatomy.skin.CustomSkinApplier;
import ru.dverkask.skinanatomy.skin.SkinLoader;
import ru.dverkask.skinanatomy.skin.SkinManager;
import ru.dverkask.skinanatomy.utils.MessageUtils;
import ru.dverkask.skinanatomy.utils.SkinUtils;

import java.io.IOException;

import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkinAnatomyCommand implements CommandExecutor {
    private static final String            DEFAULT_SKIN_URL = SkinAnatomyPlugin.getInstance().getConfig().getString("skinanatomy.defaultSkinURL");
    private final        SkinAnatomy       API              = SkinAnatomyAPI.INSTANCE;
    private final        SkinAnatomyPlugin PLUGIN           = SkinAnatomyPlugin.getInstance();
    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("skinanatomy.use")) {
                if (args.length == 1) {
                    handleOneArgument(player, args);
                } else if (args.length == 3) {
                    handleThreeArguments(player, args);
                } else player.sendMessage(MessageUtils.COMMAND_USAGE.getComponent());
            } else {
                player.sendMessage(MessageUtils.NO_PERMISSION.getComponent());
            }
        }

        return true;
    }

    private void handleOneArgument(@NotNull Player player,
                                   @NotNull String[] args) {
        try {
            if (args[0].equals("get")) {
                String message = PLUGIN.getConfig().getString(MessageUtils.SKIN_URL.getPath());
                String url = API.getSkins().containsKey(player.getUniqueId()) ?
                        API.getSkins().get(player.getUniqueId()) :
                        SkinManager.getSkinByNickname(player.getName())
                                .getAsJsonObject("textures")
                                .getAsJsonObject("SKIN")
                                .get("url")
                                .getAsString();
                String clickUrl = "<click:open_url:" + url + ">" + url + "</click>";
                message = message.replace("{url}", clickUrl);
                player.sendMessage(MiniMessage.miniMessage().deserialize(message));
            }
        } catch (IOException e) {
            player.sendMessage(MessageUtils.NO_PLAYER_SKIN.getComponent());
        }
    }

    private void handleThreeArguments(@NotNull Player player,
                                      @NotNull String[] args) {
        try {
            if (DEFAULT_SKIN_URL == null) {
                player.sendMessage(MessageUtils.DEFAULT_SKIN_NOT_FOUND.getComponent());
                return;
            }

            if (args[0].equals("set")) {
                String  urlPattern = "^https://.*\\.png$";
                Pattern pattern    = Pattern.compile(urlPattern);
                Matcher matcher    = pattern.matcher(args[2]);

                SkinDecomposer playerSkin = SkinUtils.getPlayerSkin(player);
                ResultSkin     resultSkin = SkinUtils.getResultSkin(player, playerSkin);

                SkinDecomposer targetSkin;

                if (matcher.matches()) {
                    targetSkin = API.createSkinDecomposer(args[2]);
                } else {
                    targetSkin = SkinUtils.getTargetSkin(args[2]);

                    if (targetSkin == null)
                        player.sendMessage(MessageUtils.SKIN_BY_NICKNAME_NOT_FOUND.getComponent());
                }

                BiConsumer<AbstractPlayerSkinPart, Component> loadSkinPart = (skinPart, successMessage) -> {
                    resultSkin.drawPart(
                            skinPart.getSkinPart().getImage(),
                            skinPart.getSkinPart().getX(),
                            skinPart.getSkinPart().getY()
                    );

                    player.sendMessage(successMessage);
                };

                switch (args[1]) {
                    case "head" ->
                            loadSkinPart.accept(targetSkin.getHead(), MessageUtils.HEAD_LOAD_SUCCESS.getComponent());
                    case "body" ->
                            loadSkinPart.accept(targetSkin.getBody(), MessageUtils.BODY_LOAD_SUCCESS.getComponent());
                    case "lefthand" ->
                            loadSkinPart.accept(targetSkin.getLeftHand(), MessageUtils.LEFT_HAND_LOAD_SUCCESS.getComponent());
                    case "righthand" ->
                            loadSkinPart.accept(targetSkin.getRightHand(), MessageUtils.RIGHT_HAND_LOAD_SUCCESS.getComponent());
                    case "leftleg" ->
                            loadSkinPart.accept(targetSkin.getLeftLeg(), MessageUtils.LEFT_LEG_LOAD_SUCCESS.getComponent());
                    case "rightleg" ->
                            loadSkinPart.accept(targetSkin.getRightLeg(), MessageUtils.RIGHT_LEG_LOAD_SUCCESS.getComponent());
                }

                String skinUrl = SkinLoader.getSkinURL(resultSkin);
                API.addCustomSkin(player.getUniqueId(), skinUrl);
                CustomSkinApplier.apply(player, skinUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(MessageUtils.ERROR_LOADING_SKIN.getComponent());
        }
    }
}
