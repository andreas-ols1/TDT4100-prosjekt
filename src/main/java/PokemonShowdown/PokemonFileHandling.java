package PokemonShowdown;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface PokemonFileHandling {
    
    public List<String> read(String mon) throws IOException;

    public List<String> read(InputStream is, String mon);
}
