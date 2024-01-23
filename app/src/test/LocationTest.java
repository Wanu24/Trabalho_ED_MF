package trabalho_ed_mf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location(1);
    }

    @Test
    void testHasFlag() {
        location.setHasFlag(true);
        assertTrue(location.getHasFlag());
    }

    @Test
    void testHasBot() {
        location.setHasBot(true);
        assertTrue(location.getHasBot());
    }

    @Test
    void testFlag() {
        Flag flag = new Flag();
        location.setFlag(flag);
        assertEquals(flag, location.getFlag());
    }

    @Test
    void testIndex() {
        location.setIndex(5);
        assertEquals(5, location.getIndex());
    }
}