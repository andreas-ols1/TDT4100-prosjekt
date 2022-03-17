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
        this.name = "fire";
        strongAgainst.add("grass");
        strongAgainst.add("ice");
        weakAgainst.add("water");
        weakAgainst.add("fire");
    }

    private void setWaterType() {
        this.name = "water";
        strongAgainst.add("fire");
        strongAgainst.add("ground");
        weakAgainst.add("grass");
        weakAgainst.add("water");
    }

    private void setGrassType() {
        this.name = "grass";
        strongAgainst.add("water");
        strongAgainst.add("ground");
        weakAgainst.add("fire");
        weakAgainst.add("grass");
    }

    private void setGroundType() {
        this.name = "ground";
        strongAgainst.add("fire");
        weakAgainst.add("grass");
    }

    private void setNormalType() {
        this.name = "normal";
    }

    private void setIceType() {
        this.name = "ice";
        strongAgainst.add("ground");
        strongAgainst.add("grass");
        weakAgainst.add("fire");
        weakAgainst.add("water");
        weakAgainst.add("ice");
    }

    public String getName() {
        return this.name;
    }

    public boolean checkIfStrongAgainst(Type type1, Type type2) {
        for (String typeName : type1.strongAgainst) {
            if (typeName.equals(type2.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfWeakAgainst(Type type1, Type type2) {
        for (String typeName : type1.weakAgainst) {
            if (typeName.equals(type2.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Name: " + name + 
        "\nStrong against: " + strongAgainst +
        "\nWeak against: " + weakAgainst;
    }   
}
