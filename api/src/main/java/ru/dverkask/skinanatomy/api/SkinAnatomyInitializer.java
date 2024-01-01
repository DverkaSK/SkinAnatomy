package ru.dverkask.skinanatomy.api;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.dverkask.skinanatomy.api.skinparts.*;
import ru.dverkask.skinanatomy.api.skinparts.impl.*;

import java.io.IOException;

public class SkinAnatomyInitializer implements SkinAnatomy {
    private final Body body;
    private final Head head;
    private final LeftHand leftHand;
    private final RightHand rightHand;
    private final LeftLeg leftLeg;
    private final RightLeg rightLeg;

    public SkinAnatomyInitializer(final String skinUrl) throws IOException {
        this.body = new PlayerSkinBody(skinUrl);
        this.head = new PlayerSkinHead(skinUrl);
        this.leftHand = new PlayerSkinLeftHand(skinUrl);
        this.rightHand = new PlayerSkinRightHand(skinUrl);
        this.leftLeg = new PlayerSkinLeftLeg(skinUrl);
        this.rightLeg = new PlayerSkinRightLeg(skinUrl);
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
}
