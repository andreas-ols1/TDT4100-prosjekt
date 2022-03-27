package PokemonShowdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Type {
    
    private String name;
    private Collection<String> strongAgainst = new ArrayList<>();
    private Collection<String> weakAgainst = new ArrayList<>();
    private Collection<String> immune = new ArrayList<>();
    private Collection<String> validTypes = Arrays.asList("fire", "water", "grass", "ground", "normal", "ice", "electric");

    public Type(String type) {
        checkValidType(type);
        switch (type.toLowerCase()) {
            case "fire":
                setFireType();
                break;
            case "water":
                setWaterType();
                break;
            case "grass":
                setGrassType();
                break;
            case "normal":
                setNormalType();
                break;
            case "ground":
                setGroundType();
                break;
            case "ice":
                setIceType();
                break;
            case "electric":
                setElectricType();
                break;
        }

    }

    private void checkValidType(String type) {
        if (!(validTypes.contains(type))) throw new IllegalArgumentException("Not a valid type");
    }

    private void setFireType() {
        setTypeAttributes("fire", Arrays.asList("grass","ice"), Arrays.asList("water","fire"), Arrays.asList());
    }

    private void setWaterType() {
        setTypeAttributes("water", Arrays.asList("fire","ground"), Arrays.asList("grass","water"), Arrays.asList());
    }

    private void setGrassType() {
        setTypeAttributes("grass", Arrays.asList("water","ground"), Arrays.asList("fire","grass"), Arrays.asList());
    }

    private void setGroundType() {
        setTypeAttributes("ground", Arrays.asList("fire", "electric"), Arrays.asList("grass"), Arrays.asList());
    }

    private void setNormalType() {
        this.name = "normal";
    }

    private void setIceType() {
        setTypeAttributes("ice", Arrays.asList("ground","grass"), Arrays.asList("fire","water","ice"), Arrays.asList());
    }

    private void setElectricType() {
        setTypeAttributes("electric", Arrays.asList("water"), Arrays.asList("grass", "electric"), Arrays.asList("ground"));
    }

    private void setTypeAttributes(String name, Collection<String> strongAgainst, Collection<String> weakAgainst, Collection<String> immune) {
        this.name = name;
        this.strongAgainst = strongAgainst;
        this.weakAgainst = weakAgainst;
        this.immune = immune;
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
        "\nWeak against: " + weakAgainst +
        "\nImmune: " + immune;

    }   

    public static void main(String[] args) {
        Type fire = new Type("normal");
        System.out.println(fire);
    }

}
