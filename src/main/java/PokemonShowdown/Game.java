package PokemonShowdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Game {
    
    private List<Pokemon> playerTeam = new ArrayList<>();
    private List<Pokemon> opponentTeam = new ArrayList<>();
    private Pokemon activeMon;
    private Pokemon activeOpponentMon;
    private final List<String> validPokemon = Arrays.asList("Venusaur","Charizard","Blastoise","Pikachu",
    "Nidoking","Arcanine","Alakazam","Machamp","Golem","Slowbro","Gengar","Gyarados","Aerodactyl","Snorlax",
    "Articuno","Zapdos","Moltres","Dragonite","Mewtwo","Mew","Rayquaza","Amoonguss");

    public Game(List<Pokemon> playerTeam) {
        playerTeam.stream().forEach((mon) -> this.playerTeam.add(mon));
        getRandomTeam().stream().forEach((mon) -> opponentTeam.add(new Pokemon(mon))); 
        activeOpponentMon = opponentTeam.get(ThreadLocalRandom.current().nextInt(4));
    }

    public Game() {
        getRandomTeam().stream().forEach((mon) -> playerTeam.add(new Pokemon(mon)));
        getRandomTeam().stream().forEach((mon) -> opponentTeam.add(new Pokemon(mon)));
        activeOpponentMon = opponentTeam.get(ThreadLocalRandom.current().nextInt(4));
    }
    
    public void setActiveMon(int index) {
        if (activeMon != null) activeMon.revertStatChanges();
        activeMon = playerTeam.get(index);
        System.out.println("I choose you, " + activeMon.getName() + "!\n");
    }

    public void setActiveOpponentMon() {
        int count = 0;
        for (Pokemon mon : getOpponentTeam()) {
            if (mon.isDead()) count ++;
        }
        if (count < 4) {
            Pokemon mon = opponentTeam.get(ThreadLocalRandom.current().nextInt(4));
            while (mon.isDead()) {
                mon = opponentTeam.get(ThreadLocalRandom.current().nextInt(4));
            }
            activeOpponentMon = mon;
        }
    }

    private List<String> getRandomTeam() {
        List<String> team = new ArrayList<>();
        IntStream.range(0, 4).forEach(i -> {
            String mon = validPokemon.get(new Random().nextInt(validPokemon.size()));
            while (team.contains(mon)) {
                mon = validPokemon.get(new Random().nextInt(validPokemon.size()));
            }
            team.add(mon); });
        return team;
    }

    public List<Pokemon> getPlayerTeam() {
        return playerTeam;
    }

    public List<Pokemon> getOpponentTeam() {
        return opponentTeam;
    }

    public Pokemon getActiveMon() {
        return activeMon;
    }
    
    public Pokemon getActiveOpponentMon() {
        return activeOpponentMon;
    }

    public List<String> getValidPokemon() {
        return validPokemon;
    }

    public boolean gameEnded() {
        int playerCount = 0;
        int opponentCount = 0;
        for (Pokemon mon : this.getPlayerTeam()) {
            if(mon.isDead()) playerCount ++;
        }
        for (Pokemon mon : this.getOpponentTeam()) {
            if(mon.isDead()) opponentCount ++;
        }
        return (playerCount == 4 || opponentCount == 4);
    }

    @Override
    public String toString() {
        return "New game\n---------\n" +
        "Players team:\n" + playerTeam +
        "\n\nOpponents team:\n" + opponentTeam +
        "\n---------\n";
    }

    public static void main(String[] args) {
        
    }

}
