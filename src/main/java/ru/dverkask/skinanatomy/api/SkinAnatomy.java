package ru.dverkask.skinanatomy.api;

import org.checkerframework.checker.nullness.qual.NonNull;
import ru.dverkask.skinanatomy.api.skinparts.*;

public interface SkinAnatomy {
    @NonNull Body getBody();
    @NonNull Head getHead();
    @NonNull LeftHand getLeftHand();
    @NonNull RightHand getRightHand();
    @NonNull LeftLeg getLeftLeg();
    @NonNull RightLeg getRightLeg();
}
