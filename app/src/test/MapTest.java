package trabalho_ed_mf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    private Map map;

    @BeforeEach
    void setUp() {
        map = new Map();
    }

    @Test
    void testSize() {
        map.setSize(5);
        assertEquals(5, map.getSize());
    }

    @Test
    void testAddLocal() {
        Location location = new Location(1);
        map.addLocal(location);
        assertEquals(location, map.getLocations()[0]);
    }

    @Test
    void testRemoveLocal() {
        Location location = new Location(1);
        map.addLocal(location);
        map.removeLocal(location);
        assertEquals(0, map.getLocations().length);
    }

    @Test
    void testAddFlag() {
        Flag flag = new Flag();
        map.addFlag(1, flag);
        assertTrue(map.getLocations()[0].getHasFlag());
    }

    @Test
    void testRemoveFlag() {
        Flag flag = new Flag();
        map.addFlag(1, flag);
        map.removeFlag(1);
        assertFalse(map.getLocations()[0].getHasFlag());
    }
}