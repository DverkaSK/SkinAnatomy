package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinLayer
import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.image.ImageProcessor
import java.awt.AlphaComposite
import java.awt.image.BufferedImage

/**
 * Base implementation of [ISkinPart] backed by a region of a shared 64x64 skin canvas.
 *
 * [canvas] is shared between every part of the same [ru.dverkask.skinanatomy.api.skin.PlayerSkin],
 * so edits made through one part are immediately visible to the others and to the final render.
 *
 * In the 64x64 layout every part's overlay faces are its base faces shifted by one fixed
 * offset, so a part declares that offset ([overlayDx], [overlayDy]) instead of six more boxes.
 */
abstract class BaseSkinPart(
    protected val canvas: BufferedImage,
    override val type: SkinPartType,
    override val sides: Map<SkinSide, SkinCoordinates>,
    overlayDx: Int,
    overlayDy: Int
) : ISkinPart {

    override val overlaySides: Map<SkinSide, SkinCoordinates> =
        sides.mapValues { (_, coords) -> coords.copy(x = coords.x + overlayDx, y = coords.y + overlayDy) }

    override fun sides(layer: SkinLayer): Map<SkinSide, SkinCoordinates> = when (layer) {
        SkinLayer.BASE -> sides
        SkinLayer.OVERLAY -> overlaySides
    }

    override fun getImage(side: SkinSide): BufferedImage = getImage(side, SkinLayer.BASE)

    override fun getImage(side: SkinSide, layer: SkinLayer): BufferedImage {
        val coords = sides(layer)[side] ?: throw IllegalArgumentException("Side $side not found for ${type.name}")
        return canvas.getSubimage(coords.x, coords.y, coords.width, coords.height)
    }

    override fun processImage(processor: ImageProcessor): ISkinPart = processImage(processor, SkinLayer.BASE)

    override fun processImage(processor: ImageProcessor, layer: SkinLayer): ISkinPart = apply {
        sides(layer).forEach { (side, coords) ->
            val original = getImage(side, layer)
            val processed = processor.process(original)

            // Processors are free to mutate `original` in place (it's a live view backed by
            // the shared canvas) or return a brand-new BufferedImage. Either way, make sure
            // the result actually lands back on the canvas.
            if (processed !== original) {
                val graphics = canvas.createGraphics()
                try {
                    // Src, not clearRect: clearRect fills with opaque black, not transparency.
                    graphics.composite = AlphaComposite.Src
                    graphics.drawImage(processed, coords.x, coords.y, coords.width, coords.height, null)
                } finally {
                    graphics.dispose()
                }
            }
        }
    }
}
