package ru.dverkask.skinanatomy.api.skinparts.impl;

import lombok.Getter;
import lombok.Setter;
import ru.dverkask.skinanatomy.api.SkinPartData;
import ru.dverkask.skinanatomy.api.skinparts.LeftLeg;
import ru.dverkask.skinanatomy.utils.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerSkinLeftLeg implements LeftLeg {
    private SkinPartData front;
    private SkinPartData back;
    private SkinPartData right;
    private SkinPartData left;
    private SkinPartData top;
    private SkinPartData bottom;
    @Getter
    @Setter
    private BufferedImage leftLeg;
    public PlayerSkinLeftLeg(final String skinUrl) throws IOException {
        BufferedImage skin = ImageLoader.loadImage(skinUrl);

        this.leftLeg = skin.getSubimage(0, 48, 32, 16);
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
