package PokemonShowdown;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Team implements IFileHandling {

    private String name;
    private List<Pokemon> mons = new ArrayList<>();
    private final String filepath = "./src/main/resources/PokemonShowdown/teams";

    public Team(String name, List<Pokemon> mons) {  
        this.name = name;
        mons.stream().forEach((mon) -> this.mons.add(mon));
    }


    @Override
    public List<Pokemon> read() throws IOException {
        try (Scanner sc = new Scanner(new FileReader(filepath + "/" + getName() + ".txt"))) {
            List<String> names = new ArrayList<>();
            while (sc.hasNextLine()) {
                names.add(sc.nextLine());
            }
            List<Pokemon> mons = new ArrayList<>();
            names.stream().
            forEach((name) -> mons.add(new Pokemon(name)));
            return mons;
        } catch (IOException e) {
            System.out.println("An IO-exception has occured.");
        } catch (Exception e) {
            System.out.println("An unknown error has occured");
        } return null;
        
    }

    @Override
    public void write() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filepath + "/" + name + ".txt"))){
            mons.stream()
            .map((mon) -> mon.getName())
            .forEach(pw::println);
        } catch (IOException e) {
            System.out.println("An IO-exception has occured.");
        } catch (Exception e) {
            System.out.println("An unknown error has occured");
        }
        
    }


    public String getName() {
        return name;
    }

    public List<Pokemon> getMons() {
        return mons;
    }
    

    public static void main(String[] args) throws IOException {
        // System.out.println(System.getProperty("user.dir"));
        // System.out.println(Path.of(System.getProperty("user.dir")));
        
        // String folderpath = "./src/main/resources/PokemonShowdown/teams";
        // String filepath = "./src/main/resources/PokemonShowdown/teams/team1.txt";
        // File folder = new File(folderpath);
        // folder.mkdir();
        // File fil = new File(filepath);
        // FileWriter fw = new FileWriter(fil);
        // PrintWriter pw = new PrintWriter(fw);
        // pw.println("Charizard");
        // pw.println("Venusaur");
        // pw.println("Blastoise");
        // pw.println("balle");
        // pw.close();
        // System.out.println(fil.getPath());
        // System.out.println(fil.getAbsolutePath());
        // System.out.println(Paths.get("./src/main/resources/PokemonShowdown/teams/team1.txt").toUri());


        Pokemon c = new Pokemon("charizard");
        Pokemon b = new Pokemon("blastoise");
        Pokemon v = new Pokemon("venusaur");
        Pokemon mr = new Pokemon("mega-rayquaza");

        Team t1 = new Team("t1", new ArrayList<Pokemon>(Arrays.asList(c, b, v, mr)));

        t1.write();

        System.out.println(t1.read());

        
    }
    
}
