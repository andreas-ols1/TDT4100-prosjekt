package PokemonShowdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {
    
    private List<Pokemon> playerTeam = new ArrayList<>();
    private List<Pokemon> opponentTeam = new ArrayList<>();
    private Pokemon activeMon;
    private Pokemon activeOpponentMon;
    private final List<String> validPokemon = Arrays.asList("venusaur","charizard","blastoise","pikachu","sandslash","nidoqueen",
    "nidoking","golduck","primeape","arcanine","poliwrath","alakazam","machamp","tentacruel","golem","slowbro","gengar","exeggutor",
    "rhydon","starmie","gyarados","kabutops","aerodactyl","snorlax","articuno","zapdos","moltres","dragonite","mewtwo","mew");

    public Game(List<String> playerTeam) {
        playerTeam.stream().forEach((mon) -> this.playerTeam.add(new Pokemon(mon)));
        getRandomTeam().stream().forEach((mon) -> opponentTeam.add(new Pokemon(mon))); 
        activeOpponentMon = opponentTeam.get(0);
    }
    
    private void setActiveMon(int index) {
        activeMon = playerTeam.get(index);
    }

    public List<String> getRandomTeam() {
        List<String> team = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String mon = validPokemon.get(new Random().nextInt(validPokemon.size()));
            while (team.contains(mon)) {
                mon = validPokemon.get(new Random().nextInt(validPokemon.size()));
            }
            team.add(mon);
        }
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
        Game game = new Game(Arrays.asList("blastoise", "golem", "arcanine"));
        game.setActiveMon(2);
        System.out.println(game);
        System.out.println(game.getActiveMon());
        System.out.println(game.getActiveMon().getHp());
        System.out.println("");
        System.out.println(game.getActiveOpponentMon());
        System.out.println(game.getActiveOpponentMon().getHp());
        System.out.println("");
        game.getActiveMon().attack(game.getActiveOpponentMon(), 3);
        System.out.println(game.getActiveMon() + " used " + game.getActiveMon().getMoves().get(3).getName());
        System.out.println(game.getActiveMon());
        System.out.println(game.getActiveMon().getHp());
        System.out.println("");
        System.out.println(game.getActiveOpponentMon());
        System.out.println(game.getActiveOpponentMon().getHp());
        System.out.println("");
        game.getActiveOpponentMon().attack(game.getActiveMon(), 1);
        System.out.println(game.getActiveOpponentMon() + " used " + game.getActiveOpponentMon().getMoves().get(1).getName());
        System.out.println(game.getActiveMon());
        System.out.println(game.getActiveMon().getHp());
        System.out.println("");
        System.out.println(game.getActiveOpponentMon());
        System.out.println(game.getActiveOpponentMon().getHp());
        System.out.println("");
    }

}
