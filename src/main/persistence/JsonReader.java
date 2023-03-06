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
        Trainer.Gender gender = Trainer.Gender.valueOf(jsonObject.getString("gender"));
        Trainer tr = new Trainer(name, gender);
        addAllPokemonToRanch(tr, jsonObject);
        addAllTeams(tr, jsonObject);
        return tr;
    }

    // MODIFIES: tr
    // EFFECTS: parses all Pokemon from JSON object and adds them to trainer's ranch
    private void addAllPokemonToRanch(Trainer tr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allRanchPokemon");
        for (Object json : jsonArray) {
            JSONObject nextPokemon = (JSONObject) json;
            tr.addPokemonToRanch(getPokemonToAdd(nextPokemon));
        }
    }

    // EFFECTS: returns a parsed a Pokemon from JSON object
    private Pokemon getPokemonToAdd(JSONObject jsonObject) {
        Species species = Species.valueOf(jsonObject.getString("species"));
        String nickname = jsonObject.getString("nickname");
        boolean shiny = jsonObject.getBoolean("shiny");
        Pokemon.Gender gender = Pokemon.Gender.valueOf(jsonObject.getString("gender"));
        return new Pokemon(species, nickname, shiny, gender);
    }

    // MODIFIES: tr
    // EFFECTS: parses all teams from JSON object and adds them to trainers list of teams
    private void addAllTeams(Trainer tr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allTeams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            tr.addTeam(getTeamToAdd(nextTeam));

        }
    }

    // EFFECTS: returns a parsed team from JSON object
    private Team getTeamToAdd(JSONObject jsonObject) {
        String teamName = jsonObject.getString("teamName");
        Team te = new Team(teamName);
        addAllPokemonToTeam(te, jsonObject);
        return te;
    }

    // MODIFIES: te
    // EFFECTS: parses all Pokemon from JSON object and adds them to te
    private void addAllPokemonToTeam(Team te, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allTeamPokemon");
        for (Object json : jsonArray) {
            JSONObject nextPokemon = (JSONObject) json;
            te.addPokemon(getPokemonToAdd(nextPokemon));
        }
    }
}
