package ru.dverkask.skinanatomy.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@AllArgsConstructor
@Getter
@Setter
@Deprecated
public class SkinPartData {

    @Deprecated
    private BufferedImage image;

    @Deprecated
    private int           width;

    @Deprecated
    private int           height;

    @Deprecated
    private int           x;

    @Deprecated
    private int           y;

    @Deprecated
    public SkinPartData(BufferedImage image, int x, int y) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = x;
        this.y = y;
    }
}