package PokemonShowdown;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import java.io.File;

public class PokemonShowdownController {
    
    private List<Pokemon> team = new ArrayList<>();
    private List<Button> selectedPokemonButtons = new ArrayList<>();
    private final static String filePath = "./src/main/resources/PokemonShowdown/teams";
    private final static int teamSize = 4;

    @FXML
    private Button charizard,venusaur,blastoise,pikachu,nidoking,arcanine,alakazam,machamp,golem,slowbro,gengar,gyarados,aerodactyl,
    snorlax,articuno,zapdos,moltres,dragonite,mewtwo,mew,megarayquaza,amoonguss,deselectlast,createteam;

    @FXML
    private TextField teamName;

    @FXML
    private GridPane teamList;

    @FXML
    private void initialize() {
        getTeamsFromFile();
    }
    
    @FXML
    private void addTeamMember(ActionEvent event){
        if (team.size() < teamSize) {
            Button button = (Button) event.getSource();
            team.add(new Pokemon(button.getText().toLowerCase()));
            selectedPokemonButtons.add(button);
            button.setDisable(true);
        }
        System.out.println(selectedPokemonButtons);
        System.out.println(team);
    }

    @FXML
    private void deselectLast() {
        if (selectedPokemonButtons.size() > 0) {
            selectedPokemonButtons.get(selectedPokemonButtons.size()-1).setDisable(false);
            selectedPokemonButtons.remove(selectedPokemonButtons.size()-1);
            team.remove(team.size()-1);
        }
        System.out.println(selectedPokemonButtons);
        System.out.println(team);
    }

    @FXML
    private void handleCreateTeam() throws IOException {
        if (team.size() == teamSize && teamName.getLength() > 0) {
            Team tmp = new Team(teamName.getText(),team);
            selectedPokemonButtons.stream().forEach((button) -> button.setDisable(false));
            team.clear();
            selectedPokemonButtons.clear();
            teamName.clear();
            if (checkButtonPosition()<10 && checkButtonPosition()>=-1) {
                teamList.add(createTeamButton(tmp),0, checkButtonPosition());
            } 
            tmp.write();
        } else if (teamName.getLength() == 0) {
            showWarning("name");
        } else if (team.size() < 4) {
            showWarning("pokemon");
        } 
    }

    @FXML
    private void handleEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            handleCreateTeam();
        }
    }

    private Button createTeamButton(Team team) {
        Button button = new Button(team.getName());
        button.wrapTextProperty().setValue(true);
        button.setStyle("-fx-text-alignment: center;");
        button.setCursor(Cursor.HAND);
        // button.setOnAction((event) -> );
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        return button;
    }

    private int checkButtonPosition() {
        File dir = new File(filePath);
        return dir.list().length;
    }

    private void getTeamsFromFile() {
        File dir = new File(filePath);
        String [] teams = dir.list();
        List<String> teamNames = new ArrayList<>();
        Arrays.stream(teams)
        .map((str)-> str.split("\\.")[0]).
        forEach((str) -> teamNames.add(str));
        if (teamNames.size()>10) {
            IntStream.range(0, 10).
            forEach((i) -> teamList.add(createTeamButton(new Team(teamNames.get(i), new ArrayList<Pokemon>())), 0, i));
        } else {
            IntStream.range(0, teamNames.size()).
            forEach((i)->teamList.add( createTeamButton(new Team(teamNames.get(i), new ArrayList<Pokemon>())), 0, i));
        }
    }

    private void showWarning(String type) {
        if (type.equals("name")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No team name");
            alert.setHeaderText("No team name entered");
            alert.setContentText("You need to enter a name for your team.");
            alert.showAndWait();
        } else if (type.equals("pokemon")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Few Pokémon");
            alert.setHeaderText("Not enough Pokémon");
            alert.setContentText("You must select 4 Pokémon for your team.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unknown error");
            alert.setContentText("Something went wrong.");
            alert.showAndWait();
        }
    }
}
