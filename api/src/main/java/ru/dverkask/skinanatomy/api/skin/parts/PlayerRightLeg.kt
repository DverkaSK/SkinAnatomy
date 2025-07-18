package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.skin.SkinPartMetadata

@SkinPartMetadata(type = SkinPartType.RIGHT_LEG, description = "Player right leg part")
class PlayerRightLeg(skinUrl: String) : BaseSkinPart(
    skinUrl = skinUrl,
    type = SkinPartType.RIGHT_LEG,
    sides = mapOf(
        SkinSide.FRONT to SkinCoordinates(4, 20, 4, 12),
        SkinSide.BACK to SkinCoordinates(12, 20, 4, 12),
        SkinSide.LEFT to SkinCoordinates(8, 20, 4, 12),
        SkinSide.RIGHT to SkinCoordinates(0, 20, 4, 12),
        SkinSide.TOP to SkinCoordinates(4, 16, 4, 4),
        SkinSide.BOTTOM to SkinCoordinates(8, 16, 4, 4)
    )
)