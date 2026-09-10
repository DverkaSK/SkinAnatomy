package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinLayer
import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.image.ImageProcessor
import java.awt.image.BufferedImage

sealed interface ISkinPart {
    val type: SkinPartType

    /** Faces of the base layer. */
    val sides: Map<SkinSide, SkinCoordinates>

    /** Faces of the overlay layer - hat, jacket, sleeves, pants. */
    val overlaySides: Map<SkinSide, SkinCoordinates>

    fun sides(layer: SkinLayer): Map<SkinSide, SkinCoordinates>

    /** A face of the base layer. */
    fun getImage(side: SkinSide): BufferedImage
    fun getImage(side: SkinSide, layer: SkinLayer): BufferedImage

    /** Runs [processor] over every face of the base layer. */
    fun processImage(processor: ImageProcessor): ISkinPart
    fun processImage(processor: ImageProcessor, layer: SkinLayer): ISkinPart
}
