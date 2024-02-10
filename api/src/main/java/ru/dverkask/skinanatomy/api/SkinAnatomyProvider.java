package ru.dverkask.skinanatomy.api;

import lombok.NonNull;

public class SkinAnatomyProvider {
    private static ISkinAnatomyAPI instance;

    public static void register(@NonNull ISkinAnatomyAPI instanceToRegister) {
        if (instance != null) {
            throw new IllegalStateException("SkinAnatomyProvider is already initialized.");
        }

        instance = instanceToRegister;
    }

    public static @NonNull ISkinAnatomyAPI get() {
        if (instance == null) {
            throw new IllegalStateException("SkinAnatomyProvider is not initialized.");
        }

        return instance;
    }
}
