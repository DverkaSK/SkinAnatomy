package ru.dverkask.skinanatomy.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.dverkask.skinanatomy.api.ResultSkin;
import ru.dverkask.skinanatomy.api.SkinAnatomy;
import ru.dverkask.skinanatomy.api.SkinAnatomyInitializer;
import ru.dverkask.skinanatomy.api.skinparts.impl.*;
import ru.dverkask.skinanatomy.skin.CustomSkinApplier;
import ru.dverkask.skinanatomy.skin.SkinLoader;
import ru.dverkask.skinanatomy.skin.SkinManager;
public class PlayerSkinApplierListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws Exception {
        Player player = e.getPlayer();

        SkinAnatomy prisonerSkin = new SkinAnatomyInitializer(SkinManager.getSkinByNickname("5opka")
                .getAsJsonObject("textures")
                .getAsJsonObject("SKIN")
                .get("url")
                .getAsString());
        SkinAnatomy playerSkin = new SkinAnatomyInitializer(SkinManager.getSkinByNickname("Mipix")
                .getAsJsonObject("textures")
                .getAsJsonObject("SKIN")
                .get("url")
                .getAsString());

        ResultSkin resultSkin = playerSkin.getResultSkin();

        PlayerSkinBody      body      = (PlayerSkinBody) prisonerSkin.getBody();
        PlayerSkinRightLeg  rightLeg  = (PlayerSkinRightLeg) prisonerSkin.getRightLeg();
        PlayerSkinLeftLeg   leftLeg   = (PlayerSkinLeftLeg) prisonerSkin.getLeftLeg();
        PlayerSkinRightHand rightHand = (PlayerSkinRightHand) prisonerSkin.getRightHand();
        PlayerSkinLeftHand  leftHand  = (PlayerSkinLeftHand) prisonerSkin.getLeftHand();

        resultSkin.drawPart(body.getSkinPart().getImage(),
                body.getSkinPart().getX(),
                body.getSkinPart().getY());
        resultSkin.drawPart(rightHand.getSkinPart().getImage(),
                rightHand.getSkinPart().getX(),
                rightHand.getSkinPart().getY());
        resultSkin.drawPart(leftHand.getSkinPart().getImage(),
                leftHand.getSkinPart().getX(),
                leftHand.getSkinPart().getY());
        resultSkin.drawPart(rightLeg.getSkinPart().getImage(),
                rightLeg.getSkinPart().getX(),
                rightLeg.getSkinPart().getY());
        resultSkin.drawPart(leftLeg.getSkinPart().getImage(),
                leftLeg.getSkinPart().getX(),
                leftLeg.getSkinPart().getY());

        player.sendMessage("LOL");

        CustomSkinApplier.apply(player, SkinLoader.getSkinURL(resultSkin));
    }
}
