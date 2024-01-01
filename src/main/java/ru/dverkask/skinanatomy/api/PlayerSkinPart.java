package ru.dverkask.skinanatomy.api;

import lombok.Getter;
import lombok.Setter;
import ru.dverkask.skinanatomy.utils.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Getter
@Setter
public abstract class PlayerSkinPart {
    protected BufferedImage skinPart;
    public PlayerSkinPart(final String skinUrl, int x, int y, int w, int h) throws IOException {
        BufferedImage skin = ImageLoader.loadImage(skinUrl);
        this.skinPart = skin.getSubimage(x, y, w, h);
    }

    protected SkinPartData createSkinPartData(int x, int y, int width, int height) {
        return new SkinPartData(
                getSkinPart().getSubimage(x, y, width, height),
                x, y
        );
    }
}
