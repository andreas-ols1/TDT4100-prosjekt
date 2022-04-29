package PokemonShowdown;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {
    
    private Game game;

    @BeforeEach
    public void setup() {
        game = new Game();
    }


    @Test
    @DisplayName("Test gameEnded()")
    public void testGameEnded() {
        game.getOpponentTeam().stream().forEach(mon -> mon.setIsDead(true));
        assertTrue(game.gameEnded());
        game.getPlayerTeam().stream().forEach(mon -> mon.setIsDead(true));
        assertTrue(game.gameEnded());
        game.getOpponentTeam().get(0).setIsDead(false);
        assertTrue(game.gameEnded());
        game.getPlayerTeam().get(0).setIsDead(false);
        assertFalse(game.gameEnded());
    }

    @Test
    @DisplayName("Test setActiveOpponentMon()")
    public void testSetActiveOpponentMon() {
        game.setActiveOpponentMon();
        assertTrue(game.getOpponentTeam().contains(game.getActiveOpponentMon()));
        Pokemon activeOpponentMon = game.getActiveOpponentMon();
        game.getOpponentTeam().stream().forEach(mon -> mon.setIsDead(true));
        game.setActiveOpponentMon();
        assertEquals(activeOpponentMon.getName(), game.getActiveOpponentMon().getName());
    }

    @Test
    @DisplayName("Test constructor with input argument")
    public void testGameConstructorWithInputArgument() {
        game = new Game(Arrays.asList(new Pokemon("Charizard"),new Pokemon("Pikachu"),new Pokemon("Venusaur"),new Pokemon("Blastoise")));
        IntStream.range(0,4).forEach(i -> {
            assertEquals(Arrays.asList("Charizard","Pikachu","Venusaur","Blastoise").get(i),game.getPlayerTeam().get(i).getName());
        });
        assertEquals(game.getActiveMon(),null);
        
        game.getOpponentTeam().stream().forEach(mon -> assertTrue(Pokemon.validPokemon.contains(mon.getName())));
        Set<Pokemon> opponentTeamSet = new HashSet<>(game.getOpponentTeam());
        assertEquals(opponentTeamSet.size(),game.getOpponentTeam().size());
        assertTrue(game.getOpponentTeam().contains(game.getActiveOpponentMon()));
    }

    @Test
    @DisplayName("Test constructor without input argument")
    public void testGameConstructorWithoutInputArgument() {
        game.getOpponentTeam().stream().forEach(mon -> assertTrue(Pokemon.validPokemon.contains(mon.getName())));
        Set<Pokemon> opponentTeamSet = new HashSet<>(game.getOpponentTeam());
        assertEquals(opponentTeamSet.size(),game.getOpponentTeam().size());
        assertTrue(game.getOpponentTeam().contains(game.getActiveOpponentMon()));
        
        game.getPlayerTeam().stream().forEach(mon -> assertTrue(Pokemon.validPokemon.contains(mon.getName())));
        Set<Pokemon> playerTeamSet = new HashSet<>(game.getPlayerTeam());
        assertEquals(playerTeamSet.size(),game.getPlayerTeam().size());
        assertEquals(game.getActiveMon(),null);
    }

}
