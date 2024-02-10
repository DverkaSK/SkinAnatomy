package ru.dverkask.skinanatomy.api.skinparts;

import ru.dverkask.skinanatomy.api.AbstractPlayerSkinPart;
import ru.dverkask.skinanatomy.api.SkinPartData;

import java.io.IOException;

public class PlayerSkinHead extends AbstractPlayerSkinPart {
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
    public SkinPartData getFrontSide() {
        return this.front;
    }

    public SkinPartData getBackSide() {
        return this.back;
    }

    public SkinPartData getRightSide() {
        return this.right;
    }

    public SkinPartData getLeftSide() {
        return this.left;
    }

    public SkinPartData getTopSide() {
        return this.top;
    }

    public SkinPartData getBottomSide() {
        return this.bottom;
    }

    public void setFrontSide(SkinPartData data) {
        this.front = data;
    }

    public void setBackSide(SkinPartData data) {
        this.back = data;
    }

    public void setLeftSide(SkinPartData data) {
        this.left = data;
    }

    public void setRightSide(SkinPartData data) {
        this.right = data;
    }

    public void setTopSide(SkinPartData data) {
        this.top = data;
    }

    public void setBottomSide(SkinPartData data) {
        this.bottom = data;
    }
}
