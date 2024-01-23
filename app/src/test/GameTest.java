package trabalho_ed_mf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        game = new Game();
        player1 = new Player("Player1", new Flag());
        player2 = new Player("Player2", new Flag());
        game.getPlayers().add(player1);
        game.getPlayers().add(player2);
    }

    @Test
    void testUseTurn() {
        int result = game.useTurn(0);
        assertEquals(0, result, "Expected useTurn to return 0 when the game continues");
    }

    @Test
    void testGetPlayers() {
        LinkedList<Player> players = game.getPlayers();
        assertEquals(2, players.size(), "Expected 2 players in the game");
        assertEquals(player1, players.get(0), "Expected the first player to be player1");
        assertEquals(player2, players.get(1), "Expected the second player to be player2");
    }

    @Test
    void testCreatePlayer() {
        game.createPlayer("Player3", "RED");
        assertEquals(3, game.getPlayers().size(), "Expected 3 players in the game after creating a new player");
        assertEquals("Player3", game.getPlayers().get(2).getName(), "Expected the third player to be Player3");
    }

    @Test
    void testGetMap() {
        Map map = game.getMap();
        assertNotNull(map, "Expected getMap to return a non-null Map object");
    }
}