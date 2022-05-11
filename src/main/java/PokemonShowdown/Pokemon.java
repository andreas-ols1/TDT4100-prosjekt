package PokemonShowdown;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Pokemon implements PokemonFileHandling {
    
    private String name;
    private List<Type> types = new ArrayList<>();
    private List<Move> moves = new ArrayList<>();
    private int attack, orgAttack, defence, speed, orgSpeed, maxHp, hp;
    private boolean isDead = false, crit;
    private double attackBoost = 1, speedBoost = 1;
    public final static List<String> validPokemon = Arrays.asList("Venusaur","Charizard","Blastoise","Pikachu",
    "Nidoqueen","Nidoking","Clefable","Vileplume","Arcanine","Poliwrath","Alakazam","Machamp","Tentacruel","Golem",
    "Slowbro","Muk","Gengar","Electrode","Exeggutor","Starmie","Gyarados","Lapras","Vaporeon","Jolteon","Flareon",
    "Omastar","Kabutops","Aerodactyl","Snorlax","Articuno","Zapdos","Moltres","Dragonite","Mewtwo","Mew","Meganium",
    "Typhlosion","Feraligatr","Crobat","Ampharos","Quagsire","Espeon","Umbreon","Forretress","Steelix","Scizor",
    "Heracross","Mantine","Skarmory","Houndoom","Kingdra","Smeargle","Blissey","Raikou","Entei","Suicune","Tyranitar",
    "Lugia","Ho-Oh","Celebi","Rayquaza","Amoonguss","Kevin Lauren");
    private final static String filepath = "./src/main/resources/PokemonShowdown/mons.txt";

    public Pokemon(String name) throws IOException {
        checkValidPokemon(name);
        setPokemon(name);
    }

    private void setPokemon(String name) throws IOException {
        List<String> atb = read(name);
        setPokemonDetails(atb.get(0), Arrays.asList(atb.get(1),atb.get(2)), Integer.valueOf(atb.get(3)), Integer.valueOf(atb.get(4)), Integer.valueOf(atb.get(5)), Integer.valueOf(atb.get(6)), Arrays.asList(atb.get(7),atb.get(8),atb.get(9),atb.get(10)));
    }

    private void checkValidPokemon(String name) {
        if (!(validPokemon.contains(name))) {
            throw new IllegalArgumentException("Not a valid pokémon");
        }
    }

    private void setPokemonDetails(String name, List<String> types, int maxHp, int attack, int defence, int speed, List<String> moves) {
        this.name = name;
        types.stream().forEach(type -> this.types.add(new Type(type)));
        this.maxHp = maxHp;
        hp = maxHp;
        this.attack = attack;
        orgAttack = attack;
        this.defence = defence;
        this.speed = speed;
        orgSpeed = speed;
        moves.stream().forEach(move -> {
            try {
                this.moves.add(new Move(move));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void attack(Pokemon target, int moveIndex) {
        double effectiveness = getEffectiveness(target, moveIndex); // How effective the move's type is on the opponents types. Can multiply damage output by 0.25, 0.5, 1, 2 or 4.
        double stab = getStab(moveIndex); // stab = same type attack bonus. A moves power is multiplied by 1.5 if it is the same type as the pokemon using it.
        if (checkIfHit(moveIndex)) { // Checks if the move hit the oppponent. This is based on the moves accuracy. If a move misses, it deals no damage.
            if (!(getMove(moveIndex).getDamage() == 0)) { // Checks if a passive move was used - a move that does not deal any damage to the opponent
                if (!checkImmunity(target, moveIndex)) { // Checks if the opponent is immune to the move used based on their types.
                    target.takeDamage(calculateDamage(target, moveIndex, effectiveness, stab));
                    heal(moveIndex);
                    speedBoost(moveIndex);
                    attackBoost(moveIndex);
                    System.out.println(getName() + " used " + getMove(moveIndex).getName() + " on " + target.getName());
                    if (getEffectiveness(target, moveIndex) > 1) System.out.println("It's super effective!");
                    if (getEffectiveness(target, moveIndex) < 1) System.out.println("It was not very effective...");
                    if (crit) System.out.println("It's a crit!"); // A critical hit is a random boost to power that has a 1/16 chanse of occuring every time a pokemon uses an attack.
                    System.out.println("");
                }
                else System.out.println(getName() + " tried to use " + getMove(moveIndex).getName() + ",\nbut " + target.getName() + " is immune to " + getMove(moveIndex).getType().getName() + ",\nso the attack did no damage!\n");
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
        double randomness = ThreadLocalRandom.current().nextDouble(0.85,1); // Decides a range in which attack damage can be. It is random every time.
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

    public void revertStatChanges() { // Ensures that a pokemon does not keep its stat boosts if it is swapped out.
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

    @Override
    public List<String> read(InputStream is, String mon) {
        try (Scanner sc = new Scanner(is)) {
            List<String> monAttributes = new ArrayList<>();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine(); 
                if (nextLine.startsWith(mon)) {
                    monAttributes = Arrays.asList(nextLine.split(";"));
                }
            }
            return monAttributes;
        }
    }

    @Override
    public List<String> read(String mon) throws IOException {
        String path = filepath;
        try (var is = new FileInputStream(path)) {
            return read(is, mon);
        }
    }
}