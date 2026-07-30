package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.skin.SkinPartMetadata
import java.awt.image.BufferedImage

@SkinPartMetadata(type = SkinPartType.LEFT_ARM, description = "Player left arm part")
class PlayerLeftArm(canvas: BufferedImage) : BaseSkinPart(
    canvas = canvas,
    type = SkinPartType.LEFT_ARM,
    sides = mapOf(
        SkinSide.FRONT to SkinCoordinates(36, 52, 4, 12),
        SkinSide.BACK to SkinCoordinates(44, 52, 4, 12),
        SkinSide.LEFT to SkinCoordinates(40, 52, 4, 12),
        SkinSide.RIGHT to SkinCoordinates(32, 52, 4, 12),
        SkinSide.TOP to SkinCoordinates(36, 48, 4, 4),
        SkinSide.BOTTOM to SkinCoordinates(40, 48, 4, 4)
    )
)