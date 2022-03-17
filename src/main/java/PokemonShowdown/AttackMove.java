package PokemonShowdown;

public class AttackMove extends Move {
    int damage;

    public AttackMove(String name) {
        this.name = name;
        if (name.equals("slash")) {
            type = new Type("normal");
            damage = 70;
        }
        if (name.equals("flamethrower")) {
            type = new Type("fire");
            damage = 95;
        }
        if (name.equals("earthquake")) {
            type = new Type("ground");
            damage = 100;
        }
        if (name.equals("solar beam")) {
            type = new Type("grass");
            damage = 120;
        }
        if (name.equals("hydro pump")) {
            type = new Type("water");
            damage = 120;
        }
        if (name.equals("skull bash")) {
            type = new Type("normal");
            damage = 100;
        }
        if (name.equals("ice beam")) {
            type = new Type("ice");
            damage = 90;
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + 
        ", Type: " + type.getName() + 
        ", Base damage: " + damage + "\n";
    }

}
