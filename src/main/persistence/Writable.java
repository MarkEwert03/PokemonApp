package persistence;

import org.json.JSONObject;

// code adapted from the workroom sample application

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
