package ru.dverkask.skinanatomy.api;

import lombok.Getter;
import org.checkerframework.checker.nullness.qual.NonNull;
import ru.dverkask.skinanatomy.api.skinparts.*;
import ru.dverkask.skinanatomy.api.skinparts.impl.*;

import java.io.IOException;

public class SkinAnatomyInitializer implements SkinAnatomy {
    private Body      body;
    private Head      head;
    private LeftHand  leftHand;
    private RightHand rightHand;
    private LeftLeg   leftLeg;
    private RightLeg   rightLeg;
    private ResultSkin resultSkin;

    public SkinAnatomyInitializer(final String skinUrl) throws IOException {
        this.body = new PlayerSkinBody(skinUrl);
        this.head = new PlayerSkinHead(skinUrl);
        this.leftHand = new PlayerSkinLeftHand(skinUrl);
        this.rightHand = new PlayerSkinRightHand(skinUrl);
        this.leftLeg = new PlayerSkinLeftLeg(skinUrl);
        this.rightLeg = new PlayerSkinRightLeg(skinUrl);
        this.resultSkin = new ResultSkin(skinUrl);
    }

    @Override
    public @NonNull Body getBody() {
        return this.body;
    }

    @Override
    public @NonNull Head getHead() {
        return this.head;
    }

    @Override
    public @NonNull LeftHand getLeftHand() {
        return this.leftHand;
    }

    @Override
    public @NonNull RightHand getRightHand() {
        return this.rightHand;
    }

    @Override
    public @NonNull LeftLeg getLeftLeg() {
        return this.leftLeg;
    }

    @Override
    public @NonNull RightLeg getRightLeg() {
        return this.rightLeg;
    }

    @Override
    public @NonNull ResultSkin getResultSkin() {
        return this.resultSkin;
    }

    @Override
    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public void setHead(Head head) {
        this.head = head;
    }

    @Override
    public void setLeftHand(LeftHand leftHand) {
        this.leftHand = leftHand;
    }

    @Override
    public void setRightHand(RightHand rightHand) {
        this.rightHand = rightHand;
    }

    @Override
    public void setLeftLeg(LeftLeg leftLeg) {
        this.leftLeg = leftLeg;
    }

    @Override
    public void setRightLeg(RightLeg rightLeg) {
        this.rightLeg = rightLeg;
    }

    @Override
    public void setResultSkin(ResultSkin resultSkin) {
        this.resultSkin = resultSkin;
    }
}
