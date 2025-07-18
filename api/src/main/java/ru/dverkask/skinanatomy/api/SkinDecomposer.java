package ru.dverkask.skinanatomy.api;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.dverkask.skinanatomy.api.skinparts.*;

import java.io.IOException;

@Deprecated
public class SkinDecomposer {

    @Deprecated
    private AbstractPlayerSkinPart body;

    @Deprecated
    private AbstractPlayerSkinPart head;

    @Deprecated
    private AbstractPlayerSkinPart leftHand;

    @Deprecated
    private AbstractPlayerSkinPart rightHand;

    @Deprecated
    private AbstractPlayerSkinPart leftLeg;

    @Deprecated
    private AbstractPlayerSkinPart rightLeg;

    @Deprecated
    private ResultSkin             resultSkin;

    @Deprecated
    protected SkinDecomposer(final String skinUrl) throws IOException {
        this.body = new PlayerSkinBody(skinUrl);
        this.head = new PlayerSkinHead(skinUrl);
        this.leftHand = new PlayerSkinLeftHand(skinUrl);
        this.rightHand = new PlayerSkinRightHand(skinUrl);
        this.leftLeg = new PlayerSkinLeftLeg(skinUrl);
        this.rightLeg = new PlayerSkinRightLeg(skinUrl);
        this.resultSkin = new ResultSkin(skinUrl);
    }

    @Deprecated
    public @NonNull AbstractPlayerSkinPart getBody() {
        return this.body;
    }

    @Deprecated
    public @NonNull AbstractPlayerSkinPart getHead() {
        return this.head;
    }

    @Deprecated
    public @NonNull AbstractPlayerSkinPart getLeftHand() {
        return this.leftHand;
    }

    @Deprecated
    public @NonNull AbstractPlayerSkinPart getRightHand() {
        return this.rightHand;
    }

    @Deprecated
    public @NonNull AbstractPlayerSkinPart getLeftLeg() {
        return this.leftLeg;
    }

    @Deprecated
    public @NonNull AbstractPlayerSkinPart getRightLeg() {
        return this.rightLeg;
    }

    @Deprecated
    public @NonNull ResultSkin getResultSkin() {
        return this.resultSkin;
    }

    @Deprecated
    public void setBody(AbstractPlayerSkinPart body) {
        this.body = body;
    }

    @Deprecated
    public void setHead(AbstractPlayerSkinPart head) {
        this.head = head;
    }

    @Deprecated
    public void setLeftHand(AbstractPlayerSkinPart leftHand) {
        this.leftHand = leftHand;
    }

    @Deprecated
    public void setRightHand(AbstractPlayerSkinPart rightHand) {
        this.rightHand = rightHand;
    }

    @Deprecated
    public void setLeftLeg(AbstractPlayerSkinPart leftLeg) {
        this.leftLeg = leftLeg;
    }

    @Deprecated
    public void setRightLeg(AbstractPlayerSkinPart rightLeg) {
        this.rightLeg = rightLeg;
    }

    @Deprecated
    public void setResultSkin(ResultSkin resultSkin) {
        this.resultSkin = resultSkin;
    }
}
