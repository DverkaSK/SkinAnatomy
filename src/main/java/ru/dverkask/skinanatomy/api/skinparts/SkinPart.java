package ru.dverkask.skinanatomy.api.skinparts;

import ru.dverkask.skinanatomy.api.SkinPartData;

public interface SkinPart {
    SkinPartData getFrontSide();
    SkinPartData getBackSide();
    SkinPartData getRightSide();
    SkinPartData getLeftSide();
    SkinPartData getTopSide();
    SkinPartData getBottomSide();

    void setFrontSide(SkinPartData data);
    void setBackSide(SkinPartData data);
    void setLeftSide(SkinPartData data);
    void setRightSide(SkinPartData data);
    void setTopSide(SkinPartData data);
    void setBottomSide(SkinPartData data);
}
