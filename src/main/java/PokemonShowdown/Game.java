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
    private final List<String> validPokemon = Arrays.asList("venusaur","charizard","blastoise","pikachu",
    "nidoking","arcanine","alakazam","machamp","golem","slowbro","gengar","gyarados","aerodactyl","snorlax",
    "articuno","zapdos","moltres","dragonite","mewtwo","mew","rayquaza","amoonguss");

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
        activeMon = playerTeam.get(index);
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

    public List<String> getRandomTeam() {
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

    @Override
    public String toString() {
        return "Players team:\n" + playerTeam
        + "\nActive mon: " + activeMon
        + "\n\nOpponents team:\n" + opponentTeam
        + "\nActive opponent mon: " + activeOpponentMon;
    }

    public static void main(String[] args) {

    }

}
