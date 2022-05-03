package PokemonShowdown;

import java.util.Arrays;
import java.util.Collection;

public class Move {
    
    private String name;
    private Type type;
    private int damage;
    private double accuracy, heal, attackBoost, speedBoost;
    private final Collection<String> validMoves = Arrays.asList("Swords Dance","Recover","Hydro Pump","Slash","Flamethrower",
    "Earthquake","Solar Beam","Ice Beam","Skull Bash","Thunderbolt","Surf","Sludge Bomb","Air Slash","Cross Chop","Thunder Punch",
    "Ice Punch","Fire Punch","Psychic","Rock Slide","Shadow Ball","Dragon Pulse","Roost","Agility","Dragon Dance","V-create",
    "Dragons Ascent","Synthesis","Giga Drain","Ring mandem","Jacke sykebil","Trench warfare","Jeg gætter trynet ditt bro");

    public Move(String name) {
        checkValidMove(name);
        switch (name) {
            case "Slash" -> setMoveAttributes(name, "Normal",70, 1,0, 0,0);
            case "Flamethrower" -> setMoveAttributes(name, "Fire",95, 1, 0, 0,0);
            case "Earthquake" -> setMoveAttributes(name, "Ground",100, 1, 0, 0,0);
            case "Solar Beam" -> setMoveAttributes(name, "Grass",120, 0.8, 0, 0,0);
            case "Hydro Pump" -> setMoveAttributes(name, "Water",120, 0.8, 0, 0,0);
            case "Skull Bash" -> setMoveAttributes(name, "Normal",100, 1, 0, 0,0);
            case "Ice Beam" -> setMoveAttributes(name, "Ice",90, 1, 0, 0,0);
            case "Swords Dance" -> setMoveAttributes(name, "Normal",0, 1, 0, 1,0);
            case "Recover" -> setMoveAttributes(name, "Normal",0, 1, 0.5, 0,0);
            case "Thunderbolt" -> setMoveAttributes(name, "Electric", 95, 1, 0, 0,0);
            case "Surf" -> setMoveAttributes(name, "Water", 95, 1, 0, 0,0);
            case "Sludge Bomb" -> setMoveAttributes(name, "Poison", 90, 1, 0, 0,0);
            case "Air Slash" -> setMoveAttributes(name, "Flying", 75, 0.95, 0, 0,0);
            case "Cross Chop" -> setMoveAttributes(name, "Fighting", 100, 0.8, 0, 0,0);
            case "Thunder Punch" -> setMoveAttributes(name, "Electric", 75, 1, 0, 0,0);
            case "Fire Punch" -> setMoveAttributes(name, "Fire", 75, 1, 0, 0,0);
            case "Ice Punch" -> setMoveAttributes(name, "Ice", 75, 1, 0, 0,0);
            case "Psychic" -> setMoveAttributes(name, "Psychic", 90, 1, 0, 0,0);
            case "Rock Slide" -> setMoveAttributes(name, "Rock", 75, 0.9, 0, 0,0);
            case "Shadow Ball" -> setMoveAttributes(name, "Ghost", 80, 1, 0, 0,0);
            case "Dragon Pulse" -> setMoveAttributes(name, "Dragon", 85, 1, 0, 0,0);
            case "Roost" -> setMoveAttributes(name, "Flying", 0, 1, 0.5, 0,0);
            case "Agility" -> setMoveAttributes(name, "Psychic", 0, 1, 0, 0, 1);
            case "Dragon Dance" -> setMoveAttributes(name, "Dragon", 0, 1, 0, 0.5, 0.5);
            case "V-create" -> setMoveAttributes(name, "Fire", 180, 0.95, 0, -0.5, -0.5);
            case "Dragons Ascent" -> setMoveAttributes(name, "Flying", 120, 1, 0, 0, 0);
            case "Synthesis" -> setMoveAttributes(name, "Grass", 0, 1, 0.5, 0, 0);
            case "Giga Drain" -> setMoveAttributes(name, "Grass", 75, 1, 0.1, 0, 0);
            case "Ring mandem" -> setMoveAttributes(name, "Fighting", 0, 1, 0, 3, 0);
            case "Jacke sykebil" -> setMoveAttributes(name, "Normal", 0, 1, 1, 0, 0);
            case "Trench warfare" -> setMoveAttributes(name, "Fighting", 150, 1, 0, 0, 0);
            case "Jeg gætter trynet ditt bro" -> setMoveAttributes(name, "Dark", 120, 0.8, 0, 0, 0);
        }
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
}