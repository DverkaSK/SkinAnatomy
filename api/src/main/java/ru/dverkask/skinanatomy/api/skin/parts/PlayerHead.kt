package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.skin.SkinPartMetadata

@SkinPartMetadata(type = SkinPartType.HEAD, description = "Player head part")
class PlayerHead(skinUrl: String) : BaseSkinPart(
    skinUrl = skinUrl,
    type = SkinPartType.HEAD,
    sides = mapOf(
        SkinSide.FRONT to SkinCoordinates(8, 8, 8, 8),
        SkinSide.BACK to SkinCoordinates(24, 8, 8, 8),
        SkinSide.LEFT to SkinCoordinates(16, 8, 8, 8),
        SkinSide.RIGHT to SkinCoordinates(0, 8, 8, 8),
        SkinSide.TOP to SkinCoordinates(8, 0, 8, 8),
        SkinSide.BOTTOM to SkinCoordinates(16, 0, 8, 8)
    )
)