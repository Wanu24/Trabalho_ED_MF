package trabalho_ed_mf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BotTest {
    private Bot bot;
    private Map map;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        player1 = new Player("Player1", new Flag());
        player2 = new Player("Player2", new Flag());
        map = new Map();
        bot = new Bot(player1, MovementEnum.SHORTESTPATH);
    }

    @Test
    void testMove() {
        bot.move(map, player2);
        // Add your assertion here based on your expected result
    }

    @Test
    void testGetIndex() {
        int index = bot.getIndex();
        assertEquals(player1.getFlag().getIndex(), index);
    }

    @Test
    void testSetIndex() {
        bot.setIndex(5);
        assertEquals(5, bot.getIndex());
    }

    @Test
    void testSetMov() {
        bot.setMov(MovementEnum.RANDOMPATH);
        assertEquals(MovementEnum.RANDOMPATH, bot.getMovEnum());
    }

    @Test
    void testGetMovEnum() {
        assertEquals(MovementEnum.SHORTESTPATH, bot.getMovEnum());
    }
}