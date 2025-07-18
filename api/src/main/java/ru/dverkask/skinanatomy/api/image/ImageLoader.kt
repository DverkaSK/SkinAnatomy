package ru.dverkask.skinanatomy.api.image

import java.io.IOException
import java.net.URL
import javax.imageio.ImageIO

object ImageLoader {
    fun loadImage(url: String): ImageLoadResult {
        return try {
            URL(url).openStream().use { stream ->
                val image = ImageIO.read(stream)
                if (image != null) {
                    ImageLoadResult.Success(image)
                } else {
                    ImageLoadResult.Error(IOException("Failed to load image: null result"))
                }
            }
        } catch (e: Exception) {
            ImageLoadResult.Error(e)
        }
    }
}