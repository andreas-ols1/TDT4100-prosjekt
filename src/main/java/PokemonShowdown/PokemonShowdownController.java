package PokemonShowdown;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PokemonShowdownController {
    
    private List<Pokemon> team = new ArrayList<>();
    private List<Button> selectedPokemonButtons = new ArrayList<>();

    @FXML
    Button charizard,venusaur,blastoise,pikachu,nidoking,arcanine,alakazam,machamp,golem,slowbro,gengar,gyarados,aerodactyl,
    snorlax,articuno,zapdos,moltres,dragonite,mewtwo,mew,megarayquaza,amoonguss,deselectlast,createteam;

    @FXML
    TextField teamname;
    
    @FXML
    private void addTeamMember(ActionEvent event){
        if (team.size() < 4) {
            Button button = (Button) event.getSource();
            team.add(new Pokemon(button.getText().toLowerCase()));
            selectedPokemonButtons.add(button);
            button.setDisable(true);
        }
        System.out.println(selectedPokemonButtons);
        System.out.println(team);
    }

    @FXML
    public void deselectLast() {
        if (selectedPokemonButtons.size() > 0) {
            selectedPokemonButtons.get(selectedPokemonButtons.size()-1).setDisable(false);
            selectedPokemonButtons.remove(selectedPokemonButtons.size()-1);
            team.remove(team.size()-1);
        }
        System.out.println(selectedPokemonButtons);
        System.out.println(team);
    }

    @FXML
    public void createTeam() {
        if (team.size() == 4 && teamname.getLength() > 0) {
            new Team(teamname.getText(),team);
        }
    }

    @FXML
    public void createTeamButton(Team team) {

    }

}
