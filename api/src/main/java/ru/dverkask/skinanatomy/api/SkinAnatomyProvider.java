package ru.dverkask.skinanatomy.api;

import org.checkerframework.checker.nullness.qual.NonNull;

public class SkinAnatomyProvider {
    private static SkinAnatomy instance;

    public static void register(@NonNull SkinAnatomy instanceToRegister) {
        if (instance != null) {
            throw new IllegalStateException("SkinAnatomyProvider is already initialized.");
        }

        instance = instanceToRegister;
    }

    public static @NonNull SkinAnatomy get() {
        if (instance == null) {
            throw new IllegalStateException("SkinAnatomyProvider is not initialized.");
        }

        return instance;
    }
}
