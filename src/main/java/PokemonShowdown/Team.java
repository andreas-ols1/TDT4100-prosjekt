package PokemonShowdown;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Team implements IFileHandling {

    private String name;
    private List<Pokemon> mons = new ArrayList<>();
    private final static String filepath = "./src/main/resources/PokemonShowdown/teams/";

    public Team(String name, List<Pokemon> mons) {  
        this.name = name;
        mons.stream().forEach((mon) -> this.mons.add(mon));
    }


    @Override
	public List<Pokemon> read(InputStream is) {
		try (Scanner sc = new Scanner(is)) {
            List<String> names = new ArrayList<>();
            while (sc.hasNextLine()) {
                names.add(sc.nextLine());
            }
            List<Pokemon> mons = new ArrayList<>();
            names.stream().
            forEach((name) -> mons.add(new Pokemon(name)));
            return mons;
        }
    }

    @Override
    public List<Pokemon> read() throws IOException {
        String path = filepath + getName()+".txt";
        try (var is = new FileInputStream(path)) {
            return read(is);
        }
    } 
 
    @Override
    public void write() throws IOException {
        String path = filepath + getName() + ".txt";
        try (var os = new FileOutputStream(path)) {
            write(os);
        }
        
    }

    @Override
	public void write(OutputStream os) {
		try (PrintWriter pw = new PrintWriter(os)){
            mons.stream()
            .map((mon) -> mon.getName())
            .forEach(pw::println);
        }
	}


    public String getName() {
        return name;
    }

    public List<Pokemon> getMons() {
        return mons;
    }

    public void addMons(List<Pokemon> mons) {
        mons.stream().forEach((mon) -> this.mons.add(mon));
    }
    

    public static void main(String[] args) throws IOException {

    }


	


	
    
}
