package ru.dverkask.skinanatomy.api;

import lombok.NonNull;
import ru.dverkask.skinanatomy.api.skinparts.*;

public interface SkinAnatomy {
    @lombok.NonNull Body getBody();
    @lombok.NonNull Head getHead();
    @NonNull LeftHand getLeftHand();
    @lombok.NonNull RightHand getRightHand();
    @lombok.NonNull LeftLeg getLeftLeg();
    @lombok.NonNull RightLeg getRightLeg();
    @lombok.NonNull ResultSkin getResultSkin();
    void setBody(Body body);
    void setHead(Head head);
    void setLeftHand(LeftHand leftHand);
    void setRightHand(RightHand rightHand);
    void setLeftLeg(LeftLeg leftLeg);
    void setRightLeg(RightLeg rightLeg);
    void setResultSkin(ResultSkin resultSkin);
}
