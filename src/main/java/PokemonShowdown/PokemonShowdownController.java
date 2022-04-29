package PokemonShowdown;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.File;

public class PokemonShowdownController {
    
    private List<Pokemon> team = new ArrayList<>();
    private List<Button> selectedPokemonButtons= new ArrayList<>();
    private final static String filePath = "./src/main/resources/PokemonShowdown/teams";
    private final static int teamSize = 4;
    private Team selectedTeam;
    public static Game gameTransferring;

    @FXML
    private Button charizard,venusaur,blastoise,pikachu,nidoking,arcanine,alakazam,machamp,golem,slowbro,gengar,gyarados,aerodactyl,
    snorlax,articuno,zapdos,moltres,dragonite,mewtwo,mew,rayquaza,amoonguss,deselectlast,createteam,playRandomTeam,playSelectedTeam;

    @FXML
    private TextField teamName;

    @FXML
    private GridPane teamList, playerTeamView;

    @FXML
    private TextArea teamViewer;

    @FXML
    private Stage stage;
    
    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    @FXML
    private void initialize() {
        getTeamsFromFile();
    }
    
    @FXML
    private void addTeamMember(ActionEvent ae){
        if (team.size() < teamSize) {
            Button button = (Button) ae.getSource();
            team.add(new Pokemon(button.getText()));
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
        if (!checkValidTeamName(teamName.getText())) showWarning("invalid name");
        else if (team.size() == teamSize && teamName.getLength() > 0) {
            Team tmp = new Team(teamName.getText(),team);
            selectedPokemonButtons.stream().forEach((button) -> button.setDisable(false));
            team.clear();
            selectedPokemonButtons.clear();
            teamName.clear();
            if (checkButtonPosition()<10 && checkButtonPosition()>=-1) {
                teamList.add(createTeamButton(tmp),0, checkButtonPosition());
            } 
            tmp.write();
        } 
        else if (teamName.getLength() == 0) showWarning("no name");
        else if (team.size() < teamSize) showWarning("pokemon");
        
    }

    @FXML
    private void handleEnter(KeyEvent ke) throws IOException {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            handleCreateTeam();
        }
    }

    public void switchScreen(ActionEvent ae, String file) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(file));
        stage = (Stage)((Node) ae.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNewGame(ActionEvent ae) throws IOException {
        if (selectedTeam == null) showWarning("team");
        gameTransferring= new Game(selectedTeam.getMons());
        switchScreen(ae,"PokemonShowdownMainView.fxml");
    }

    @FXML
    private void handleRandomGame(ActionEvent ae) throws IOException {
        gameTransferring= new Game();
        System.out.println(gameTransferring.getPlayerTeam());
        switchScreen(ae,"PokemonShowdownMainView.fxml");
    }

    @FXML
    private void handleSelectTeam(ActionEvent ae) throws IOException {
        Button button = (Button) ae.getSource();
        selectedTeam = new Team(button.getText(), new ArrayList<>());
        List<Pokemon> selectedTeamList = selectedTeam.read();
        selectedTeam.addMons(selectedTeamList);
        teamViewer.setText("Team name: " + button.getText()+"\nPokémon:\n--------\n");
        selectedTeamList.stream().forEach((mon) -> teamViewer.appendText(mon.getName()+"\n"));
    }

    private Button createTeamButton(Team team) {
        Button button = new Button(team.getName());
        button.wrapTextProperty().setValue(true);
        button.setStyle("-fx-text-alignment: center;");
        button.setCursor(Cursor.HAND);
        button.setOnAction((event) -> {
            try {
                handleSelectTeam(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
        String[] teams = dir.list();
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


    private boolean checkValidTeamName(String name) {
        return Pattern.matches("[a-zA-Z0-9]+", name);
    } 

    private void showWarning(String type) {
        if (type.equals("no name")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No team name");
            alert.setHeaderText("No team name entered");
            alert.setContentText("You need to enter a name for your team.");
            alert.showAndWait();
        } else if (type.equals("invalid name")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Invalid team name");
            alert.setHeaderText("Team can only contain alphanumeric characters");
            alert.setContentText("Please use characters a-z, A-Z and 0-9.");
            alert.showAndWait();
        } else if (type.equals("pokemon")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Few Pokémon");
            alert.setHeaderText("Not enough Pokémon");
            alert.setContentText("You must select 4 Pokémon for your team.");
            alert.showAndWait();
        } else if (type.equals("team")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No team selected");
            alert.setHeaderText("No team selected");
            alert.setContentText("You must either select a team, or choose the option to play with a random team");
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
