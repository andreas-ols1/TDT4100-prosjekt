package PokemonShowdown;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TypeTest {
    
    private Type type;


    @BeforeEach
    public void setup() {
        type = new Type("Ground");
    }

    @Test
    @DisplayName("Test constructor")
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Type("Halaisiken"),
        "Test if exception is thrown when instantiating a nonexistent type");
        assertEquals("Fighting",new Type("Fighting").getName());
    }

    @Test
    @DisplayName("Test strength")
    public void testCheckIfStrongAgainst() {
        assertTrue(type.checkIfStrongAgainst(new Type("Fire")));
        assertFalse(type.checkIfStrongAgainst(new Type("Water")));
    }

    @Test
    @DisplayName("Test weakness")
    public void testCheckIfWeakAgainst() {
        assertTrue(type.checkIfWeakAgainst(new Type("Grass")));
        assertFalse(type.checkIfWeakAgainst(new Type("Ghost")));
    }

    @Test
    @DisplayName("Test immunity")
    public void testCheckIfImmune() {
        assertTrue(type.checkIfImmune(new Type("Flying")));
        assertFalse(type.checkIfImmune(new Type("Ghost")));
    }

    


}
