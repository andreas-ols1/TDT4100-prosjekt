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
    private Collection<String> validPokemon = Arrays.asList("venusaur","charizard","blastoise","pikachu","sandslash","nidoqueen",
    "nidoking","golduck","primeape","arcanine","poliwrath","alakazam","machamp","tentacruel","golem","slowbro","gengar","exeggutor",
    "rhydon","starmie","gyarados","kabutops","aerodactyl","snorlax","articuno","zapdos","moltres","dragonite","mewtwo","mew");

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
            case "sandslash":
                setPokemonToSandslash();
                break;
            case "nidoqueen":
                setPokemonToNidoqueen();
                break;
            case "nidoking":
                setPokemonToNidoking();
                break;
            case "golduck":
                setPokemonToGolduck();
                break;
            case "primeape":
                setPokemonToPrimeape();
                break;
            case "arcanine":
                setPokemonToArcanine();
                break;
            case "poliwrath":
                setPokemonToPoliwrath();
                break;
            case "alakazam":
                setPokemonToAlakazam();
                break;
            case "machamp":
                setPokemonToMachamp();
                break;
            case "tentacruel":
                setPokemonToTentacruel();
                break;
            case "golem":
                setPokemonToGolem();
                break;
            case "slowbro":
                setPokemonToSlowbro();
                break;
            case "gengar":
                setPokemonToGengar();
                break;
            case "exeggutor":
                setPokemonToExeggutor();
                break;
            case "rhydon":
                setPokemonToRhydon();
                break;
            case "starmie":
                setPokemonToStarmie();
                break;
            case "gyarados":
                setPokemonToGyarados();
                break;
            case "kabutops":
                setPokemonToKabutops();
                break;
            case "aerodactyl":
                setPokemonToAerodactyl();
                break;
            case "snorlax":
                setPokemonToSnorlax();
                break;
            case "articuno":
                setPokemonToArticuno();
                break;
            case "zapdos":
                setPokemonToZapdos();
                break;
            case "moltres":
                setPokemonToMoltres();
                break;
            case "dragonite":
                setPokemonToDragonite();
                break;
            case "mewtwo":
                setPokemonToMewtwo();
                break;
            case "mew":
                setPokemonToMew();
                break;
        }
    }

    private void checkValidPokemon(String name) {
        if (!(validPokemon.contains(name))) {
            throw new IllegalArgumentException("Not a valid pokémon");
        }
    }

    private void setPokemonToVenusaur() {
        setPokemonDetails("venusaur", Arrays.asList("grass","poison"), 332, 231, 267, 227, Arrays.asList("sludge bomb","earthquake","swords dance","solar beam"));
    }
 
    private void setPokemonToCharizard() {
        setPokemonDetails("charizard", Arrays.asList("fire","flying"), 328, 285, 237, 267,Arrays.asList("flamethrower","earthquake","swords dance","air slash"));
    }

    private void setPokemonToBlastoise() {
        setPokemonDetails("blastoise", Arrays.asList("water","none"), 330, 237, 277, 223, Arrays.asList("hydro pump","recover","skull bash","ice beam"));  
    }

    private void setPokemonToPikachu() {
        setPokemonDetails("pikachu", Arrays.asList("electric","none"), 274, 299, 167, 247, Arrays.asList("slash","thunderbolt","swords dance","surf"));
    }

    private void setPokemonToSandslash() {
        setPokemonDetails("sandslash", Arrays.asList("ground", "none"), 322, 267, 287, 197, Arrays.asList("slash","earthquake","swords dance","skull bash"));
    }
    // name, types, hp, attack, defence, speed, moves
    private void setPokemonToNidoqueen() {
        setPokemonDetails("nidoqueen", Arrays.asList("poison", "ground"), 352, 251, 241, 219, Arrays.asList("earthquake","thunderbolt","sludge bomb","surf"));
    }

    private void setPokemonToNidoking() {
        setPokemonDetails("nidoking", Arrays.asList("poison", "ground"), 334, 271, 221, 237, Arrays.asList("earthquake","sludge bomb","surf","swords dance"));
    }

    private void setPokemonToGolduck() {
        setPokemonDetails("golduck", Arrays.asList("water", "none"), 332, 257, 227, 237, Arrays.asList("hydro pump","slash","ice beam","surf"));
    }

    private void setPokemonToPrimeape() {
        setPokemonDetails("primeape", Arrays.asList("figthing", "none"), 302, 277, 207, 257, Arrays.asList("cross chop","swords dance","earthquake","thunder punch"));
    }

    private void setPokemonToArcanine() {
        setPokemonDetails("arcanine", Arrays.asList("fire", "none"), 352, 287, 227, 257, Arrays.asList("flamethrower","recover","earthquake","skull bash"));
    }

    private void setPokemonToPoliwrath() {
        setPokemonDetails("poliwrath", Arrays.asList("water", "fighting"), 352, 257, 257, 207, Arrays.asList("hydro pump","cross chop","ice beam","swords dance"));
    }

    private void setPokemonToAlakazam() {
        setPokemonDetails("alakazam", Arrays.asList("psychic", "none"), 282, 337, 257, 307, Arrays.asList("psychic","thunderbolt","ice beam","recover"));
    }

    private void setPokemonToMachamp() {
        setPokemonDetails("machamp", Arrays.asList("fighting", "none"), 352, 327, 237, 177, Arrays.asList("cross chop","thunder punch","ice punch","fire punch"));
    }

    private void setPokemonToTentacruel() {
        setPokemonDetails("tentacruel", Arrays.asList("water", "poison"), 332, 227, 307, 267, Arrays.asList("surf","sludge bomb","recover","ice beam"));
    }

    private void setPokemonToGolem() {
        setPokemonDetails("golem", Arrays.asList("rock", "ground"), 332, 307, 327, 157, Arrays.asList("earthquake","rock slide","skull bash","swords dance")); 
        // Add explosion ??
    }

    private void setPokemonToSlowbro() {
        setPokemonDetails("slowbro", Arrays.asList("water", "psychic"), 362, 287, 267, 127, Arrays.asList("surf", "psychic", "recover", "ice beam"));
    }

    private void setPokemonToGengar() {
        setPokemonDetails("gengar", Arrays.asList("ghost", "poison"), 292, 327, 217, 287, Arrays.asList("shadow ball", "psychic", "sludge bomb", "thunderbolt"));
    }

    private void setPokemonToExeggutor() {
        setPokemonDetails("exeggutor", Arrays.asList("grass", "psychic"), 362, 237, 317, 177, Arrays.asList("solar beam", "psychic", "recover", "slash"));
    }

    private void setPokemonToRhydon() {
        setPokemonDetails("rhydon", Arrays.asList("ground", "rock"), 382, 327, 307, 147, Arrays.asList("earthquake", "rock slide", "swords dance", "surf"));
    }

    private void setPokemonToStarmie() {
        setPokemonDetails("starmie", Arrays.asList("water", "psychic"), 292, 267, 237, 297, Arrays.asList("hydro pump", "psychic", "ice beam", "thunderbolt"));
    }

    private void setPokemonToGyarados() {
        setPokemonDetails("gyarados", Arrays.asList("water", "flying"), 362, 317, 267, 229, Arrays.asList("hydro pump", "dragon pulse", "ice beam", "thunderbolt"));
    }

    private void setPokemonToKabutops() {
        setPokemonDetails("kabutops", Arrays.asList("water", "rock"), 292, 297, 277, 227, Arrays.asList("hydro pump", "rock slide", "ice beam", "swords dance"));
        // Add agility ??
    }

    private void setPokemonToAerodactyl() {
        setPokemonDetails("aerodactyl", Arrays.asList("rock", "flying"), 332, 277, 217, 327, Arrays.asList("rock slide", "air slash", "earthquake", "swords dance"));
    }
    
    private void setPokemonToSnorlax() {
        setPokemonDetails("snorlax", Arrays.asList("normal", "none"), 492, 256, 287, 127, Arrays.asList("skull bash", "ice punch", "earthquake", "fire punch"));
    }

    private void setPokemonToArticuno() {
        setPokemonDetails("articuno", Arrays.asList("ice", "flying"), 352, 257, 317, 237, Arrays.asList("ice beam", "air slash", "slash", "roost"));
    }

    private void setPokemonToZapdos() {
        setPokemonDetails("zapdos", Arrays.asList("electric", "flying"), 352, 317, 247, 267, Arrays.asList("thunderbolt", "air slash", "slash", "roost"));
    }

    private void setPokemonToMoltres() {
        setPokemonDetails("moltres", Arrays.asList("fire", "flying"), 352, 247, 317, 247, Arrays.asList("flamethrower", "air slash", "slash", "roost"));
    }

    private void setPokemonToDragonite() {
        setPokemonDetails("dragonite", Arrays.asList("dragon", "flying"), 354, 335, 267, 227, Arrays.asList("dragon pulse", "flamethrower", "roost", "thunderbolt"));
    }

    private void setPokemonToMewtwo() {
        setPokemonDetails("mewtwo", Arrays.asList("psychic", "none"), 384, 247, 375, 327, Arrays.asList("psychic", "ice beam", "shadow ball", "recover"));
    }

    private void setPokemonToMew() {
        setPokemonDetails("mew", Arrays.asList("psychic", "none"), 372, 267, 267, 267, Arrays.asList("psychic", "earthquake", "recover", "swords dance"));
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
            if (!checkImmunity(mon, moveIndex)) {
                mon.takeDamage(calculateDamage(mon, moveIndex, effectiveness, stab));
                heal(moveIndex);
                System.out.println(getName() + " used " + getMove(moveIndex).getName() + " on " + mon);
                if (getMoves().get(moveIndex).getAttackBoost() == true) attackBoost();
            }
            else System.out.println(mon.getName() + " is immune to " + getMove(moveIndex).getName());
        } 
        else System.out.println(getName() + " missed " + getMove(moveIndex).getName());
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

    private boolean checkImmunity(Pokemon mon, int moveIndex) {
        if (getMoveType(moveIndex).checkIfImmune(mon.getTypes().get(0)) 
        || getMoveType(moveIndex).checkIfImmune(mon.getTypes().get(1))) return true;
        return false;
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
        // Pokemon charizard = new Pokemon("charizard");
        // Pokemon blastoise = new Pokemon("blastoise");
        // Pokemon venusaur = new Pokemon("venusaur");
        // Pokemon pikachu = new Pokemon("pikachu");
        // Pokemon mewtwo = new Pokemon("mewtwo");
        // Pokemon golem = new Pokemon("golem");
        // Pokemon machamp = new Pokemon("machamp");
        // System.out.println(golem.getHp());
        // machamp.attack(golem, 0);
        // System.out.println(golem.getHp());
    }

}
