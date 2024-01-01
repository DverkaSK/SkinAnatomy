package ru.dverkask.skinanatomy.api;

import lombok.AllArgsConstructor;

import java.awt.image.BufferedImage;

@AllArgsConstructor
public class SkinPartData {
    private BufferedImage image;
    private int           width;
    private int           height;
    private int           x;
    private int           y;

    public SkinPartData(BufferedImage image, int x, int y) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = x;
        this.y = y;
    }
}