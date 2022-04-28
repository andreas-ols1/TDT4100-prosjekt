package PokemonShowdown;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoveTest {

    private Move move; 

    @BeforeEach
    public void setup() {
        move = new Move("Slash");
    }
    

    @Test
    @DisplayName("Test constructor")
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Move("Hyper Beam"),
        "Cannot instantiate a move which is not implemented");
        assertThrows(IllegalArgumentException.class, () -> new Move("SOLAR BEAM"),
         "Test case sensitivity");
        assertEquals("Slash", move.getName());
        assertEquals(70, move.getDamage());
        assertEquals(1, move.getAccuracy());
        assertEquals(0, move.getHeal());
        assertEquals(0, move.getAttackBoost());
        assertEquals(0, move.getSpeedBoost());
    }

}
