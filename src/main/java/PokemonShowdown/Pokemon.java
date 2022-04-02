package PokemonShowdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Pokemon {
    
    private String name;
    private List<Type> types = new ArrayList<>();
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
        setPokemonDetails("charizard", Arrays.asList("fire","flying"), 328, 285, 237, 267,Arrays.asList("flamethrower","earthquake","swords dance","air slash"));
    }

    private void setPokemonToBlastoise() {
        setPokemonDetails("blastoise", Arrays.asList("water","none"), 330, 237, 277, 223, Arrays.asList("hydro pump","recover","skull bash","ice beam"));  
    }

    private void setPokemonToVenusaur() {
        setPokemonDetails("venusaur", Arrays.asList("grass","poison"), 332, 231, 267, 227, Arrays.asList("sludge bomb","earthquake","swords dance","solar beam"));
    }

    private void setPokemonToPikachu() {
        setPokemonDetails("pikachu", Arrays.asList("electric","none"), 274, 299, 167, 247, Arrays.asList("slash","thunderbolt","swords dance","surf"));
    }

    private void setPokemonDetails(String name, List<String> types, int maxHp, int attack, int defence, int speed, Collection<String> moves) {
        this.name = name;
        types.stream().forEach((type) -> this.types.add(new Type(type)));
        this.maxHp = maxHp;
        hp = maxHp;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        moves.stream().forEach((move) -> this.moves.add(new Move(move)));
    }

    public void attack(Pokemon mon, int moveIndex) {
        double effectiveness = getEffectiveness(mon, moveIndex);
        System.out.println(effectiveness);
        double stab = getStab(moveIndex);
        System.out.println(stab);
        if (checkIfHit(moveIndex)) {
            mon.takeDamage(calculateDamage(mon, moveIndex, effectiveness, stab));
            heal(moveIndex);
            System.out.println(getName() + " used " + getMove(moveIndex).getName() + " on " + mon);
            if (getMoves().get(moveIndex).getAttackBoost() == true) attackBoost();
        } else System.out.println(getName() + " missed " + getMoves().get(moveIndex).getName());
    }

    private int calculateDamage(Pokemon mon, int moveIndex, double effectiveness, double stab) {
        double randomness = ThreadLocalRandom.current().nextDouble(0.85,1);
        double moveBaseDamage = this.getMoves().get(moveIndex).getDamage();
        double attackPerOpponentsDefence = (double)this.getAttack()/mon.getDefence();
        return (int)Math.floor(((0.84*(moveBaseDamage*attackBoost)*attackPerOpponentsDefence)+2)*stab*effectiveness*randomness*criticalHit());
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

    public List<Type> getTypes() {
        return types;
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
        if ((getMoveType(moveIndex).getName().equals(getTypes().get(0).getName())) 
        || getMoveType(moveIndex).getName().equals(getTypes().get(1).getName())) return 1.5;
        return 1;
    }

    private boolean checkIfHit(int moveIndex) {
        if (ThreadLocalRandom.current().nextDouble(0,1) > getMoves().get(moveIndex).getAccuracy()) return false;
        return true;
    }

    private double criticalHit() {
        if (ThreadLocalRandom.current().nextDouble(0,1) < 0.0625) {
            System.out.println("Its a crit!");
            return 1.95;
        }
        return 1;
    }

    private double getEffectiveness(Pokemon mon, int moveIndex) {
        if (getMoveType(moveIndex).checkIfStrongAgainst(mon.getTypes().get(0)) 
        && getMoveType(moveIndex).checkIfStrongAgainst(mon.getTypes().get(1))) {
            return 4;
        }
        if (getMoveType(moveIndex).checkIfWeakAgainst(mon.getTypes().get(0)) 
        && getMoveType(moveIndex).checkIfWeakAgainst(mon.getTypes().get(1))) {
            return 0.25;
        }
        if ((getMoveType(moveIndex).checkIfStrongAgainst(mon.getTypes().get(0)) 
        && getMoveType(moveIndex).checkIfWeakAgainst(mon.getTypes().get(1))) 
        || ((getMoveType(moveIndex).checkIfStrongAgainst(mon.getTypes().get(1)) 
        && getMoveType(moveIndex).checkIfWeakAgainst(mon.getTypes().get(0))))) {
            return 1;
        }
        if (getMoves().get(moveIndex).getType().checkIfStrongAgainst(mon.getTypes().get(0)) 
        || getMoves().get(moveIndex).getType().checkIfStrongAgainst(mon.getTypes().get(1))) {
            return 2;
        }
        if (getMoves().get(moveIndex).getType().checkIfWeakAgainst(mon.getTypes().get(0)) 
        || getMoves().get(moveIndex).getType().checkIfWeakAgainst(mon.getTypes().get(1))) {
            return 0.5;
        }
        return 1;
    }

    public Collection<String> getValidPokemon() {
        return validPokemon;
    }

    public Move getMove(int moveIndex) {
        return moves.get(moveIndex);
    }

    public Type getMoveType(int moveIndex) {
        return moves.get(moveIndex).getType();
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
