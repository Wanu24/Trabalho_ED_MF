package trabalho_ed_mf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlagTest {
    private Flag flag;

    @BeforeEach
    void setUp() {
        flag = new Flag();
    }

    @Test
    void testColour() {
        flag.setColour(PlayerColour.WHITE);
        assertEquals(PlayerColour.WHITE, flag.getColour());
    }

    @Test
    void testIndex() {
        flag.setIndex(5);
        assertEquals(5, flag.getIndex());
    }
}