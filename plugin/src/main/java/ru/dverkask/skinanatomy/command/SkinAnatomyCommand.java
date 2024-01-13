package ru.dverkask.skinanatomy.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.dverkask.skinanatomy.api.ResultSkin;
import ru.dverkask.skinanatomy.api.SkinAnatomy;
import ru.dverkask.skinanatomy.api.SkinAnatomyInitializer;
import ru.dverkask.skinanatomy.api.skinparts.impl.*;
import ru.dverkask.skinanatomy.skin.CustomSkinApplier;
import ru.dverkask.skinanatomy.skin.SkinLoader;
import ru.dverkask.skinanatomy.skin.SkinManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkinAnatomyCommand implements CommandExecutor {
    private final Map<UUID, String> skins = new HashMap<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("skinanatomy.use")) {
                if (args.length == 0) player.sendMessage(""); //command usage
                if (args.length == 1) {
                    handleOneArgument(player, args);
                } else if (args.length == 3) {
                    handleThreeArguments(player, args);
                }
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
                                .getAsString()); //link to skin on imgur
            }
        } catch (IOException e) {
            player.sendMessage(""); //no player skin
        }
    }

    private void handleThreeArguments(@NotNull Player player,
                                      @NotNull String[] args) {
        try {
            if (args[0].equals("set")) {
                String  urlPattern = "^https://.*\\.png$";
                Pattern pattern    = Pattern.compile(urlPattern);

                SkinAnatomy playerSkin = null;
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
                    //playerSkin = url to default skin
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
                        //player.sendMessage skin by nickname not find
                    }
                }

                if (args[1].equals("head")) {
                    PlayerSkinHead head = (PlayerSkinHead) targetSkin.getHead();

                    resultSkin.drawPart(head.getSkinPart().getImage(),
                            head.getSkinPart().getX(),
                            head.getSkinPart().getY());
                } else if (args[1].equals("body")) {
                    PlayerSkinBody body = (PlayerSkinBody) targetSkin.getBody();

                    resultSkin.drawPart(body.getSkinPart().getImage(),
                            body.getSkinPart().getX(),
                            body.getSkinPart().getY());
                } else if (args[1].equals("lefthand")) {
                    PlayerSkinLeftHand leftHand = (PlayerSkinLeftHand) targetSkin.getLeftHand();

                    resultSkin.drawPart(leftHand.getSkinPart().getImage(),
                            leftHand.getSkinPart().getX(),
                            leftHand.getSkinPart().getY());
                } else if (args[1].equals("righthand")) {
                    PlayerSkinRightHand rightHand = (PlayerSkinRightHand) targetSkin.getRightHand();

                    resultSkin.drawPart(rightHand.getSkinPart().getImage(),
                            rightHand.getSkinPart().getX(),
                            rightHand.getSkinPart().getY());
                } else if (args[1].equals("leftleg")) {
                    PlayerSkinLeftLeg leftLeg = (PlayerSkinLeftLeg) targetSkin.getLeftLeg();

                    resultSkin.drawPart(leftLeg.getSkinPart().getImage(),
                            leftLeg.getSkinPart().getX(),
                            leftLeg.getSkinPart().getY());
                } else if (args[1].equals("rightleg")) {
                    PlayerSkinRightLeg rightLeg = (PlayerSkinRightLeg) targetSkin.getRightLeg();

                    resultSkin.drawPart(rightLeg.getSkinPart().getImage(),
                            rightLeg.getSkinPart().getX(),
                            rightLeg.getSkinPart().getY());
                }

                String skinUrl = SkinLoader.getSkinURL(resultSkin);
                skins.put(player.getUniqueId(), skinUrl);
                CustomSkinApplier.apply(player, skinUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage(""); //error load skin message
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
