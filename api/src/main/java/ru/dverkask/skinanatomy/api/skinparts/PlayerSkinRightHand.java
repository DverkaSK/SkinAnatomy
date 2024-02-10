package ru.dverkask.skinanatomy.api.skinparts;

import ru.dverkask.skinanatomy.api.AbstractPlayerSkinPart;
import ru.dverkask.skinanatomy.api.SkinPartData;

import java.io.IOException;

public class PlayerSkinRightHand extends AbstractPlayerSkinPart {
    private SkinPartData front;
    private SkinPartData back;
    private SkinPartData right;
    private SkinPartData left;
    private SkinPartData top;
    private SkinPartData bottom;
    public PlayerSkinRightHand(final String skinUrl) throws IOException {
        super(skinUrl, 40, 16, 16, 32);

        this.front = createSkinPartData(4, 4, 4, 12);
        this.back = createSkinPartData(12, 4, 4, 12);
        this.right = createSkinPartData(0, 4, 4, 12);
        this.left = createSkinPartData(8, 4, 4, 12);
        this.top = createSkinPartData(4, 0, 4, 4);
        this.bottom = createSkinPartData(8, 0, 4, 4);
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
