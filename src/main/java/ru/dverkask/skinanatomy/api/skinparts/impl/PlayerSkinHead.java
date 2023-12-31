package ru.dverkask.skinanatomy.api.skinparts.impl;

import lombok.Getter;
import lombok.Setter;
import ru.dverkask.skinanatomy.api.PlayerSkinPart;
import ru.dverkask.skinanatomy.api.SkinPartData;
import ru.dverkask.skinanatomy.api.skinparts.Head;
import ru.dverkask.skinanatomy.utils.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerSkinHead extends PlayerSkinPart implements Head {
    private SkinPartData front;
    private SkinPartData back;
    private SkinPartData right;
    private SkinPartData left;
    private SkinPartData top;
    private SkinPartData bottom;
    @
    public PlayerSkinHead(final String skinUrl) throws IOException {
        super(skinUrl, 0, 0, 64, 16);
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
