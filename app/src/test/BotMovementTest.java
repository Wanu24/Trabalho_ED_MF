package trabalho_ed_mf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BotMovementTest {
    private Map map;
    private BotMovement botMovement;

    @BeforeEach
    void setUp() {
        map = new Map();
        botMovement = new BotMovement();
    }

    @Test
    void testShortestPath() {
        int result = botMovement.shortestPath(map, 1, 5);
        // Add your assertion here based on your expected result
    }

    @Test
    void testRandomPath() {
        int result = botMovement.randomPath(map, 1);
        // Add your assertion here based on your expected result
    }

    @Test
    void testAtheleticPath() {
        int result = botMovement.atheleticPath(map, 1, 2);
        // Add your assertion here based on your expected result
    }
}