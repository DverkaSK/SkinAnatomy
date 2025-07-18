package ru.dverkask.skinanatomy.api;

import lombok.NonNull;

@Deprecated
public class SkinAnatomyProvider {

    @Deprecated
    private static SkinAnatomy instance;

    @Deprecated
    public static void register(@NonNull SkinAnatomy instanceToRegister) {
        if (instance != null) {
            throw new IllegalStateException("SkinAnatomyProvider is already initialized.");
        }

        instance = instanceToRegister;
    }

    @Deprecated
    public static @NonNull SkinAnatomy get() {
        if (instance == null) {
            throw new IllegalStateException("SkinAnatomyProvider is not initialized.");
        }

        return instance;
    }
}
