package persistence;

import org.json.JSONObject;

// code adapted from the workroom sample application

// a general interface that represents a writable object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
