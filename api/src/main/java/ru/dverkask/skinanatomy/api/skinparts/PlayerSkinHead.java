package ru.dverkask.skinanatomy.api.skinparts;

import ru.dverkask.skinanatomy.api.AbstractPlayerSkinPart;
import ru.dverkask.skinanatomy.api.SkinPartData;

import java.io.IOException;

@Deprecated
public class PlayerSkinHead extends AbstractPlayerSkinPart {

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
    public PlayerSkinHead(final String skinUrl) throws IOException {
        super(skinUrl, 0, 0, 64, 16);

        this.front = createSkinPartData(8, 8, 8, 8);
        this.back = createSkinPartData(24, 8, 8, 8);
        this.right = createSkinPartData(0, 8, 8, 8);
        this.left = createSkinPartData(16, 8, 8, 8);
        this.top = createSkinPartData(8, 0, 8, 8);
        this.bottom = createSkinPartData(16, 0, 8, 8);
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
