package ru.dverkask.skinanatomy.api;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.dverkask.skinanatomy.api.skinparts.*;

import java.io.IOException;

public class SkinDecomposer {
    private AbstractPlayerSkinPart body;
    private AbstractPlayerSkinPart head;
    private AbstractPlayerSkinPart leftHand;
    private AbstractPlayerSkinPart rightHand;
    private AbstractPlayerSkinPart leftLeg;
    private AbstractPlayerSkinPart rightLeg;
    private ResultSkin             resultSkin;

    protected SkinDecomposer(final String skinUrl) throws IOException {
        this.body = new PlayerSkinBody(skinUrl);
        this.head = new PlayerSkinHead(skinUrl);
        this.leftHand = new PlayerSkinLeftHand(skinUrl);
        this.rightHand = new PlayerSkinRightHand(skinUrl);
        this.leftLeg = new PlayerSkinLeftLeg(skinUrl);
        this.rightLeg = new PlayerSkinRightLeg(skinUrl);
        this.resultSkin = new ResultSkin(skinUrl);
    }

    public @NonNull AbstractPlayerSkinPart getBody() {
        return this.body;
    }

    public @NonNull AbstractPlayerSkinPart getHead() {
        return this.head;
    }

    public @NonNull AbstractPlayerSkinPart getLeftHand() {
        return this.leftHand;
    }

    public @NonNull AbstractPlayerSkinPart getRightHand() {
        return this.rightHand;
    }

    public @NonNull AbstractPlayerSkinPart getLeftLeg() {
        return this.leftLeg;
    }

    public @NonNull AbstractPlayerSkinPart getRightLeg() {
        return this.rightLeg;
    }

    public @NonNull ResultSkin getResultSkin() {
        return this.resultSkin;
    }

    public void setBody(AbstractPlayerSkinPart body) {
        this.body = body;
    }

    public void setHead(AbstractPlayerSkinPart head) {
        this.head = head;
    }

    public void setLeftHand(AbstractPlayerSkinPart leftHand) {
        this.leftHand = leftHand;
    }

    public void setRightHand(AbstractPlayerSkinPart rightHand) {
        this.rightHand = rightHand;
    }

    public void setLeftLeg(AbstractPlayerSkinPart leftLeg) {
        this.leftLeg = leftLeg;
    }

    public void setRightLeg(AbstractPlayerSkinPart rightLeg) {
        this.rightLeg = rightLeg;
    }

    public void setResultSkin(ResultSkin resultSkin) {
        this.resultSkin = resultSkin;
    }
}
