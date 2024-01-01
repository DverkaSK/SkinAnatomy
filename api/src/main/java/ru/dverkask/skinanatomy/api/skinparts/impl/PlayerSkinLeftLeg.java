package ru.dverkask.skinanatomy.api.skinparts.impl;

import lombok.Getter;
import lombok.Setter;
import ru.dverkask.skinanatomy.api.PlayerSkinPart;
import ru.dverkask.skinanatomy.api.SkinPartData;
import ru.dverkask.skinanatomy.api.skinparts.LeftLeg;
import ru.dverkask.skinanatomy.utils.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerSkinLeftLeg extends PlayerSkinPart implements LeftLeg {
    private SkinPartData front;
    private SkinPartData back;
    private SkinPartData right;
    private SkinPartData left;
    private SkinPartData top;
    private SkinPartData bottom;
    public PlayerSkinLeftLeg(final String skinUrl) throws IOException {
        super(skinUrl, 0, 48, 32, 16);

        this.front = createSkinPartData(20, 4, 4, 12);
        this.back = createSkinPartData(28, 4, 4, 12);
        this.right = createSkinPartData(16, 4, 4, 12);
        this.left = createSkinPartData(24, 4, 4, 12);
        this.top = createSkinPartData(20, 0, 4, 4);
        this.bottom = createSkinPartData(24, 0, 4, 4);
    }
    @Override
    public SkinPartData getFrontSide() {
        return this.front;
    }

    @Override
    public SkinPartData getBackSide() {
        return this.back;
    }

    @Override
    public SkinPartData getRightSide() {
        return this.right;
    }

    @Override
    public SkinPartData getLeftSide() {
        return this.left;
    }

    @Override
    public SkinPartData getTopSide() {
        return this.top;
    }

    @Override
    public SkinPartData getBottomSide() {
        return this.bottom;
    }

    @Override
    public void setFrontSide(SkinPartData data) {
        this.front = data;
    }

    @Override
    public void setBackSide(SkinPartData data) {
        this.back = data;
    }

    @Override
    public void setLeftSide(SkinPartData data) {
        this.left = data;
    }

    @Override
    public void setRightSide(SkinPartData data) {
        this.right = data;
    }

    @Override
    public void setTopSide(SkinPartData data) {
        this.top = data;
    }

    @Override
    public void setBottomSide(SkinPartData data) {
        this.bottom = data;
    }
}
