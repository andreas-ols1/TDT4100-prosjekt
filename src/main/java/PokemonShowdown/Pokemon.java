package PokemonShowdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Pokemon {
    
    private String name;
    private Type type;
    private List<Move> moves = new ArrayList<>();
    private int attack;
    private int defence;
    private int speed;
    private int maxHp;
    private int hp;
    private boolean isDead = false;
    private int attackBoost = 1;
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
        setPokemonDetails("charizard", "fire", 328, 285, 237, 267,Arrays.asList("flamethrower","earthquake","swords dance","slash"));
    }

    private void setPokemonToBlastoise() {
        setPokemonDetails("blastoise", "water", 330, 237, 277, 223, Arrays.asList("hydro pump","recover","skull bash","ice beam"));  
    }

    private void setPokemonToVenusaur() {
        setPokemonDetails("venusaur", "grass", 332, 231, 267, 227, Arrays.asList("slash","earthquake","swords dance","solar beam"));
    }

    private void setPokemonToPikachu() {
        setPokemonDetails("pikachu", "electric", 274, 299, 167, 247, Arrays.asList("slash","thunderbolt","swords dance","surf"));
    }

    private void setPokemonDetails(String name, String type, int maxHp, int attack, int defence, int speed, Collection<String> moves) {
        this.name = name;
        this.type = new Type(type);
        this.maxHp = maxHp;
        hp = maxHp;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        moves.stream().forEach((move) -> this.moves.add(new Move(move)));
    }

    public void attack(Pokemon mon, int moveIndex) {
        double effectiveness = getMoves().get(moveIndex).getType().getEffectiveness(mon.getType());
        double stab = getStab(moveIndex);
        mon.takeDamage(calculateDamage(mon, moveIndex, effectiveness, stab));
        heal(moveIndex);
        if (getMoves().get(moveIndex).getAttackBoost() == true) attackBoost();
    }

    private int calculateDamage(Pokemon mon, int moveIndex, double effectiveness, double stab) {
        double randomness = ThreadLocalRandom.current().nextDouble(0.85,1);
        double moveBaseDamage = this.getMoves().get(moveIndex).getDamage();
        double attackPerOpponentsDefence = (double)this.getAttack()/mon.getDefence();
        return (int)Math.floor(((0.84*(moveBaseDamage*attackBoost)*attackPerOpponentsDefence)+2)*stab*effectiveness*randomness);
    }

    private void heal(int moveIndex) {
        double healPercentage = getMoves().get(moveIndex).getHeal();
        if (hp + healPercentage * maxHp < maxHp) {
            hp += healPercentage * maxHp;
        }
        else hp = maxHp;
    }

    private void attackBoost() {
        if (attackBoost + 1 <= 4) {
            attackBoost += 1;
        }
        else throw new IllegalStateException("Attack boost maxes out at x4");
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

    private void takeDamage(double damage) {
        if (hp - damage > 0) {
            hp -= damage;
        }  
        else {
            hp = 0;
            isDead = true;
        }
    }

    private double getStab(int moveIndex) {
        if (this.getMoves().get(moveIndex).getType().getName().equals(this.getType().getName())) return 1.5;
        return 1;
    }

    public Collection<String> getValidPokemon() {
        return validPokemon;
    }
 
    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Pokemon charizard = new Pokemon("charizard");
        Pokemon blastoise = new Pokemon("blastoise");
        Pokemon venusaur = new Pokemon("venusaur");
        Pokemon pikachu = new Pokemon("pikachu");
        // System.out.println(charizard);
        // System.out.println(charizard.getMoves());
        // System.out.println(venusaur);
        // System.out.println(chadizard.getType().checkIfStrongAgainst(venusaur.getType()));
        // System.out.println();
        // System.out.println(pikachu);
        // System.out.println(new File("").getAbsolutePath());
        // System.out.println(blastoise.getHp());
        // charizard.attack(blastoise, 0);
        // System.out.println(blastoise.getHp());
        // System.out.println(charizard.getHp());
        // blastoise.attack(charizard, 0);
        // System.out.println(charizard.getHp());
        // blastoise.attack(charizard, 2);
        System.out.println(charizard.getHp());
        venusaur.attack(charizard,1);
        System.out.println(charizard.getHp());
    }

}
