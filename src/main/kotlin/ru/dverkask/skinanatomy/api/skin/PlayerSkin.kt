package ru.dverkask.skinanatomy.api.skin

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.image.ImageLoadResult
import ru.dverkask.skinanatomy.api.image.ImageLoader
import ru.dverkask.skinanatomy.api.skin.parts.ISkinPart
import ru.dverkask.skinanatomy.api.skin.parts.PlayerBody
import ru.dverkask.skinanatomy.api.skin.parts.PlayerHead
import ru.dverkask.skinanatomy.api.skin.parts.PlayerLeftArm
import ru.dverkask.skinanatomy.api.skin.parts.PlayerLeftLeg
import ru.dverkask.skinanatomy.api.skin.parts.PlayerRightArm
import ru.dverkask.skinanatomy.api.skin.parts.PlayerRightLeg
import java.awt.image.BufferedImage

/**
 * A full 64x64 player skin texture together with the six anatomical parts that make it up.
 *
 * Every part shares the same underlying canvas, so editing one part through
 * [ISkinPart.processImage] is immediately reflected in [render] and in every other part -
 * there is no separate "compose" step.
 */
class PlayerSkin private constructor(private val canvas: BufferedImage) {

    val head: PlayerHead = PlayerHead(canvas)
    val body: PlayerBody = PlayerBody(canvas)
    val leftArm: PlayerLeftArm = PlayerLeftArm(canvas)
    val rightArm: PlayerRightArm = PlayerRightArm(canvas)
    val leftLeg: PlayerLeftLeg = PlayerLeftLeg(canvas)
    val rightLeg: PlayerRightLeg = PlayerRightLeg(canvas)

    /** All six anatomical parts, in a stable order. */
    val parts: List<ISkinPart> = listOf(head, body, leftArm, rightArm, leftLeg, rightLeg)

    fun part(type: SkinPartType): ISkinPart = parts.first { it.type == type }

    /**
     * Copies every side of [type] from [source] onto this skin, replacing it in place.
     * Used to graft a part (head, body, an arm, a leg...) from one skin onto another.
     */
    fun copyPartFrom(source: PlayerSkin, type: SkinPartType): PlayerSkin = apply {
        val sourcePart = source.part(type)
        val targetPart = part(type)

        val graphics = canvas.createGraphics()
        try {
            targetPart.sides.forEach { (side, coords) ->
                graphics.clearRect(coords.x, coords.y, coords.width, coords.height)
                graphics.drawImage(sourcePart.getImage(side), coords.x, coords.y, null)
            }
        } finally {
            graphics.dispose()
        }
    }

    /** A defensive copy of the current texture, ready to be uploaded or applied to a player. */
    fun render(): BufferedImage {
        val copy = BufferedImage(canvas.width, canvas.height, BufferedImage.TYPE_INT_ARGB)
        val graphics = copy.createGraphics()
        try {
            graphics.drawImage(canvas, 0, 0, null)
        } finally {
            graphics.dispose()
        }
        return copy
    }

    companion object {
        private const val SKIN_SIZE = 64
        private const val LIMB_BOX = 16

        fun fromUrl(url: String): PlayerSkin = when (val result = ImageLoader.loadImage(url)) {
            is ImageLoadResult.Success -> fromImage(result.image)
            is ImageLoadResult.Error -> throw result.exception
        }

        fun fromImage(source: BufferedImage): PlayerSkin = PlayerSkin(normalize(source))

        /**
         * Upgrades a legacy 64x32 skin (pre-1.8: no overlay layer, no dedicated left arm/leg
         * regions) to the modern 64x64 layout by mirroring the right arm/leg UV boxes into the
         * left ones, the same way vanilla clients render legacy skins on modern models.
         */
        private fun normalize(source: BufferedImage): BufferedImage {
            val canvas = BufferedImage(SKIN_SIZE, SKIN_SIZE, BufferedImage.TYPE_INT_ARGB)
            val graphics = canvas.createGraphics()
            try {
                graphics.drawImage(source, 0, 0, null)
            } finally {
                graphics.dispose()
            }

            if (source.height <= 32) {
                mirrorLimbBox(canvas, srcX = 40, srcY = 16, dstX = 32, dstY = 48) // right arm -> left arm
                mirrorLimbBox(canvas, srcX = 0, srcY = 16, dstX = 16, dstY = 48)  // right leg -> left leg
            }

            return canvas
        }

        private fun mirrorLimbBox(canvas: BufferedImage, srcX: Int, srcY: Int, dstX: Int, dstY: Int) {
            val source = canvas.getSubimage(srcX, srcY, LIMB_BOX, LIMB_BOX)
            val flipped = BufferedImage(LIMB_BOX, LIMB_BOX, BufferedImage.TYPE_INT_ARGB)

            val flipGraphics = flipped.createGraphics()
            try {
                flipGraphics.drawImage(source, LIMB_BOX, 0, -LIMB_BOX, LIMB_BOX, null)
            } finally {
                flipGraphics.dispose()
            }

            val destGraphics = canvas.createGraphics()
            try {
                destGraphics.drawImage(flipped, dstX, dstY, null)
            } finally {
                destGraphics.dispose()
            }
        }
    }
}
