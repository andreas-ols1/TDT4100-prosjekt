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
    private Collection<String> validMoves = Arrays.asList("swords dance","recover","hydro pump","slash","flamethrower",
    "earthquake","solar beam","ice beam","skull bash","thunderbolt","surf","sludge bomb","air slash","cross chop","thunder punch",
    "ice punch","fire punch","psychic","rock slide","shadow ball","dragon pulse","roost");

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
                break;
            case "surf":
                setMoveAttributes(name, "water", 95, 1, 0, false);
                break;
            case "sludge bomb":
                setMoveAttributes(name, "poison", 90, 1, 0, false);
                break;
            case "air slash":
                setMoveAttributes(name, "flying", 75, 0.95, 0, false);
                break;
            case "cross chop":
                setMoveAttributes(name, "fighting", 100, 0.8, 0, false);
                break;
            case "thunder punch":
                setMoveAttributes(name, "electric", 75, 1, 0, false);
                break;
            case "fire punch":
                setMoveAttributes(name, "fire", 75, 1, 0, false);
                break;
            case "ice punch":
                setMoveAttributes(name, "ice", 75, 1, 0, false);
                break;
            case "psychic":
                setMoveAttributes(name, "psychic", 90, 1, 0, false);
                break;
            case "rock slide":
                setMoveAttributes(name, "rock", 75, 0.9, 0, false);
                break;
            case "shadow ball":
                setMoveAttributes(name, "ghost", 80, 1, 0, false);
                break;
            case "dragon pulse":
                setMoveAttributes(name, "dragon", 85, 1, 0, false);
                break;
            case "roost":
                setMoveAttributes(name, "flying", 0, 1, 0.5, false);
                break;
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