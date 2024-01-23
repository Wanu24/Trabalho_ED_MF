package trabalho_ed_mf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private Flag flag;
    private Bot bot;

    @BeforeEach
    void setUp() {
        flag = new Flag();
        bot = new Bot(new Player("BotOwner", new Flag()), MovementEnum.SHORTESTPATH);
        player = new Player("Player1", flag);
    }

    @Test
    void testName() {
        player.setName("NewName");
        assertEquals("NewName", player.getName());
    }

    @Test
    void testFlag() {
        Flag newFlag = new Flag();
        player.setFlag(newFlag);
        assertEquals(newFlag, player.getFlag());
    }

    @Test
    void testAddBot() {
        player.addBot(bot);
        assertEquals(bot, player.getBots().getRear().getElement());
    }

    @Test
    void testRemoveBot() {
        player.addBot(bot);
        player.removeBot(bot);
        assertNull(player.getBots().getRear());
    }

    @Test
    void testPlayerColour() {
        flag.setColour(PlayerColour.WHITE);
        assertEquals(PlayerColour.WHITE, player.getPlayerColour());
    }
}