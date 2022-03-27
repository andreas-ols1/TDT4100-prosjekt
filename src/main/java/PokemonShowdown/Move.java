package PokemonShowdown;

import java.util.Arrays;
import java.util.Collection;

public class Move {
    
    private String name;
    private Type type;
    private int damage;
    private boolean heal;
    private boolean statBoost;
    private Collection<String> passiveMoves = Arrays.asList("swords dance", "recover");
    private Collection<String> attackMoves = Arrays.asList("hydro pump", "slash", "flamethrower", "earthquake", "solar beam", "ice beam", "skull bash", "thunderbolt", "surf");

    public Move(String name) {
        switch (name.toLowerCase()) {
            case "slash":
                setMoveAttributes("slash", "normal",70,false, false);
                break;
            case "flamethrower":
                setMoveAttributes("flamethrower", "fire",95, false, false);
                break;
            case "earthquake":
                setMoveAttributes("earthquake", "ground",100, false, false);
                break;
            case "solar beam":
                setMoveAttributes("solar beam", "grass",120, false, false);
                break;
            case "hydro pump":
                setMoveAttributes("hydro pump", "water",120, false, false);
                break;
            case "skull bash":
                setMoveAttributes("skull bash", "normal",100, false, false);
                break;
            case "ice beam":
                setMoveAttributes("ice beam", "ice",90, false, false);
                break;
            case "swords dance":
                setMoveAttributes("swords dance", "normal",0, false, true);
                break;
            case "recover":
                setMoveAttributes("recover", "normal",0, true, false);
                break;
            case "thunderbolt":
                setMoveAttributes("thunderbolt", "electric", 95, false, false);
            case "surf":
                setMoveAttributes("surf", "water", 95, false, false);
        }
    }

    private void setMoveAttributes(String name, String type, int damage, boolean heal, boolean statBoost) {
        this.name = name;
        this.type = new Type(type);
        this.damage = damage;
        this.heal = heal;
        this.statBoost = statBoost;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Name: " + name + 
        ", Type: " + type.getName() + 
        ", Base damage: " + damage + "\n" +
        ", Stat boost: " + statBoost + 
        ", Heal: " + heal + "\n";
    }

    public static void main(String[] args) {
        Move flammekaster = new Move("flamethrower");
        System.out.println(flammekaster);
    }

}