package ru.dverkask.skinanatomy.skin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class SkinManager {
    public static JsonObject getSkinByNickname(final String nickname) throws IOException {
        try {
            URL           api           = new URL("https://api.mojang.com/users/profiles/minecraft/" + nickname);
            URLConnection apiConnection = api.openConnection();

            JsonObject apiResponse = new Gson().fromJson(
                    new JsonReader(
                            new InputStreamReader(apiConnection.getInputStream())
                    ),
                    JsonObject.class
            );
            String uuid = apiResponse.get("id").getAsString();

            URL           session           = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            URLConnection sessionConnection = session.openConnection();

            JsonObject sessionResponse = new Gson().fromJson(
                    new JsonReader(
                            new InputStreamReader(sessionConnection.getInputStream())
                    ),
                    JsonObject.class
            );
            JsonObject properties = sessionResponse.get("properties")
                    .getAsJsonArray()
                    .get(0)
                    .getAsJsonObject();
            String texture = properties.get("value").getAsString();

            return JsonParser.parseString(
                    new String(Base64.getDecoder().decode(texture))
            ).getAsJsonObject();
        } catch (IOException e) {
            return null;
        }
    }
}