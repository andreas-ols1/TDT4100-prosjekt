package PokemonShowdown;

import java.util.Arrays;
import java.util.Collection;

public class Move {
    
    private String name;
    private Type type;
    private int damage;
    private double accuracy;
    private double heal;
    private boolean attackBoost;
    private Collection<String> validMoves = Arrays.asList("swords dance", "recover", "hydro pump", 
    "slash", "flamethrower", "earthquake", "solar beam", "ice beam", "skull bash", "thunderbolt", "surf");

    public Move(String name) {
        checkValidMove(name);
        switch (name.toLowerCase()) {
            case "slash":
                setMoveAttributes(name, "normal",70, 1,0, false);
                break;
            case "flamethrower":
                setMoveAttributes(name, "fire",95, 1, 0, false);
                break;
            case "earthquake":
                setMoveAttributes(name, "ground",100, 1, 0, false);
                break;
            case "solar beam":
                setMoveAttributes(name, "grass",120, 0.8, 0, false);
                break;
            case "hydro pump":
                setMoveAttributes(name, "water",120, 0.8, 0, false);
                break;
            case "skull bash":
                setMoveAttributes(name, "normal",100, 1, 0, false);
                break;
            case "ice beam":
                setMoveAttributes(name, "ice",90, 1, 0, false);
                break;
            case "swords dance":
                setMoveAttributes(name, "normal",0, 1, 0, true);
                break;
            case "recover":
                setMoveAttributes(name, "normal",0, 1, 0.5, false);
                break;
            case "thunderbolt":
                setMoveAttributes(name, "electric", 95, 1, 0, false);
            case "surf":
                setMoveAttributes(name, "water", 95, 1, 0, false);
        }
    }

    private void setMoveAttributes(String name, String type, int damage, double accuracy, double heal, boolean attackBoost) {
        this.name = name;
        this.type = new Type(type);
        this.damage = damage;
        this.accuracy = accuracy;
        this.heal = heal;
        this.attackBoost = attackBoost;
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

    public boolean getAttackBoost() {
        return attackBoost;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public String toString() {
        return "Name: " + name + 
        ", Type: " + type.getName() + 
        ", Base damage: " + damage + "\n" +
        ", Attack boost: " + attackBoost + 
        ", Heal: " + heal + "\n";
    }

    public static void main(String[] args) {
        Move flammekaster = new Move("flamethrower");
        System.out.println(flammekaster);
    }

}