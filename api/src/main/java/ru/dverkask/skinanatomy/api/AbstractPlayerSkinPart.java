package ru.dverkask.skinanatomy.api;

import lombok.Getter;
import lombok.Setter;
import ru.dverkask.skinanatomy.utils.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Getter
@Setter
public abstract class AbstractPlayerSkinPart {
    protected SkinPartData skinPart;
    public AbstractPlayerSkinPart(final String skinUrl, int x, int y, int w, int h) throws IOException {
        BufferedImage skin = ImageLoader.loadImage(skinUrl);
        this.skinPart = new SkinPartData(
                skin.getSubimage(x, y, w, h),
                x, y
        );
    }

    protected SkinPartData createSkinPartData(int x, int y, int width, int height) {
        return new SkinPartData(
                getSkinPart().getImage().getSubimage(x, y, width, height),
                x, y
        );
    }
}
