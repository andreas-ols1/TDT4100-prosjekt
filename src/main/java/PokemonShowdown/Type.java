package PokemonShowdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Type {
    
    private String name;
    private Collection<String> strongAgainst = new ArrayList<>();
    private Collection<String> weakAgainst = new ArrayList<>();
    private Collection<String> immune = new ArrayList<>();
    private Collection<String> validTypes = Arrays.asList("fire","water","grass","ground","normal","ice","electric","poison",
    "flying","none","rock","fighting","psychic","ghost","dragon");

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
            case "none":
                setTypeNone();
                break;
            case "poison":
                setPoisonType();
                break;
            case "flying":
                setFlyingType();
                break;
            case "rock":
                setRockType();
                break;
            case "fighting":
                setFightingType();
                break;
            case "psychic":
                setPsychicType();
                break;
            case "ghost":
                setGhostType();
                break;
            case "dragon":
                setDragonType();
                break;
        }

    }

    private void checkValidType(String type) {
        if (!(validTypes.contains(type))) throw new IllegalArgumentException("Not a valid type");
    }

    private void setFireType() {
        setTypeAttributes("fire", Arrays.asList("grass","ice"), Arrays.asList("water","fire","rock","dragon"), Arrays.asList());
    }

    private void setWaterType() {
        setTypeAttributes("water", Arrays.asList("fire","ground","rock"), Arrays.asList("grass","water","dragon"), Arrays.asList());
    }

    private void setGrassType() {
        setTypeAttributes("grass", Arrays.asList("water","ground","rock"), Arrays.asList("fire","grass","poison","flying","dragon"), Arrays.asList());
    }

    private void setGroundType() {
        setTypeAttributes("ground", Arrays.asList("fire","electric","poison","rock"), Arrays.asList("grass"), Arrays.asList("flying"));
    }

    private void setNormalType() {
        setTypeAttributes("normal", Arrays.asList(), Arrays.asList("rock"), Arrays.asList("ghost"));
    }

    private void setIceType() {
        setTypeAttributes("ice", Arrays.asList("ground","grass","flying","dragon"), Arrays.asList("fire","water","ice"), Arrays.asList());
    }

    private void setElectricType() {
        setTypeAttributes("electric", Arrays.asList("water","flying"), Arrays.asList("grass", "electric","dragon"), Arrays.asList("ground"));
    }

    private void setPoisonType() {
        setTypeAttributes("poison", Arrays.asList("grass"), Arrays.asList("poison","rock","ghost"), Arrays.asList());
    }

    private void setFlyingType() {
        setTypeAttributes("flying", Arrays.asList("grass","fighting"), Arrays.asList("electric","rock"), Arrays.asList());
    }

    private void setRockType() {
        setTypeAttributes("rock", Arrays.asList("fire","ice","flying"), Arrays.asList("fighting","ground"), Arrays.asList());
    }

    private void setFightingType() {
        setTypeAttributes("fighting", Arrays.asList("normal","ice","rock"), Arrays.asList("poison","flying","psychic"), Arrays.asList("ghost"));
    }

    private void setPsychicType() {
        setTypeAttributes("psychic", Arrays.asList("fighting","poison"), Arrays.asList("psychic"), Arrays.asList());
    }

    private void setGhostType() {
        setTypeAttributes("ghost", Arrays.asList("psychic","ghost"), Arrays.asList(), Arrays.asList("normal"));
    }

    private void setDragonType() {
        setTypeAttributes("dragon", Arrays.asList("dragon"), Arrays.asList(), Arrays.asList());
    }

    private void setTypeNone() {
        name = "none";
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
        if (strongAgainst.contains(type.getName())) return true;
        return false;
    }

    public boolean checkIfWeakAgainst(Type type) {
        if (weakAgainst.contains(type.getName())) return true;
        return false;
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
