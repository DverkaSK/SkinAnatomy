package ru.dverkask.skinanatomy.api.skin.parts

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.skin.SkinCoordinates
import ru.dverkask.skinanatomy.api.skin.SkinPartMetadata

@SkinPartMetadata(type = SkinPartType.RIGHT_ARM, description = "Player right arm part")
class PlayerRightArm(skinUrl: String) : BaseSkinPart(
    skinUrl = skinUrl,
    type = SkinPartType.RIGHT_ARM,
    sides = mapOf(
        SkinSide.FRONT to SkinCoordinates(44, 20, 4, 12),
        SkinSide.BACK to SkinCoordinates(52, 20, 4, 12),
        SkinSide.LEFT to SkinCoordinates(48, 20, 4, 12),
        SkinSide.RIGHT to SkinCoordinates(40, 20, 4, 12),
        SkinSide.TOP to SkinCoordinates(44, 16, 4, 4),
        SkinSide.BOTTOM to SkinCoordinates(48, 16, 4, 4)
    )
)