package ru.dverkask.skinanatomy.api.skin

import ru.dverkask.skinanatomy.api.enums.SkinLayer
import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.image.ImageProcessor
import java.awt.Color
import java.awt.Rectangle
import java.awt.image.BufferedImage
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class OverlayLayerTest {

    private fun blankSkin(): BufferedImage = BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)

    private fun fill(color: Color) = object : ImageProcessor {
        override fun process(image: BufferedImage): BufferedImage {
            image.graphics.apply {
                this.color = color
                fillRect(0, 0, image.width, image.height)
                dispose()
            }
            return image
        }
    }

    @Test
    fun `overlay faces sit where the 64x64 layout puts them`() {
        val skin = PlayerSkin.fromImage(blankSkin())

        // One face per part is enough to pin the offset: every face shares it.
        assertEquals(SkinCoordinates(40, 8, 8, 8), skin.head.overlaySides[SkinSide.FRONT])
        assertEquals(SkinCoordinates(20, 36, 8, 12), skin.body.overlaySides[SkinSide.FRONT])
        assertEquals(SkinCoordinates(44, 36, 4, 12), skin.rightArm.overlaySides[SkinSide.FRONT])
        assertEquals(SkinCoordinates(52, 52, 4, 12), skin.leftArm.overlaySides[SkinSide.FRONT])
        assertEquals(SkinCoordinates(4, 36, 4, 12), skin.rightLeg.overlaySides[SkinSide.FRONT])
        assertEquals(SkinCoordinates(4, 52, 4, 12), skin.leftLeg.overlaySides[SkinSide.FRONT])
    }

    @Test
    fun `no two faces of either layer overlap, and all fit the canvas`() {
        val skin = PlayerSkin.fromImage(blankSkin())
        val faces = skin.parts.flatMap { part ->
            SkinLayer.entries.flatMap { layer ->
                part.sides(layer).map { (side, c) -> "${part.type}/$layer/$side" to Rectangle(c.x, c.y, c.width, c.height) }
            }
        }
        val canvas = Rectangle(0, 0, 64, 64)

        assertEquals(6 * 2 * 6, faces.size)
        faces.forEach { (name, rect) -> assertTrue(canvas.contains(rect), "$name $rect is outside the canvas") }
        faces.forEachIndexed { i, (a, ra) ->
            faces.drop(i + 1).forEach { (b, rb) ->
                if (ra.intersects(rb)) fail("$a $ra overlaps $b $rb")
            }
        }
    }

    @Test
    fun `copyPartFrom grafts the overlay along with the base by default`() {
        val source = PlayerSkin.fromImage(blankSkin())
        source.head.processImage(fill(Color.GREEN), SkinLayer.OVERLAY)

        val target = PlayerSkin.fromImage(blankSkin())
        target.head.processImage(fill(Color.RED), SkinLayer.OVERLAY)
        target.copyPartFrom(source, SkinPartType.HEAD)

        assertEquals(Color.GREEN.rgb, target.render().getRGB(40, 8)) // hat FRONT
        assertEquals(Color.GREEN.rgb, target.render().getRGB(40, 0)) // hat TOP
    }

    @Test
    fun `a transparent source overlay clears the target's one instead of blacking it out`() {
        val source = PlayerSkin.fromImage(blankSkin())

        val target = PlayerSkin.fromImage(blankSkin())
        target.head.processImage(fill(Color.RED), SkinLayer.OVERLAY)
        target.copyPartFrom(source, SkinPartType.HEAD)

        assertEquals(0, target.render().getRGB(40, 8) ushr 24, "hat FRONT must end up fully transparent")
    }

    @Test
    fun `copyPartFrom limited to the base layer leaves the target overlay alone`() {
        val source = PlayerSkin.fromImage(blankSkin())
        source.head.processImage(fill(Color.GREEN))
        source.head.processImage(fill(Color.GREEN), SkinLayer.OVERLAY)

        val target = PlayerSkin.fromImage(blankSkin())
        target.head.processImage(fill(Color.RED), SkinLayer.OVERLAY)
        target.copyPartFrom(source, SkinPartType.HEAD, setOf(SkinLayer.BASE))

        assertEquals(Color.GREEN.rgb, target.render().getRGB(8, 8)) // head FRONT replaced
        assertEquals(Color.RED.rgb, target.render().getRGB(40, 8)) // hat FRONT kept
    }

    @Test
    fun `processImage on the overlay does not touch the base layer`() {
        val skin = PlayerSkin.fromImage(blankSkin())
        skin.body.processImage(fill(Color.BLUE), SkinLayer.OVERLAY)

        assertEquals(Color.BLUE.rgb, skin.body.getImage(SkinSide.FRONT, SkinLayer.OVERLAY).getRGB(0, 0))
        assertEquals(0, skin.body.getImage(SkinSide.FRONT).getRGB(0, 0) ushr 24)
    }
}
