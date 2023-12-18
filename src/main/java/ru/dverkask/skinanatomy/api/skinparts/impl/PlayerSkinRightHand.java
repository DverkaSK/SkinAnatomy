package ru.dverkask.skinanatomy.api.skinparts.impl;

import lombok.Getter;
import lombok.Setter;
import ru.dverkask.skinanatomy.api.SkinPartData;
import ru.dverkask.skinanatomy.api.skinparts.RightHand;
import ru.dverkask.skinanatomy.utils.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerSkinRightHand implements RightHand {
    private SkinPartData front;
    private SkinPartData back;
    private SkinPartData right;
    private SkinPartData left;
    private SkinPartData top;
    private SkinPartData bottom;
    @Getter
    @Setter
    private BufferedImage rightHand;
    public PlayerSkinRightHand(final String skinUrl) throws IOException {
        BufferedImage skin = ImageLoader.loadImage(skinUrl);

        this.rightHand = skin.getSubimage(40, 16, 16, 32);
    }
    @Override
    public SkinPartData getFrontSide() {
        return null;
    }

    @Override
    public SkinPartData getBackSide() {
        return null;
    }

    @Override
    public SkinPartData getRightSide() {
        return null;
    }

    @Override
    public SkinPartData getLeftSide() {
        return null;
    }

    @Override
    public SkinPartData getTopSide() {
        return null;
    }

    @Override
    public SkinPartData getBottomSide() {
        return null;
    }

    @Override
    public void setFrontSide(SkinPartData data) {

    }

    @Override
    public void setBackSide(SkinPartData data) {

    }

    @Override
    public void setLeftSide(SkinPartData data) {

    }

    @Override
    public void setRightSide(SkinPartData data) {

    }

    @Override
    public void setTopSide(SkinPartData data) {

    }

    @Override
    public void setBottomSide(SkinPartData data) {

    }
}
