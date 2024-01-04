package ru.dverkask.skinanatomy.api.skinparts.impl;

import lombok.Getter;
import lombok.Setter;
import ru.dverkask.skinanatomy.api.PlayerSkinPart;
import ru.dverkask.skinanatomy.api.SkinPartData;
import ru.dverkask.skinanatomy.api.skinparts.Body;
import ru.dverkask.skinanatomy.utils.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Getter
public class PlayerSkinBody extends PlayerSkinPart implements Body {
    private SkinPartData front;
    private SkinPartData back;
    private SkinPartData right;
    private SkinPartData left;
    private SkinPartData top;
    private SkinPartData bottom;

    public PlayerSkinBody(final String skinUrl) throws IOException {
        super(skinUrl, 16, 16, 24, 32);

        this.front = createSkinPartData(4, 4, 8, 12);
        this.back = createSkinPartData(16, 4, 8, 12);
        this.right = createSkinPartData(0, 4, 4, 12);
        this.left = createSkinPartData(12, 4, 4, 12);
        this.top = createSkinPartData(4, 0, 8, 4);
        this.bottom = createSkinPartData(12, 0, 8, 4);
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
