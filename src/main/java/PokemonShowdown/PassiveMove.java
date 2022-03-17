package PokemonShowdown;

public class PassiveMove extends Move {
    
    private boolean statBoost = false;
    private boolean heal = false;

    public PassiveMove(String name) {
        this.name = name;
        if (name.equals("swords dance")) {
            type = new Type("normal");
            statBoost = true;
        }
        if (name.equals("recover")) {
            type = new Type("normal");
            heal = true;
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + 
        "Type: " + type.getName() + 
        "Stat boost: " + statBoost + 
        "Heal: " + heal + "\n";
    }
}
