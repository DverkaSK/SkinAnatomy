package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.image.ImageProcessor
import java.awt.image.BufferedImage

/**
 * Base implementation of [ISkinPart] backed by a region of a shared 64x64 skin canvas.
 *
 * [canvas] is shared between every part of the same [ru.dverkask.skinanatomy.api.skin.PlayerSkin],
 * so edits made through one part are immediately visible to the others and to the final render.
 */
abstract class BaseSkinPart(
    protected val canvas: BufferedImage,
    override val type: SkinPartType,
    override val sides: Map<SkinSide, SkinCoordinates>
) : ISkinPart {

    override fun getImage(side: SkinSide): BufferedImage {
        val coords = sides[side] ?: throw IllegalArgumentException("Side $side not found for ${type.name}")
        return canvas.getSubimage(coords.x, coords.y, coords.width, coords.height)
    }

    override fun processImage(processor: ImageProcessor): ISkinPart = apply {
        sides.forEach { (side, coords) ->
            val original = getImage(side)
            val processed = processor.process(original)

            // Processors are free to mutate `original` in place (it's a live view backed by
            // the shared canvas) or return a brand-new BufferedImage. Either way, make sure
            // the result actually lands back on the canvas.
            if (processed !== original) {
                val graphics = canvas.createGraphics()
                try {
                    graphics.clearRect(coords.x, coords.y, coords.width, coords.height)
                    graphics.drawImage(processed, coords.x, coords.y, coords.width, coords.height, null)
                } finally {
                    graphics.dispose()
                }
            }
        }
    }
}
