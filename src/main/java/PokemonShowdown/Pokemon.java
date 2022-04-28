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
    private boolean crit;
    private double attackBoost = 1;
    private double speedBoost = 1;
    private final List<String> validPokemon = Arrays.asList("Venusaur","Charizard","Blastoise","Pikachu",
    "Nidoking","Arcanine","Alakazam","Machamp","Golem","Slowbro","Gengar","Gyarados","Aerodactyl","Snorlax",
    "Articuno","Zapdos","Moltres","Dragonite","Mewtwo","Mew","Rayquaza","Amoonguss");

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
        setPokemonDetails("Venusaur", Arrays.asList("Grass","Poison"), 332, 231, 267, 227, Arrays.asList("Sludge Bomb","Earthquake","Swords Dance","Giga Drain"));
    }
 
    private void setPokemonToCharizard() {
        setPokemonDetails("Charizard", Arrays.asList("Fire","Flying"), 328, 285, 237, 267,Arrays.asList("Flamethrower","Earthquake","Swords Dance","Air Slash"));
    }

    private void setPokemonToBlastoise() {
        setPokemonDetails("Blastoise", Arrays.asList("Water","None"), 330, 237, 277, 223, Arrays.asList("Hydro Pump","Recover","Skull Bash","Ice Beam"));  
    }

    private void setPokemonToPikachu() {
        setPokemonDetails("Pikachu", Arrays.asList("Electric","None"), 274, 299, 167, 247, Arrays.asList("Slash","Thunderbolt","Swords Dance","Surf"));
    }

    private void setPokemonToNidoking() {
        setPokemonDetails("Nidoking", Arrays.asList("Poison", "Ground"), 334, 271, 221, 237, Arrays.asList("Earthquake","Sludge Bomb","Surf","Swords Dance"));
    }

    private void setPokemonToArcanine() {
        setPokemonDetails("Arcanine", Arrays.asList("Fire", "None"), 352, 287, 227, 257, Arrays.asList("Flamethrower","Recover","Earthquake","Skull Bash"));
    }
    
    private void setPokemonToAlakazam() {
        setPokemonDetails("Alakazam", Arrays.asList("Psychic", "None"), 282, 337, 257, 307, Arrays.asList("Psychic","Shadow Ball","Thunderbolt","Recover"));
    }

    private void setPokemonToMachamp() {
        setPokemonDetails("Machamp", Arrays.asList("Fighting", "None"), 352, 327, 237, 177, Arrays.asList("Cross Chop","Thunder Punch","Ice Punch","Fire Punch"));
    }

    private void setPokemonToGolem() {
        setPokemonDetails("Golem", Arrays.asList("Rock", "Ground"), 332, 307, 327, 157, Arrays.asList("Earthquake","Rock Slide","Skull Bash","Swords Dance")); 
    }

    private void setPokemonToSlowbro() {
        setPokemonDetails("Slowbro", Arrays.asList("Water", "Psychic"), 362, 287, 267, 127, Arrays.asList("Surf","Psychic","Recover","Ice Beam"));
    }

    private void setPokemonToGengar() {
        setPokemonDetails("Gengar", Arrays.asList("Ghost", "Poison"), 292, 327, 217, 287, Arrays.asList("Shadow Ball","Psychic","Sludge Bomb","Thunderbolt"));
    }

    private void setPokemonToGyarados() {
        setPokemonDetails("Gyarados", Arrays.asList("Water", "Flying"), 362, 317, 267, 229, Arrays.asList("Hydro Pump","Dragon Pulse","Dragon Dance","Thunderbolt"));
    }

    private void setPokemonToAerodactyl() {
        setPokemonDetails("Aerodactyl", Arrays.asList("Rock", "Flying"), 332, 277, 217, 327, Arrays.asList("Rock Slide","Air Slash","Earthquake","Swords Dance"));
    }
    
    private void setPokemonToSnorlax() {
        setPokemonDetails("Snorlax", Arrays.asList("Normal", "None"), 492, 256, 287, 127, Arrays.asList("Skull Bash","Ice Punch","Earthquake","Fire Punch"));
    }

    private void setPokemonToArticuno() {
        setPokemonDetails("Articuno", Arrays.asList("Ice", "Flying"), 352, 257, 317, 237, Arrays.asList("Ice Beam","Air Slash","Slash","Roost"));
    }

    private void setPokemonToZapdos() {
        setPokemonDetails("Zapdos", Arrays.asList("Electric", "Flying"), 352, 317, 247, 267, Arrays.asList("Thunderbolt","Air Slash","Slash","Roost"));
    }

    private void setPokemonToMoltres() {
        setPokemonDetails("Moltres", Arrays.asList("Fire", "Flying"), 352, 247, 317, 247, Arrays.asList("Flamethrower","Air Slash","Slash","Roost"));
    }

    private void setPokemonToDragonite() {
        setPokemonDetails("Dragonite", Arrays.asList("Dragon", "Flying"), 354, 335, 267, 227, Arrays.asList("Dragon Pulse","Flamethrower","Roost","Dragon Dance"));
    }

    private void setPokemonToMewtwo() {
        setPokemonDetails("Mewtwo", Arrays.asList("Psychic", "None"), 384, 247, 375, 327, Arrays.asList("Psychic","Ice Beam","Shadow Ball","Recover"));
    }

    private void setPokemonToMew() {
        setPokemonDetails("Mew", Arrays.asList("Psychic", "None"), 372, 267, 267, 267, Arrays.asList("Psychic","Earthquake","Recover","Swords Dance"));
    }

    private void setPokemonToRayquaza() {
        setPokemonDetails("Rayquaza", Arrays.asList("Dragon","Flying"), 382, 367, 247, 257, Arrays.asList("Dragon Pulse","Dragons Ascent","V-create","Dragon Dance"));
    }

    private void setPokemonToAmoonguss() {
        setPokemonDetails("Amoonguss", Arrays.asList("Grass","Poison"), 400, 327, 227, 127, Arrays.asList("Giga Drain","Sludge Bomb","Synthesis","Solar Beam"));
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

    public void attack(Pokemon target, int moveIndex) {
        double effectiveness = getEffectiveness(target, moveIndex);
        double stab = getStab(moveIndex);
        if (checkIfHit(moveIndex)) {
            if (!(getMove(moveIndex).getDamage() == 0)) {
                if (!checkImmunity(target, moveIndex)) {
                    target.takeDamage(calculateDamage(target, moveIndex, effectiveness, stab));
                    heal(moveIndex);
                    speedBoost(moveIndex);
                    attackBoost(moveIndex);
                    System.out.println(getName() + " used " + getMove(moveIndex).getName() + " on " + target.getName());
                    if (getEffectiveness(target, moveIndex) > 1) System.out.println("It's super effective!");
                    if (getEffectiveness(target, moveIndex) < 1) System.out.println("It was not very effective...");
                    if (crit) System.out.println("It's a crit!");
                    System.out.println("");
                }
                else System.out.println(getName() + " tried to use " + getMove(moveIndex).getName() + ",\nbut " + target.getName() + " is immune to " + getMove(moveIndex).getName() + ",\nso the attack did no damage!\n");
            }
            else {
                heal(moveIndex);
                speedBoost(moveIndex);
                attackBoost(moveIndex);
                System.out.println(getName()+ " used " + getMove(moveIndex).getName() + "\n");
            }
        } 
        else System.out.println(getName() + " missed " + getMove(moveIndex).getName() + "\n");
    }

    private int calculateDamage(Pokemon mon, int moveIndex, double effectiveness, double stab) {
        double randomness = ThreadLocalRandom.current().nextDouble(0.85,1);
        double moveBaseDamage = this.getMove(moveIndex).getDamage();
        double attackPerOpponentsDefence = (double)this.getAttack()/mon.getDefence();
        return (int)Math.floor(((0.84*moveBaseDamage*attackPerOpponentsDefence)+2)*stab*effectiveness*randomness*criticalHit());
    }

    private void heal(int moveIndex) {
        double healPercentage = getMove(moveIndex).getHeal();
        if (hp + healPercentage * maxHp < maxHp) {
            hp += healPercentage * maxHp;
        }
        else hp = maxHp;
    }

    private void attackBoost(int moveIndex) {
        if ((attackBoost + getMove(moveIndex).getAttackBoost() <= 4) && (attackBoost + getMove(moveIndex).getAttackBoost() > 0)) {
            attackBoost += getMove(moveIndex).getAttackBoost();
            attack = (int) Math.floor(orgAttack * attackBoost);
        }
        else System.out.println("Attack boost maxes out at x4, and\ncan't go below x0.5");
    }

    private void speedBoost(int moveIndex) {
        if ((speedBoost + getMove(moveIndex).getSpeedBoost() <= 4) && (speedBoost + getMove(moveIndex).getSpeedBoost() > 0)) {
            speedBoost += getMove(moveIndex).getSpeedBoost();
            speed = (int) Math.floor(orgSpeed * speedBoost);
        }
        else System.out.println("Speed boost maxes out at x4, and\ncan't go below x0.5");
    }

    public void revertStatChanges() {
        attack = orgAttack;
        speed = orgSpeed;
        attackBoost = 1;
        speedBoost = 1;
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

    public int getOrgAttack() {
        return orgAttack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
    }

    public int getOrgSpeed() {
        return orgSpeed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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
        if (ThreadLocalRandom.current().nextDouble(0,1) > getMove(moveIndex).getAccuracy()) return false;
        return true;
    }

    private double criticalHit() {
        if (ThreadLocalRandom.current().nextDouble(0,1) < 0.0625) {
            crit = true;
            return 1.95;
        }
        crit = false;
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
        if (getMoveType(moveIndex).checkIfStrongAgainst(mon.getTypes().get(0)) 
        || getMoveType(moveIndex).checkIfStrongAgainst(mon.getTypes().get(1))) {
            return 2;
        }
        if (getMoveType(moveIndex).checkIfWeakAgainst(mon.getTypes().get(0)) 
        || getMoveType(moveIndex).checkIfWeakAgainst(mon.getTypes().get(1))) {
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

    public void setIsDead(Boolean bool) {
        isDead = bool;
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
