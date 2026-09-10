package ru.dverkask.skinanatomy.api.skin

import ru.dverkask.skinanatomy.api.enums.SkinPartType
import ru.dverkask.skinanatomy.api.enums.SkinSide
import ru.dverkask.skinanatomy.api.image.ImageProcessor
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class PlayerSkinTest {

    private fun blankSkin(size: Int = 64): BufferedImage =
        BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB)

    private fun filledSkin(color: Color): BufferedImage = blankSkin().apply {
        graphics.apply {
            this.color = color
            fillRect(0, 0, width, height)
            dispose()
        }
    }

    @Test
    fun `processImage mutating the original image in place is reflected on render`() {
        val skin = PlayerSkin.fromImage(blankSkin())

        skin.head.processImage(object : ImageProcessor {
            override fun process(image: BufferedImage): BufferedImage {
                image.graphics.apply {
                    color = Color.RED
                    fillRect(0, 0, image.width, image.height)
                    dispose()
                }
                return image
            }
        })

        assertEquals(Color.RED.rgb, skin.render().getRGB(8, 8))
    }

    @Test
    fun `processImage returning a brand new image is still written back to the canvas`() {
        val skin = PlayerSkin.fromImage(blankSkin())

        skin.head.processImage(object : ImageProcessor {
            override fun process(image: BufferedImage): BufferedImage {
                val replacement = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_ARGB)
                replacement.graphics.apply {
                    color = Color.BLUE
                    fillRect(0, 0, image.width, image.height)
                    dispose()
                }
                return replacement
            }
        })

        assertEquals(Color.BLUE.rgb, skin.render().getRGB(8, 8))
    }

    @Test
    fun `copyPartFrom grafts every side of a part from another skin`() {
        val source = PlayerSkin.fromImage(blankSkin())
        source.head.processImage(object : ImageProcessor {
            override fun process(image: BufferedImage): BufferedImage {
                image.graphics.apply {
                    color = Color.GREEN
                    fillRect(0, 0, image.width, image.height)
                    dispose()
                }
                return image
            }
        })

        val target = PlayerSkin.fromImage(blankSkin())
        target.copyPartFrom(source, SkinPartType.HEAD)

        assertEquals(Color.GREEN.rgb, target.render().getRGB(8, 8)) // head FRONT
        assertEquals(Color.GREEN.rgb, target.render().getRGB(8, 0)) // head TOP
        assertNotEquals(Color.GREEN.rgb, target.render().getRGB(20, 20)) // body FRONT untouched
    }

    @Test
    fun `copying a transparent region leaves it transparent, not black`() {
        val source = PlayerSkin.fromImage(blankSkin())
        val target = PlayerSkin.fromImage(filledSkin(Color.RED))

        target.copyPartFrom(source, SkinPartType.HEAD)

        assertEquals(0, target.render().getRGB(8, 8) ushr 24, "head FRONT must end up fully transparent")
    }

    @Test
    fun `processImage returning a transparent image leaves the region transparent, not black`() {
        val skin = PlayerSkin.fromImage(filledSkin(Color.RED))

        skin.head.processImage(object : ImageProcessor {
            override fun process(image: BufferedImage): BufferedImage =
                BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_ARGB)
        })

        assertEquals(0, skin.render().getRGB(8, 8) ushr 24, "head FRONT must end up fully transparent")
    }

    @Test
    fun `legacy 64x32 skins are upgraded to 64x64 with mirrored left limbs`() {
        val legacy = BufferedImage(64, 32, BufferedImage.TYPE_INT_ARGB)
        legacy.graphics.apply {
            color = Color.YELLOW
            fillRect(40, 16, 16, 16) // right arm UV box
            dispose()
        }

        val skin = PlayerSkin.fromImage(legacy)
        val rendered = skin.render()

        assertEquals(64, rendered.height)
        // The mirrored left-arm FRONT face should now carry the right arm's color.
        assertEquals(Color.YELLOW.rgb, skin.leftArm.getImage(SkinSide.FRONT).getRGB(0, 0))
    }
}
