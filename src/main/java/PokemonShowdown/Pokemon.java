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
    private int orgAttack;
    private int defence;
    private int speed;
    private int orgSpeed;
    private int maxHp;
    private int hp;
    private boolean isDead = false;
    private double attackBoost = 1;
    private double speedBoost = 1;
    private Collection<String> validPokemon = Arrays.asList("venusaur","charizard","blastoise","pikachu",
    "nidoking","arcanine","alakazam","machamp","golem","slowbro","gengar","gyarados","aerodactyl","snorlax",
    "articuno","zapdos","moltres","dragonite","mewtwo","mew","rayquaza","amoonguss");

    public Pokemon(String name) {
        checkValidPokemon(name);
        switch (name.toLowerCase()) {
            case "charizard" -> setPokemonToCharizard();
            case "venusaur" -> setPokemonToVenusaur();
            case "blastoise" -> setPokemonToBlastoise();
            case "pikachu" -> setPokemonToPikachu();
            case "nidoking" -> setPokemonToNidoking();
            case "arcanine" -> setPokemonToArcanine();
            case "alakazam" -> setPokemonToAlakazam();
            case "machamp" -> setPokemonToMachamp();
            case "golem" -> setPokemonToGolem();
            case "slowbro" -> setPokemonToSlowbro();
            case "gengar" -> setPokemonToGengar();
            case "gyarados" -> setPokemonToGyarados();
            case "aerodactyl" -> setPokemonToAerodactyl();
            case "snorlax" -> setPokemonToSnorlax();
            case "articuno" -> setPokemonToArticuno();
            case "zapdos" -> setPokemonToZapdos();
            case "moltres" -> setPokemonToMoltres();
            case "dragonite" -> setPokemonToDragonite();
            case "mewtwo" -> setPokemonToMewtwo();
            case "mew" -> setPokemonToMew();
            case "rayquaza" -> setPokemonToRayquaza();
            case "amoonguss" -> setPokemonToAmoonguss();
        }
    }

    private void checkValidPokemon(String name) {
        if (!(validPokemon.contains(name))) {
            throw new IllegalArgumentException("Not a valid pokémon");
        }
    }

    private void setPokemonToVenusaur() {
        setPokemonDetails("venusaur", Arrays.asList("grass","poison"), 332, 231, 267, 227, Arrays.asList("sludge bomb","earthquake","swords dance","giga drain"));
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

    private void setPokemonToNidoking() {
        setPokemonDetails("nidoking", Arrays.asList("poison", "ground"), 334, 271, 221, 237, Arrays.asList("earthquake","sludge bomb","surf","swords dance"));
    }

    private void setPokemonToArcanine() {
        setPokemonDetails("arcanine", Arrays.asList("fire", "none"), 352, 287, 227, 257, Arrays.asList("flamethrower","recover","earthquake","skull bash"));
    }
    
    private void setPokemonToAlakazam() {
        setPokemonDetails("alakazam", Arrays.asList("psychic", "none"), 282, 337, 257, 307, Arrays.asList("psychic","shadow ball","thunderbolt","recover"));
    }

    private void setPokemonToMachamp() {
        setPokemonDetails("machamp", Arrays.asList("fighting", "none"), 352, 327, 237, 177, Arrays.asList("cross chop","thunder punch","ice punch","fire punch"));
    }

    private void setPokemonToGolem() {
        setPokemonDetails("golem", Arrays.asList("rock", "ground"), 332, 307, 327, 157, Arrays.asList("earthquake","rock slide","skull bash","swords dance")); 
    }

    private void setPokemonToSlowbro() {
        setPokemonDetails("slowbro", Arrays.asList("water", "psychic"), 362, 287, 267, 127, Arrays.asList("surf", "psychic", "recover", "ice beam"));
    }

    private void setPokemonToGengar() {
        setPokemonDetails("gengar", Arrays.asList("ghost", "poison"), 292, 327, 217, 287, Arrays.asList("shadow ball", "psychic", "sludge bomb", "thunderbolt"));
    }

    private void setPokemonToGyarados() {
        setPokemonDetails("gyarados", Arrays.asList("water", "flying"), 362, 317, 267, 229, Arrays.asList("hydro pump", "dragon pulse", "dragon dance", "thunderbolt"));
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
        setPokemonDetails("dragonite", Arrays.asList("dragon", "flying"), 354, 335, 267, 227, Arrays.asList("dragon pulse", "flamethrower", "roost", "dragon dance"));
    }

    private void setPokemonToMewtwo() {
        setPokemonDetails("mewtwo", Arrays.asList("psychic", "none"), 384, 247, 375, 327, Arrays.asList("psychic", "ice beam", "shadow ball", "recover"));
    }

    private void setPokemonToMew() {
        setPokemonDetails("mew", Arrays.asList("psychic", "none"), 372, 267, 267, 267, Arrays.asList("psychic", "earthquake", "recover", "swords dance"));
    }

    private void setPokemonToRayquaza() {
        setPokemonDetails("rayquaza", Arrays.asList("dragon","flying"), 382, 367, 247, 257, Arrays.asList("dragon pulse","dragons ascent","v-create","dragon dance"));
    }

    private void setPokemonToAmoonguss() {
        setPokemonDetails("amoonguss", Arrays.asList("grass","poison"), 400, 327, 227, 127, Arrays.asList("giga drain","sludge bomb","synthesis","solar beam"));
    }

    private void setPokemonDetails(String name, List<String> types, int maxHp, int attack, int defence, int speed, Collection<String> moves) {
        this.name = name;
        types.stream().forEach(type -> this.types.add(new Type(type)));
        this.maxHp = maxHp;
        hp = maxHp;
        this.attack = attack;
        orgAttack = attack;
        this.defence = defence;
        this.speed = speed;
        orgSpeed = speed;
        moves.stream().forEach(move -> this.moves.add(new Move(move)));
    }

    public void attack(Pokemon mon, int moveIndex) {
        double effectiveness = getEffectiveness(mon, moveIndex);
        double stab = getStab(moveIndex);
        String monName = getName().substring(0,1).toUpperCase() + getName().substring(1);
        String moveName = getMove(moveIndex).getName().substring(0, 1).toUpperCase() + getMove(moveIndex).getName().substring(1);
        String targetName = mon.getName().substring(0,1).toUpperCase() + mon.getName().substring(1);
        if (checkIfHit(moveIndex)) {
            if (!(getMove(moveIndex).getDamage() == 0)) {
                if (!checkImmunity(mon, moveIndex)) {
                    mon.takeDamage(calculateDamage(mon, moveIndex, effectiveness, stab));
                    heal(moveIndex);
                    speedBoost(moveIndex);
                    attackBoost(moveIndex);
                    System.out.println(monName + " used " + moveName + " on " + targetName);
                }
                else System.out.println(targetName + " is immune to " + moveName);
            }
            else {
                heal(moveIndex);
                speedBoost(moveIndex);
                attackBoost(moveIndex);
                System.out.println(monName + " used " + moveName);
            }
        } 
        else System.out.println(monName + " missed " + moveName);
    }

    private int calculateDamage(Pokemon mon, int moveIndex, double effectiveness, double stab) {
        double randomness = ThreadLocalRandom.current().nextDouble(0.85,1);
        double moveBaseDamage = this.getMoves().get(moveIndex).getDamage();
        double attackPerOpponentsDefence = (double)this.getAttack()/mon.getDefence();
        return (int)Math.floor(((0.84*moveBaseDamage*attackPerOpponentsDefence)+2)*stab*effectiveness*randomness*criticalHit());
    }

    private void heal(int moveIndex) {
        double healPercentage = getMoves().get(moveIndex).getHeal();
        if (hp + healPercentage * maxHp < maxHp) {
            hp += healPercentage * maxHp;
        }
        else hp = maxHp;
    }

    private void attackBoost(int moveIndex) {
        if ((attackBoost + getMove(moveIndex).getAttackBoost() <= 4) && (attackBoost + getMove(moveIndex).getAttackBoost() > 0)) {
            attackBoost += getMove(moveIndex).getAttackBoost();
            attack = (int)(orgAttack * attackBoost);
        }
        else System.out.println("Attack boost maxes out at x4, and can't go below 0.5");
    }

    private void speedBoost(int moveIndex) {
        if ((speedBoost + getMove(moveIndex).getSpeedBoost() <= 4) && (speedBoost + getMove(moveIndex).getSpeedBoost() > 0)) {
            speedBoost += getMove(moveIndex).getSpeedBoost();
            speed = (int)(orgSpeed * speedBoost);
        }
        else System.out.println("Speed boost maxes out at x4, and can't go below 0.5");
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

    public int getMaxHp() {
        return maxHp;
    }

    public double getSpeedBoost() {
        return speedBoost;
    }

    public double getAttackBoost() {
        return attackBoost;
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

    public boolean isDead() {
        return isDead;
    }
 
    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
    Pokemon snorlax = new Pokemon("snorlax");
    Pokemon charizard = new Pokemon("charizard");
    System.out.println(charizard.getHp());
    snorlax.attack(charizard,0);
    System.out.println(charizard.getHp());
    }

}
