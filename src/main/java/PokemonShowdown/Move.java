package PokemonShowdown;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Move implements MoveFileHandling {
    
    private String name;
    private Type type;
    private int damage;
    private double accuracy, heal, attackBoost, speedBoost;
    private final static Collection<String> validMoves = Arrays.asList("Swords Dance","Recover","Hydro Pump","Slash","Flamethrower",
    "Earthquake","Solar Beam","Ice Beam","Skull Bash","Thunderbolt","Surf","Sludge Bomb","Air Slash","Cross Chop","Thunder Punch",
    "Ice Punch","Fire Punch","Psychic","Rock Slide","Shadow Ball","Dragon Pulse","Roost","Agility","Dragon Dance","V-create",
    "Dragons Ascent","Synthesis","Giga Drain","Ring mandem","Jacke sykebil","Trench warfare","Jeg gatter trynet ditt bro",
    "Fire Blast","Focus Blast","Wild Charge","Double Edge","Crunch","Brave Bird","Dark Pulse","Bug Buzz","Flash Cannon",
    "X-Scissor","Nasty Plot","Moonblast","Dazzling Gleam","Extrasensory","Meteor Mash","Stone Edge","Soft Boiled","Waterfall",
    "Gunk Shot","Flare Blitz","Shell Smash");
    private final static String filepath = "./src/main/resources/PokemonShowdown/moves.txt";

    public Move(String name) throws IOException {
        checkValidMove(name);
        setMove(name);
    }

    private void setMove(String move) throws IOException {
        List<String> atb = read(move);
        setMoveAttributes(atb.get(0), atb.get(1), Integer.valueOf(atb.get(2)), Double.valueOf(atb.get(3)), Double.valueOf(atb.get(4)), Double.valueOf(atb.get(5)), Double.valueOf(atb.get(6)));
    }

    private void setMoveAttributes(String name, String type, int damage, double accuracy, double heal, double attackBoost, double speedBoost) {
        this.name = name;
        this.type = new Type(type);
        this.damage = damage;
        this.accuracy = accuracy;
        this.heal = heal;
        this.attackBoost = attackBoost;
        this.speedBoost = speedBoost;
    }

    private void checkValidMove(String name) {
        if (!validMoves.contains(name)) throw new IllegalArgumentException("Not a valid move");
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public double getHeal() {
        return heal;
    }

    public double getAttackBoost() {
        return attackBoost;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getSpeedBoost() {
        return speedBoost;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public List<String> read(InputStream is, String move) {
        try (Scanner sc = new Scanner(is)) {
            List<String> moveAttributes = new ArrayList<>();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine(); 
                if (nextLine.startsWith(move)) {
                    moveAttributes = Arrays.asList(nextLine.split(";"));
                }
            }
            return moveAttributes;
        }
    }

    @Override
    public List<String> read(String move) throws IOException {
        String path = filepath;
        try (var is = new FileInputStream(path)) {
            return read(is, move);
        }
    }
}