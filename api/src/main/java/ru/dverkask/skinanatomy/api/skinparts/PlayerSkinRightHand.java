package ru.dverkask.skinanatomy.api.skinparts;

import ru.dverkask.skinanatomy.api.AbstractPlayerSkinPart;
import ru.dverkask.skinanatomy.api.SkinPartData;

import java.io.IOException;

@Deprecated
public class PlayerSkinRightHand extends AbstractPlayerSkinPart {

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
    public PlayerSkinRightHand(final String skinUrl) throws IOException {
        super(skinUrl, 40, 16, 16, 32);

        this.front = createSkinPartData(4, 4, 4, 12);
        this.back = createSkinPartData(12, 4, 4, 12);
        this.right = createSkinPartData(0, 4, 4, 12);
        this.left = createSkinPartData(8, 4, 4, 12);
        this.top = createSkinPartData(4, 0, 4, 4);
        this.bottom = createSkinPartData(8, 0, 4, 4);
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
