package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.image.ImageProcessor
import java.awt.image.BufferedImage

sealed interface ISkinPart {
    val type: SkinPartType
    val sides: Map<SkinSide, SkinCoordinates>
    fun getImage(side: SkinSide): BufferedImage
    fun processImage(processor: ImageProcessor): ISkinPart
}