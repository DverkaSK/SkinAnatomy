package ru.dverkask.skinanatomy.command;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.dverkask.skinanatomy.SkinAnatomyPlugin;
import ru.dverkask.skinanatomy.api.ResultSkin;
import ru.dverkask.skinanatomy.api.SkinAnatomy;
import ru.dverkask.skinanatomy.api.SkinAnatomyInitializer;
import ru.dverkask.skinanatomy.api.skinparts.impl.*;
import ru.dverkask.skinanatomy.skin.CustomSkinApplier;
import ru.dverkask.skinanatomy.skin.SkinLoader;
import ru.dverkask.skinanatomy.skin.SkinManager;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkinAnatomyCommand implements CommandExecutor {
    private final Component         COMMAND_USAGE;
    private final Component         NO_PERMISSION;
    private final Component         NO_PLAYER_SKIN;
    private final Component         SKIN_BY_NICKNAME_NOT_FOUND;
    private final Component         ERROR_LOADING_SKIN;
    private final Component         HEAD_LOAD_SUCCESS;
    private final Component         BODY_LOAD_SUCCESS;
    private final Component         LEFT_HAND_LOAD_SUCCESS;
    private final Component         RIGHT_HAND_LOAD_SUCCESS;
    private final Component         LEFT_LEG_LOAD_SUCCESS;
    private final Component         RIGHT_LEG_LOAD_SUCCESS;
    private final String            DEFAULT_SKIN_URL;
    private final SkinAnatomyPlugin plugin =
            SkinAnatomyPlugin.getInstance();
    private final Map<UUID, String> skins  = new HashMap<>();

    public SkinAnatomyCommand() {
        this.COMMAND_USAGE = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.errors.commandUsage"));
        this.NO_PERMISSION = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.errors.noPermission"));
        this.NO_PLAYER_SKIN = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.errors.noPlayerSkin"));
        this.SKIN_BY_NICKNAME_NOT_FOUND = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.errors.skinByNicknameNotFound"));
        this.ERROR_LOADING_SKIN = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.errors.errorLoadingSkin"));
        this.HEAD_LOAD_SUCCESS = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.loadSuccess.head"));
        this.BODY_LOAD_SUCCESS = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.loadSuccess.body"));
        this.LEFT_HAND_LOAD_SUCCESS = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.loadSuccess.leftHand"));
        this.RIGHT_HAND_LOAD_SUCCESS = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.loadSuccess.rightHand"));
        this.LEFT_LEG_LOAD_SUCCESS = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.loadSuccess.leftLeg"));
        this.RIGHT_LEG_LOAD_SUCCESS = MiniMessage.miniMessage().deserialize(plugin.getConfig().getString("skinanatomy.messages.loadSuccess.rightLeg"));
        this.DEFAULT_SKIN_URL = plugin.getConfig().getString("skinanatomy.defaultSkinURL");
    }

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
                } else player.sendMessage(COMMAND_USAGE);
            } else {
                player.sendMessage(NO_PERMISSION);
            }
        }

        return true;
    }

    private void handleOneArgument(@NotNull Player player,
                                   @NotNull String[] args) {
        try {
            if (args[0].equals("get")) {
                player.sendMessage(skins.containsKey(player.getUniqueId()) ?
                        skins.get(player.getUniqueId()) :
                        SkinManager.getSkinByNickname(player.getName())
                                .getAsJsonObject("textures")
                                .getAsJsonObject("SKIN")
                                .get("url")
                                .getAsString());
            }
        } catch (IOException e) {
            player.sendMessage(NO_PLAYER_SKIN);
        }
    }

    private void handleThreeArguments(@NotNull Player player,
                                      @NotNull String[] args) {
        try {
            if (args[0].equals("set")) {
                String  urlPattern = "^https://.*\\.png$";
                Pattern pattern    = Pattern.compile(urlPattern);

                SkinAnatomy playerSkin;
                SkinAnatomy targetSkin = null;
                ResultSkin  resultSkin;
                if (SkinManager.getSkinByNickname(player.getName())
                        .has("textures")) {
                    playerSkin = new SkinAnatomyInitializer(SkinManager.getSkinByNickname(player.getName())
                            .getAsJsonObject("textures")
                            .getAsJsonObject("SKIN")
                            .get("url")
                            .getAsString());
                } else {
                    playerSkin = new SkinAnatomyInitializer(DEFAULT_SKIN_URL);
                }

                resultSkin = skins.containsKey(player.getUniqueId()) ?
                        new ResultSkin(skins.get(player.getUniqueId())) :
                        playerSkin.getResultSkin();

                Matcher matcher = pattern.matcher(args[2]);

                if (matcher.matches()) {
                    targetSkin = new SkinAnatomyInitializer(args[2]);
                } else {
                    if (SkinManager.getSkinByNickname(args[2])
                            .has("textures")) {
                        targetSkin = new SkinAnatomyInitializer(SkinManager.getSkinByNickname(player.getName())
                                .getAsJsonObject("textures")
                                .getAsJsonObject("SKIN")
                                .get("url")
                                .getAsString());
                    } else {
                        player.sendMessage(SKIN_BY_NICKNAME_NOT_FOUND);
                    }
                }

                if (args[1].equals("head")) {
                    PlayerSkinHead head = (PlayerSkinHead) targetSkin.getHead();

                    resultSkin.drawPart(head.getSkinPart().getImage(),
                            head.getSkinPart().getX(),
                            head.getSkinPart().getY());

                    player.sendMessage(HEAD_LOAD_SUCCESS);
                } else if (args[1].equals("body")) {
                    PlayerSkinBody body = (PlayerSkinBody) targetSkin.getBody();

                    resultSkin.drawPart(body.getSkinPart().getImage(),
                            body.getSkinPart().getX(),
                            body.getSkinPart().getY());

                    player.sendMessage(BODY_LOAD_SUCCESS);
                } else if (args[1].equals("lefthand")) {
                    PlayerSkinLeftHand leftHand = (PlayerSkinLeftHand) targetSkin.getLeftHand();

                    resultSkin.drawPart(leftHand.getSkinPart().getImage(),
                            leftHand.getSkinPart().getX(),
                            leftHand.getSkinPart().getY());

                    player.sendMessage(LEFT_HAND_LOAD_SUCCESS);
                } else if (args[1].equals("righthand")) {
                    PlayerSkinRightHand rightHand = (PlayerSkinRightHand) targetSkin.getRightHand();

                    resultSkin.drawPart(rightHand.getSkinPart().getImage(),
                            rightHand.getSkinPart().getX(),
                            rightHand.getSkinPart().getY());

                    player.sendMessage(RIGHT_HAND_LOAD_SUCCESS);
                } else if (args[1].equals("leftleg")) {
                    PlayerSkinLeftLeg leftLeg = (PlayerSkinLeftLeg) targetSkin.getLeftLeg();

                    resultSkin.drawPart(leftLeg.getSkinPart().getImage(),
                            leftLeg.getSkinPart().getX(),
                            leftLeg.getSkinPart().getY());

                    player.sendMessage(LEFT_LEG_LOAD_SUCCESS);
                } else if (args[1].equals("rightleg")) {
                    PlayerSkinRightLeg rightLeg = (PlayerSkinRightLeg) targetSkin.getRightLeg();

                    resultSkin.drawPart(rightLeg.getSkinPart().getImage(),
                            rightLeg.getSkinPart().getX(),
                            rightLeg.getSkinPart().getY());

                    player.sendMessage(RIGHT_LEG_LOAD_SUCCESS);
                }

                String skinUrl = SkinLoader.getSkinURL(resultSkin);
                skins.put(player.getUniqueId(), skinUrl);
                CustomSkinApplier.apply(player, skinUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage(ERROR_LOADING_SKIN);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
