package ru.dverkask.skinanatomy.api.skinparts;

import lombok.Getter;
import ru.dverkask.skinanatomy.api.AbstractPlayerSkinPart;
import ru.dverkask.skinanatomy.api.SkinPartData;

import java.io.IOException;

@Getter
@Deprecated
public class PlayerSkinBody extends AbstractPlayerSkinPart {

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
    public PlayerSkinBody(final String skinUrl) throws IOException {
        super(skinUrl, 16, 16, 24, 32);

        this.front = createSkinPartData(4, 4, 8, 12);
        this.back = createSkinPartData(16, 4, 8, 12);
        this.right = createSkinPartData(0, 4, 4, 12);
        this.left = createSkinPartData(12, 4, 4, 12);
        this.top = createSkinPartData(4, 0, 8, 4);
        this.bottom = createSkinPartData(12, 0, 8, 4);
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
