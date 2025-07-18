package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.image.ImageLoadResult
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.image.ImageLoader
import ru.dverkask.skinanatomy.api.image.ImageProcessor
import java.awt.image.BufferedImage

abstract class BaseSkinPart(
    private val skinUrl: String,
    override val type: SkinPartType,
    override val sides: Map<SkinSide, SkinCoordinates>
) : ISkinPart {
    protected val image: BufferedImage by lazy {
        when (val result = ImageLoader.loadImage(skinUrl)) {
            is ImageLoadResult.Success -> result.image
            is ImageLoadResult.Error -> throw result.exception
        }
    }

    override fun getImage(side: SkinSide): BufferedImage =
        sides[side]?.let { coords ->
            image.getSubimage(coords.x, coords.y, coords.width, coords.height)
        } ?: throw IllegalArgumentException("Side $side not found for ${type.name}")

    override fun processImage(processor: ImageProcessor): ISkinPart =
        apply { sides.keys.forEach { processor.process(getImage(it)) } }
}