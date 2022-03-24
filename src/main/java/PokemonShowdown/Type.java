package PokemonShowdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Type {
    
    private String name;
    private List<String> strongAgainst = new ArrayList<>();
    private List<String> weakAgainst = new ArrayList<>();
    private List<String> validTypes = Arrays.asList("fire", "water", "grass", "ground", "normal", "ice");

    public Type(String type) {
        checkValidType(type);
        if (type.equals("fire")) {
            setFireType();
        }
        if (type.equals("water")) {
            setWaterType();
        }
        if (type.equals("grass")) {
            setGrassType();
        }
        if (type.equals("normal")) {
            setNormalType();
        }
        if (type.equals("ground")) {
            setGroundType();
        }
        if (type.equals("ice")) {
            setIceType();
        }
    }

    private void checkValidType(String type) {
        if (!(validTypes.contains(type))) throw new IllegalArgumentException("Not a valid type");
    }

    private void setFireType() {
        setTypeAttributes("fire", Arrays.asList("grass","ice"), Arrays.asList("water","fire"));
    }

    private void setWaterType() {
        setTypeAttributes("water", Arrays.asList("fire","ground"), Arrays.asList("grass","water"));
    }

    private void setGrassType() {
        setTypeAttributes("grass", Arrays.asList("water","ground"), Arrays.asList("fire","grass"));
    }

    private void setGroundType() {
        setTypeAttributes("ground", Arrays.asList("fire"), Arrays.asList("grass"));
    }

    private void setNormalType() {
        this.name = "normal";
    }

    private void setIceType() {
        setTypeAttributes("ice", Arrays.asList("ground","grass"), Arrays.asList("fire","water","ice"));
    }

    private void setTypeAttributes(String name, List<String> strongAgainst, List<String> weakAgainst) {
        this.name = name;
        this.strongAgainst = strongAgainst;
        this.weakAgainst = weakAgainst;
    }

    public String getName() {
        return this.name;
    }

    public boolean checkIfStrongAgainst(Type type) {
        return strongAgainst.contains(type.getName());
    }

    public boolean checkIfWeakAgainst(Type type) {
        return weakAgainst.contains(type.getName());
    }

    @Override
    public String toString() {
        return "Name: " + name + 
        "\nStrong against: " + strongAgainst +
        "\nWeak against: " + weakAgainst;

    }   

    // public static void main(String[] args) {
    //     Type fire = new Type("fire");
    //     System.out.println(fire.che);
    // }

}
