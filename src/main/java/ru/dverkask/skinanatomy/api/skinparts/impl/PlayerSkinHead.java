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
    public PlayerSkinHead(final String skinUrl) throws IOException {
        super(skinUrl, 0, 0, 64, 16);

        this.front = createSkinPartData(8, 8, 8, 8);
        this.back = createSkinPartData(24, 8, 8, 8);
        this.right = createSkinPartData(0, 8, 8, 8);
        this.left = createSkinPartData(16, 8, 8, 8);
        this.top = createSkinPartData(8, 0, 8, 8);
        this.bottom = createSkinPartData(16, 0, 8, 8);
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
