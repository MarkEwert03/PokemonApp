package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private static final int HASH_CONSTANT = 13;
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Changed Pokemon nickname from bob to fred");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Changed Pokemon nickname from bob to fred", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Changed Pokemon nickname from bob to fred", e.toString());
    }

    @Test
    public void testEquals() {
        assertFalse(e.equals(null));
        assertNotEquals(e, "Changed Pokemon nickname from bob to fred");
        assertEquals(HASH_CONSTANT * e.getDate().hashCode() + e.getDescription().hashCode(),e.hashCode());
    }
}
