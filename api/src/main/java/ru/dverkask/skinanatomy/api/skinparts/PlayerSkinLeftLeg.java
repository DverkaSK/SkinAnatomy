package ru.dverkask.skinanatomy.api.skinparts;

import ru.dverkask.skinanatomy.api.AbstractPlayerSkinPart;
import ru.dverkask.skinanatomy.api.SkinPartData;

import java.io.IOException;

@Deprecated
public class PlayerSkinLeftLeg extends AbstractPlayerSkinPart {

    @Deprecated
    private SkinPartData front;

    @Deprecated
    private SkinPartData back;

    @Deprecated
    private SkinPartData right;

    @Deprecated
    private SkinPartData left;

    @Deprecated
    private SkinPartData top;

    @Deprecated
    private SkinPartData bottom;

    @Deprecated
    public PlayerSkinLeftLeg(final String skinUrl) throws IOException {
        super(skinUrl, 0, 48, 32, 16);

        this.front = createSkinPartData(20, 4, 4, 12);
        this.back = createSkinPartData(28, 4, 4, 12);
        this.right = createSkinPartData(16, 4, 4, 12);
        this.left = createSkinPartData(24, 4, 4, 12);
        this.top = createSkinPartData(20, 0, 4, 4);
        this.bottom = createSkinPartData(24, 0, 4, 4);
    }

    @Deprecated
    public SkinPartData getFrontSide() {
        return this.front;
    }

    @Deprecated
    public SkinPartData getBackSide() {
        return this.back;
    }

    @Deprecated
    public SkinPartData getRightSide() {
        return this.right;
    }

    @Deprecated
    public SkinPartData getLeftSide() {
        return this.left;
    }

    @Deprecated
    public SkinPartData getTopSide() {
        return this.top;
    }

    @Deprecated
    public SkinPartData getBottomSide() {
        return this.bottom;
    }

    @Deprecated
    public void setFrontSide(SkinPartData data) {
        this.front = data;
    }

    @Deprecated
    public void setBackSide(SkinPartData data) {
        this.back = data;
    }

    @Deprecated
    public void setLeftSide(SkinPartData data) {
        this.left = data;
    }

    @Deprecated
    public void setRightSide(SkinPartData data) {
        this.right = data;
    }

    @Deprecated
    public void setTopSide(SkinPartData data) {
        this.top = data;
    }

    @Deprecated
    public void setBottomSide(SkinPartData data) {
        this.bottom = data;
    }
}
