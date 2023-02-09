package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamTest {
    Team emptyTeam;
    Team singleTeam;
    Team multipleTeam;
    Team fullTeam;

    @BeforeEach
    void setup() {
        emptyTeam = new Team("empty");
        singleTeam = new Team("single");
    }

    @Test
    void testEmptyTeamLength() {
        //stub
    }
}