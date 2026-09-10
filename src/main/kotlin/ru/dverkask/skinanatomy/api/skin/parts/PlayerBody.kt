package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.skin.SkinPartMetadata
import java.awt.image.BufferedImage

@SkinPartMetadata(type = SkinPartType.BODY, description = "Player body part")
class PlayerBody(canvas: BufferedImage) : BaseSkinPart(
    canvas = canvas,
    type = SkinPartType.BODY,
    sides = mapOf(
        SkinSide.FRONT to SkinCoordinates(20, 20, 8, 12),
        SkinSide.BACK to SkinCoordinates(32, 20, 8, 12),
        SkinSide.LEFT to SkinCoordinates(28, 20, 4, 12),
        SkinSide.RIGHT to SkinCoordinates(16, 20, 4, 12),
        SkinSide.TOP to SkinCoordinates(20, 16, 8, 4),
        SkinSide.BOTTOM to SkinCoordinates(28, 16, 8, 4)
    ),
    // Overlay layer (jacket): the base faces shifted by (0, 16).
    overlayDx = 0,
    overlayDy = 16
)