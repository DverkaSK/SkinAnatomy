package ru.dverkask.skinanatomy.api.image

import java.awt.image.BufferedImage

sealed class ImageLoadResult {
    data class Success(val image: BufferedImage) : ImageLoadResult()
    data class Error(val exception: Exception) : ImageLoadResult()
}