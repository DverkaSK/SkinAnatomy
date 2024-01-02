package ru.dverkask.skinanatomy.skin;

import java.awt.image.BufferedImage;

public interface SkinLoader {
    void uploadSkin(final BufferedImage image);
    Skin loadSkin(final String identifier);
}
