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
    "Flying","None","Rock","Fighting","Psychic","Ghost","Dragon","Dark","Steel","Fairy","Bug");

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
            case "Dark" -> setDarkType();
            case "Steel" -> setSteelType();
            case "Fairy" -> setFairyType();
            case "Bug" -> setBugType();
        }

    }

    private void checkValidType(String type) {
        if (!(validTypes.contains(type))) throw new IllegalArgumentException("Not a valid type");
    }

    private void setFireType() {
        setTypeAttributes("Fire", Arrays.asList("Grass","Ice","Steel","Bug"), Arrays.asList("Water","Fire","Rock","Dragon"), Arrays.asList());
    }

    private void setWaterType() {
        setTypeAttributes("Water", Arrays.asList("Fire","Ground","Rock"), Arrays.asList("Grass","Water","Dragon"), Arrays.asList());
    }

    private void setGrassType() {
        setTypeAttributes("Grass", Arrays.asList("Water","Ground","Rock"), Arrays.asList("Fire","Grass","Poison","Flying","Dragon","Steel","Bug"), Arrays.asList());
    }

    private void setGroundType() {
        setTypeAttributes("Ground", Arrays.asList("Fire","Electric","Poison","Rock","Steel"), Arrays.asList("Grass","Bug"), Arrays.asList("Flying"));
    }

    private void setNormalType() {
        setTypeAttributes("Normal", Arrays.asList(), Arrays.asList("Rock","Steel"), Arrays.asList("Ghost"));
    }

    private void setIceType() {
        setTypeAttributes("Ice", Arrays.asList("Ground","Grass","Flying","Dragon"), Arrays.asList("Fire","Water","Ice","Steel"), Arrays.asList());
    }

    private void setElectricType() {
        setTypeAttributes("Electric", Arrays.asList("Water","Flying"), Arrays.asList("Grass","Electric","Dragon"), Arrays.asList("Ground"));
    }

    private void setPoisonType() {
        setTypeAttributes("Poison", Arrays.asList("Grass","Fairy"), Arrays.asList("Poison","Rock","Ghost","Ground"), Arrays.asList("Steel"));
    }

    private void setFlyingType() {
        setTypeAttributes("Flying", Arrays.asList("Grass","Fighting","Bug"), Arrays.asList("Electric","Rock","Steel"), Arrays.asList());
    }

    private void setRockType() {
        setTypeAttributes("Rock", Arrays.asList("Fire","Ice","Flying","Bug"), Arrays.asList("Fighting","Ground","Steel"), Arrays.asList());
    }

    private void setFightingType() {
        setTypeAttributes("Fighting", Arrays.asList("Normal","Ice","Rock","Dark","Steel"), Arrays.asList("Poison","Flying","Psychic","Bug","Fairy"), Arrays.asList("Ghost"));
    }

    private void setPsychicType() {
        setTypeAttributes("Psychic", Arrays.asList("Fighting","Poison"), Arrays.asList("Psychic","Steel"), Arrays.asList("Dark"));
    }

    private void setGhostType() {
        setTypeAttributes("Ghost", Arrays.asList("Psychic","Ghost"), Arrays.asList("Dark"), Arrays.asList("Normal"));
    }

    private void setDragonType() {
        setTypeAttributes("Dragon", Arrays.asList("Dragon"), Arrays.asList("Steel"), Arrays.asList("Fairy"));
    }

    private void setTypeToNone() {
        name = "None";
    }

    private void setDarkType() {
        setTypeAttributes("Dark", Arrays.asList("Psychic","Ghost"), Arrays.asList("Dark","Fighting","Fairy"), Arrays.asList());
    }

    private void setSteelType() {
        setTypeAttributes("Steel", Arrays.asList("Fairy","Rock","Ice"), Arrays.asList("Fire","Water","Steel","Electric"), Arrays.asList());
    }

    private void setFairyType() {
        setTypeAttributes("Fairy", Arrays.asList("Dark","Dragon","Fighting"), Arrays.asList("Steel","Fire","Poison"), Arrays.asList());
    }

    private void setBugType() {
        setTypeAttributes("Bug", Arrays.asList("Grass","Psychic","Dark"), Arrays.asList("Steel","Fire","Fighting","Poison","Flying","Ghost","Fairy"), Arrays.asList());
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