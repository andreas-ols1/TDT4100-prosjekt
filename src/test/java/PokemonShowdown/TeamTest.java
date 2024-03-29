package PokemonShowdown;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TeamTest {
    
	private Team getTeam() throws IOException {
        return new Team("test", Arrays.asList(
        new Pokemon("Snorlax"),
        new Pokemon("Gyarados"),
        new Pokemon("Slowbro"),
        new Pokemon("Pikachu")));
    }

    private String teamAsString() {
        return "Snorlax\r\nGyarados\r\nSlowbro\r\nPikachu\r\n";
    }

    @Test
    @DisplayName("Test write team to file")
    public void testWrite() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Team team = getTeam();
        team.write(os);

        String actual = new String(os.toByteArray());
        String expected = teamAsString();

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Test read team from file")
    public void testRead() throws IOException {
        InputStream is = new ByteArrayInputStream(teamAsString().getBytes("UTF-8"));

        Team team = new Team("test", new ArrayList<>());
        List<Pokemon> actual = team.read(is);
        List<Pokemon> expected = getTeam().getMons();

        IntStream.range(0, 4).forEach(i -> assertEquals(expected.get(i).getName(), actual.get(i).getName(), "Not the same name"));

    }

}
