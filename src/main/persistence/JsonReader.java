package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// code adapted from the workroom sample application

// Represents a reader that reads trainer information from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads trainer information from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Trainer read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrainer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses trainer from JSON object and returns it
    private Trainer parseTrainer(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Trainer tr = new Trainer(name);
        addAllPokemon(tr, jsonObject);
        return tr;
    }

    // MODIFIES: tr
    // EFFECTS: parses all Pokemon from JSON object and adds them to workroom
    private void addAllPokemon(Trainer tr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allPokemon");
        for (Object json : jsonArray) {
            JSONObject nextPokemon = (JSONObject) json;
            addOnePokemon(tr, nextPokemon);
        }
    }

    // MODIFIES: tr
    // EFFECTS: parses a Pokemon from JSON object and adds it to trainer's ranch
    private void addOnePokemon(Trainer tr, JSONObject jsonObject) {
        Species species = Species.valueOf(jsonObject.getString("category"));
        Pokemon p = new Pokemon(species);
        tr.addPokemonToRanch(p);
    }
}
