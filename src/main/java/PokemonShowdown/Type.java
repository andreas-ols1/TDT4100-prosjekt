package PokemonShowdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Type {
    
    private String name;
    private Collection<String> strongAgainst = new ArrayList<>();
    private Collection<String> immune = new ArrayList<>();
    private Collection<String> weakAgainst  = new ArrayList<>();
    private final Collection<String> validTypes = Arrays.asList("Fire","Water","Grass","Ground","Normal","Ice","Electric","Poison",
    "Flying","None","Rock","Fighting","Psychic","Ghost","Dragon");

    public Type(String type) {
        checkValidType(type);
        switch (type) {
            case "Fire" -> setFireType();
            case "Water" -> setWaterType();
            case "Grass" -> setGrassType();
            case "Normal" -> setNormalType();
            case "Ground" -> setGroundType();
            case "Ice" -> setIceType();
            case "Electric" -> setElectricType();
            case "None" -> setTypeToNone();
            case "Poison" -> setPoisonType();
            case "Flying" -> setFlyingType();
            case "Rock" -> setRockType();
            case "Fighting" -> setFightingType();
            case "Psychic" -> setPsychicType();
            case "Ghost" -> setGhostType();
            case "Dragon" -> setDragonType();
        }

    }

    private void checkValidType(String type) {
        if (!(validTypes.contains(type))) throw new IllegalArgumentException("Not a valid type");
    }

    private void setFireType() {
        setTypeAttributes("Fire", Arrays.asList("Grass","Ice"), Arrays.asList("Water","Fire","Rock","Dragon"), Arrays.asList());
    }

    private void setWaterType() {
        setTypeAttributes("Water", Arrays.asList("Fire","Ground","Rock"), Arrays.asList("Grass","Water","Dragon"), Arrays.asList());
    }

    private void setGrassType() {
        setTypeAttributes("Grass", Arrays.asList("Water","Ground","Rock"), Arrays.asList("Fire","Grass","Poison","Flying","Dragon"), Arrays.asList());
    }

    private void setGroundType() {
        setTypeAttributes("Ground", Arrays.asList("Fire","Electric","Poison","Rock"), Arrays.asList("Grass"), Arrays.asList("Flying"));
    }

    private void setNormalType() {
        setTypeAttributes("Normal", Arrays.asList(), Arrays.asList("Rock"), Arrays.asList("Ghost"));
    }

    private void setIceType() {
        setTypeAttributes("Ice", Arrays.asList("Ground","Grass","Flying","Dragon"), Arrays.asList("Fire","Water","Ice"), Arrays.asList());
    }

    private void setElectricType() {
        setTypeAttributes("Electric", Arrays.asList("Water","Flying"), Arrays.asList("Grass", "Electric","Dragon"), Arrays.asList("Ground"));
    }

    private void setPoisonType() {
        setTypeAttributes("Poison", Arrays.asList("Grass"), Arrays.asList("Poison","Rock","Ghost"), Arrays.asList());
    }

    private void setFlyingType() {
        setTypeAttributes("Flying", Arrays.asList("Grass","Fighting"), Arrays.asList("Electric","Rock"), Arrays.asList());
    }

    private void setRockType() {
        setTypeAttributes("Rock", Arrays.asList("Fire","Ice","Flying"), Arrays.asList("Fighting","Ground"), Arrays.asList());
    }

    private void setFightingType() {
        setTypeAttributes("Fighting", Arrays.asList("Normal","Ice","Rock"), Arrays.asList("Poison","Flying","Psychic"), Arrays.asList("Ghost"));
    }

    private void setPsychicType() {
        setTypeAttributes("Psychic", Arrays.asList("Fighting","Poison"), Arrays.asList("Psychic"), Arrays.asList());
    }

    private void setGhostType() {
        setTypeAttributes("Ghost", Arrays.asList("Psychic","Ghost"), Arrays.asList(), Arrays.asList("Normal"));
    }

    private void setDragonType() {
        setTypeAttributes("Dragon", Arrays.asList("Dragon"), Arrays.asList(), Arrays.asList());
    }

    private void setTypeToNone() {
        name = "None";
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

    public boolean checkIfImmune(Type type) {
        if (immune.contains(type.getName())) return true;
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}