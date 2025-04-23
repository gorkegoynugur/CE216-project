package com.example.ce216_project.util;

import com.example.ce216_project.model.Artifact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ArtifactJsonHandler {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Artifact> loadArtifacts(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        Type listType = new TypeToken<List<Artifact>>() {}.getType();
        return gson.fromJson(reader, listType);
    }

    public static void saveArtifacts(String filePath, List<Artifact> artifacts) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        gson.toJson(artifacts, writer);
        writer.close();
    }
}