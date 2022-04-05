package PokemonShowdown;

import java.util.Arrays;
import java.util.Collection;

public class Move {
    
    private String name;
    private Type type;
    private int damage;
    private double accuracy;
    private double heal;
    private double attackBoost;
    private double speedBoost;
    private Collection<String> validMoves = Arrays.asList("swords dance","recover","hydro pump","slash","flamethrower",
    "earthquake","solar beam","ice beam","skull bash","thunderbolt","surf","sludge bomb","air slash","cross chop","thunder punch",
    "ice punch","fire punch","psychic","rock slide","shadow ball","dragon pulse","roost","agility","dragon dance","v-create","dragons ascent");

    public Move(String name) {
        checkValidMove(name);
        switch (name.toLowerCase()) {
            case "slash" -> setMoveAttributes(name, "normal",70, 1,0, 0,0);
            case "flamethrower" -> setMoveAttributes(name, "fire",95, 1, 0, 0,0);
            case "earthquake" -> setMoveAttributes(name, "ground",100, 1, 0, 0,0);
            case "solar beam" -> setMoveAttributes(name, "grass",120, 0.8, 0, 0,0);
            case "hydro pump" -> setMoveAttributes(name, "water",120, 0.8, 0, 0,0);
            case "skull bash" -> setMoveAttributes(name, "normal",100, 1, 0, 0,0);
            case "ice beam" -> setMoveAttributes(name, "ice",90, 1, 0, 0,0);
            case "swords dance" -> setMoveAttributes(name, "normal",0, 1, 0, 1,0);
            case "recover" -> setMoveAttributes(name, "normal",0, 1, 0.5, 0,0);
            case "thunderbolt" -> setMoveAttributes(name, "electric", 95, 1, 0, 0,0);
            case "surf" -> setMoveAttributes(name, "water", 95, 1, 0, 0,0);
            case "sludge bomb" -> setMoveAttributes(name, "poison", 90, 1, 0, 0,0);
            case "air slash" -> setMoveAttributes(name, "flying", 75, 0.95, 0, 0,0);
            case "cross chop" -> setMoveAttributes(name, "fighting", 100, 0.8, 0, 0,0);
            case "thunder punch" -> setMoveAttributes(name, "electric", 75, 1, 0, 0,0);
            case "fire punch" -> setMoveAttributes(name, "fire", 75, 1, 0, 0,0);
            case "ice punch" -> setMoveAttributes(name, "ice", 75, 1, 0, 0,0);
            case "psychic" -> setMoveAttributes(name, "psychic", 90, 1, 0, 0,0);
            case "rock slide" -> setMoveAttributes(name, "rock", 75, 0.9, 0, 0,0);
            case "shadow ball" -> setMoveAttributes(name, "ghost", 80, 1, 0, 0,0);
            case "dragon pulse" -> setMoveAttributes(name, "dragon", 85, 1, 0, 0,0);
            case "roost" -> setMoveAttributes(name, "flying", 0, 1, 0.5, 0,0);
            case "agility" -> setMoveAttributes(name, "psychic", 0, 1, 0, 0, 1);
            case "dragon dance" -> setMoveAttributes(name, "dragon", 0, 1, 0, 0.5, 0.5);
            case "v-create" -> setMoveAttributes(name, "fire", 180, 0.95, 0, -0.5, -0.5);
            case "dragons ascent" -> setMoveAttributes(name, "flying", 120, 1, 0, 0, 0);
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