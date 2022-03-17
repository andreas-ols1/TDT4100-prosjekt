package PokemonShowdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pokemon {
    
    private String name;
    private Type type;
    private List<Move> moves = new ArrayList<>();
    private int attack;
    private int defence;
    private int speed;
    private int hp;
    private List<String> validPokemon = Arrays.asList("charizard", "blastoise", "venusaur", "pikachu");

    public Pokemon(String name) {
        checkValidPokemon(name);
        if (name.toLowerCase().equals("charizard")) {
            setPokemonToCharizard();
        }
        if (name.toLowerCase().equals("venusaur")) {
            setPokemonToVenusaur();
        }
        if (name.toLowerCase().equals("blastoise")) {
            setPokemonToBlastoise();
        }
    }

    public void checkValidPokemon(String name) {
        if (!(validPokemon.contains(name))) throw new IllegalArgumentException("Not a valid pokémon");
    }
 
    public void setPokemonToCharizard() {
        name = "charizard";
        type = new Type("fire");
        moves.add(new AttackMove("flamethrower"));
        moves.add(new AttackMove("earthquake"));
        moves.add(new PassiveMove("swords dance"));
        moves.add(new AttackMove("slash"));
        attack = 109;
        defence = 80;
        speed = 100;
        hp = 78;
    }

    public void setPokemonToBlastoise() {
        name = "blastoise";
        type = new Type("water");
        moves.add(new AttackMove("hydro pump"));
        moves.add(new PassiveMove("recover"));
        moves.add(new AttackMove("skull bash"));
        moves.add(new AttackMove("ice beam"));
        attack = 85;
        defence = 105;
        speed = 78;
        hp = 80;
    }

    public void setPokemonToVenusaur() {
        name = "venusaur";
        type = new Type("grass");
        moves.add(new AttackMove("slash"));
        moves.add(new AttackMove("earthquake"));
        moves.add(new PassiveMove("swords dance"));
        moves.add(new AttackMove("solar beam"));
        attack = 100;
        defence = 100;
        speed = 80;
        hp = 79;
    }
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public List<Move> getMoves() {
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
        System.out.println(charizard);
        System.out.println(charizard.getMoves());
    }
}
