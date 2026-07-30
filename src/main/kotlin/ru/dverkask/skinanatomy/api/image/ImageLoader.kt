package ru.dverkask.skinanatomy.api.image

import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import javax.imageio.ImageIO

/**
 * Loads skin textures over HTTP.
 *
 * Uses [java.net.http.HttpClient] instead of the legacy `URL#openStream`
 * so redirects and timeouts are handled explicitly rather than relying on defaults.
 */
object ImageLoader {

    private val client: HttpClient = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(10))
        .build()

    fun loadImage(url: String): ImageLoadResult {
        return try {
            val request = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofInputStream())
            if (response.statusCode() !in 200..299) {
                return ImageLoadResult.Error(
                    IOException("Failed to load image: HTTP ${response.statusCode()} from $url")
                )
            }

            val image = response.body().use { ImageIO.read(it) }
            if (image != null) {
                ImageLoadResult.Success(image)
            } else {
                ImageLoadResult.Error(IOException("Failed to decode image from $url: unsupported or corrupt data"))
            }
        } catch (e: Exception) {
            ImageLoadResult.Error(e)
        }
    }
}
