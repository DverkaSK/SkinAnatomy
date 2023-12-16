package ru.dverkask.skinanatomy.api;

import lombok.AllArgsConstructor;

import java.awt.image.BufferedImage;

@AllArgsConstructor
public class SkinPartData {
    private BufferedImage image;
    private int width;
    private int height;
    private int x;
    private int y;
}
