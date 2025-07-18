package ru.dverkask.skinanatomy.api;

import lombok.Getter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@Deprecated
public class ResultSkin {

    @Deprecated
    private final int           WIDTH  = 64;

    @Deprecated
    private final int           HEIGHT = 64;

    @Getter
    @Deprecated
    private final BufferedImage skinImage;

    @Deprecated
    public ResultSkin(String skinUrl) throws IOException {
        this.skinImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        InputStream   inputStream = new URL(skinUrl).openStream();
        BufferedImage inputImage  = ImageIO.read(inputStream);
        if (inputImage != null) {
            Graphics g = skinImage.getGraphics();
            g.drawImage(inputImage, 0, 0, null);
            g.dispose();
        }
    }

    @Deprecated
    public void drawPart(BufferedImage part, int x, int y) {
        Graphics2D g = skinImage.createGraphics();
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(x, y, part.getWidth(), part.getHeight());
        g.setComposite(AlphaComposite.SrcOver);
        g.drawImage(part, x, y, null);
        g.dispose();
    }
}
