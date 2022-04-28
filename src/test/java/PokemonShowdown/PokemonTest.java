package PokemonShowdown;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PokemonTest {
    
    private Pokemon mon;


    @BeforeEach
    public void setup() {
        mon = new Pokemon("Charizard");
    }
    
    @Test
    @DisplayName("Test constructor")
    public void testConstructor() {
        assertEquals("Charizard",mon.getName());
        assertEquals(328, mon.getHp());
        assertEquals(285, mon.getAttack());
        assertEquals(237, mon.getDefence());
        assertEquals(267, mon.getSpeed());
        IntStream.range(0,4).forEach(i -> {
            assertEquals(Arrays.asList("Flamethrower","Earthquake","Swords Dance","Air Slash").get(i), mon.getMove(i).getName());
        });
    }

}
