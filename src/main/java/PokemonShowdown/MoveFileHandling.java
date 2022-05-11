package PokemonShowdown;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface MoveFileHandling {
    
    public List<String> read(String move) throws IOException;

    public List<String> read(InputStream is, String move);
    
}
