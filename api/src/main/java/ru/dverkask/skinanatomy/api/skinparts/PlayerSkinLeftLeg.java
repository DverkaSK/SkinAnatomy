package ru.dverkask.skinanatomy.api.skinparts;

import ru.dverkask.skinanatomy.api.AbstractPlayerSkinPart;
import ru.dverkask.skinanatomy.api.SkinPartData;

import java.io.IOException;

public class PlayerSkinLeftLeg extends AbstractPlayerSkinPart {
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
