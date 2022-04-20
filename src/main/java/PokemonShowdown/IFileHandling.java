package PokemonShowdown;

import java.io.IOException;
import java.util.List;

public interface IFileHandling {
    
    public List<Pokemon> read() throws IOException;

    public void write() throws IOException;
}
