package PokemonShowdown;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface IFileHandling {
    
    public List<Pokemon> read() throws IOException;

    public List<Pokemon> read(InputStream is);

    public void write() throws IOException;

    public void write(OutputStream os);
}
