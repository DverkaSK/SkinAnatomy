package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.skin.SkinPartMetadata

@SkinPartMetadata(type = SkinPartType.LEFT_LEG, description = "Player left leg part")
class PlayerLeftLeg(skinUrl: String) : BaseSkinPart(
    skinUrl = skinUrl,
    type = SkinPartType.LEFT_LEG,
    sides = mapOf(
        SkinSide.FRONT to SkinCoordinates(20, 52, 4, 12),
        SkinSide.BACK to SkinCoordinates(28, 52, 4, 12),
        SkinSide.LEFT to SkinCoordinates(24, 52, 4, 12),
        SkinSide.RIGHT to SkinCoordinates(16, 52, 4, 12),
        SkinSide.TOP to SkinCoordinates(20, 48, 4, 4),
        SkinSide.BOTTOM to SkinCoordinates(24, 48, 4, 4)
    )
)