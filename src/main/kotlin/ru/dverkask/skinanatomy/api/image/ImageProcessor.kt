package ru.dverkask.skinanatomy.api.image

import java.awt.image.BufferedImage

fun interface ImageProcessor {
    fun process(image: BufferedImage): BufferedImage
}