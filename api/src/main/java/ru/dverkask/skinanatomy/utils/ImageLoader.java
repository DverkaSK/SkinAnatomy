package ru.dverkask.skinanatomy.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Deprecated
public class ImageLoader {

    @Deprecated
    public static BufferedImage loadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        return ImageIO.read(url);
    }
}
