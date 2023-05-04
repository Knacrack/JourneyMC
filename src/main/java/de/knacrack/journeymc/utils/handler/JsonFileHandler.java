package de.knacrack.journeymc.utils.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFileHandler {

    private Gson gson;

    public JsonFileHandler() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void writeToJsonFile(Object obj, Path path) throws IOException {
        String json = gson.toJson(obj);
        Files.writeString(path, json);
    }

    public <T> T readFromJsonFile(Path path, Class<T> clazz) throws IOException, JsonSyntaxException {
        String json = Files.readString(path);
        return gson.fromJson(json, clazz);
    }
}
