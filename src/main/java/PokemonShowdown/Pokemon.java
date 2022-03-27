package PokemonShowdown;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Pokemon {
    
    private String name;
    private Type type;
    private Collection<Move> moves = new ArrayList<>();
    private int attack;
    private int defence;
    private int speed;
    private int hp;
    private Collection<String> validPokemon = Arrays.asList("charizard", "blastoise", "venusaur", "pikachu");

    public Pokemon(String name) {
        checkValidPokemon(name);
        switch (name.toLowerCase()) {
            case "charizard":
                setPokemonToCharizard();
                break;
            case "venusaur":
                setPokemonToVenusaur();
                break;
            case "blastoise":
                setPokemonToBlastoise();
                break;
            case "pikachu":
                setPokemonToPikachu();
                break;
        }
    }

    private void checkValidPokemon(String name) {
        if (!(validPokemon.contains(name))) {
            throw new IllegalArgumentException("Not a valid pokémon");
        }
    }
 
    private void setPokemonToCharizard() {
        setPokemonDetails("charizard", "fire", 78, 109, 80, 100,Arrays.asList("flamethrower","earthquake","swords dance","slash"));
    }

    private void setPokemonToBlastoise() {
        setPokemonDetails("blastoise", "water", 80, 85, 105, 78, Arrays.asList("hydro pump","recover","skull bash","ice beam"));  
    }

    private void setPokemonToVenusaur() {
        setPokemonDetails("venusaur", "grass", 79, 100, 100, 80, Arrays.asList("slash","earthquake","swords dance","solar beam"));
    }

    private void setPokemonToPikachu() {
        setPokemonDetails("pikachu", "electric", 35, 100, 50, 90, Arrays.asList("slash","thunderbolt","swords dance","surf"));
    }

    private void setPokemonDetails(String name, String type, int hp, int attack, int defence, int speed, Collection<String> moves) {
        this.name = name;
        this.type = new Type(type);
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        moves.stream().forEach((move) -> this.moves.add(new Move(move)));
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Collection<Move> getMoves() {
        return moves;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }
 
    @Override
    public String toString() {
        return name + 
        "\nType: " + type.getName() + 
        "\nMoves: " + moves.stream().map(move -> move.getName()).collect(Collectors.toList()) + 
        "\nHP: " + hp + 
        "\nAttack: " + attack + 
        "\nDefence: " + defence + 
        "\nSpeed: " + speed;
    }

    public static void main(String[] args) {
        Pokemon charizard = new Pokemon("charizard");
        Pokemon chadizard = new Pokemon("charizard");
        Pokemon venusaur = new Pokemon("venusaur");
        Pokemon pikachu = new Pokemon("pikachu");
        // System.out.println(charizard);
        // System.out.println(charizard.getMoves());
        // System.out.println(venusaur);
        System.out.println(chadizard.getType().checkIfStrongAgainst(venusaur.getType()));
        System.out.println(chadizard);
        System.out.println(pikachu);
        System.out.println(new File("").getAbsolutePath());
    }

}
