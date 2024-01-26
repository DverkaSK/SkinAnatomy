package ru.dverkask.skinanatomy.skin;

import org.bukkit.entity.Player;
import org.json.JSONObject;
import ru.dverkask.skinanatomy.SkinAnatomyPlugin;
import ru.dverkask.skinanatomy.api.ResultSkin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SkinLoader {
    private final static String token = "Bearer " + SkinAnatomyPlugin.getInstance().getConfig().getString("skinanatomy.imgurAccessToken");
    private static String upload(BufferedImage image) throws Exception {
        String boundary = Long.toHexString(System.currentTimeMillis());
        String CRLF     = "\r\n";

        URL               url        = new URL("https://api.imgur.com/3/image");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", token);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        var outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        try (OutputStream output = connection.getOutputStream();
             DataOutputStream writer = new DataOutputStream(output)) {

            writer.writeBytes("--" + boundary + CRLF);
            writer.writeBytes("Content-Disposition: form-data; name=\"image\"" + CRLF);
            writer.writeBytes("Content-Type: image/png" + CRLF);
            writer.writeBytes("Content-Transfer-Encoding: binary" + CRLF);
            writer.writeBytes(CRLF);
            writer.write(imageBytes);
            writer.writeBytes(CRLF);
            writer.writeBytes("--" + boundary + "--" + CRLF);

            InputStream responseStream = connection.getInputStream();
            byte[]      bytes          = responseStream.readAllBytes();

            String response = new String(bytes, StandardCharsets.UTF_8);

            return response;
        } finally {
            connection.disconnect();
        }
    }

    public static String getSkinURL(final ResultSkin resultSkin) throws Exception {
        JSONObject data = new JSONObject(upload(resultSkin.getSkinImage()))
                .getJSONObject("data");
        return data.getString("link");
    }
}
