package PokemonShowdown;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class PokemonShowdownController {
    
    private List<Pokemon> team = new ArrayList<>();
    private List<Button> selectedPokemonButtons= new ArrayList<>();
    private List<String> teamNameList = new ArrayList<>();
    private final static String filePath = "./src/main/resources/PokemonShowdown/teams";
    private final static int teamSize = 4;
    private Team selectedTeam;
    public static Game gameTransferring;
    public static String name;
    public static double volume = 0.5;
    private Media mainTheme;
    private MediaPlayer mainThemePlayer;

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

    @FXML private Slider volumeSlider;

    @FXML
    private void initialize() {
        getTeamsFromFile();
        try {
            mainTheme = new Media(
                getClass().getClassLoader().getResource("PokemonShowdown/sound/mainTheme.mp3").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        
        mainThemePlayer = new MediaPlayer(mainTheme);
        mainThemePlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mainThemePlayer.seek(Duration.ZERO);
            }
        });
        mainThemePlayer.setVolume(volume);
        volumeSlider.setValue(volume);
        mainThemePlayer.play();
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mainThemePlayer.setVolume((double) newValue);
                volume = (double)newValue;
            }
        });
    }
    
    @FXML
    private void addTeamMember(ActionEvent ae){
        if (team.size() < teamSize) {
            Button button = (Button) ae.getSource();
            team.add(new Pokemon(button.getText()));
            selectedPokemonButtons.add(button);
            button.setDisable(true);
        }
    }

    @FXML
    private void deselectLast() {
        if (selectedPokemonButtons.size() > 0) {
            selectedPokemonButtons.get(selectedPokemonButtons.size()-1).setDisable(false);
            selectedPokemonButtons.remove(selectedPokemonButtons.size()-1);
            team.remove(team.size()-1);
        }
    }

    @FXML
    private void handleCreateTeam() throws IOException {
        String name = teamName.getText();
        if (!checkValidTeamName(name)) showWarning("invalid name");
        else if (team.size() == teamSize && teamName.getLength() > 0) {
            Team tmp = new Team(name,team);
            selectedPokemonButtons.stream().forEach((button) -> button.setDisable(false));
            team.clear();
            selectedPokemonButtons.clear();
            teamName.clear();
            if (checkButtonPosition()<10 && checkButtonPosition()>=-1) {
                if (!teamNameList.contains(name)) teamList.add(createTeamButton(tmp),0, checkButtonPosition());
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

    private void switchScreen(ActionEvent ae, String file) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(file));
        stage = (Stage)((Node) ae.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        mainThemePlayer.stop();
    }

    @FXML
    private void handleNewGame(ActionEvent ae) throws IOException {
        if (selectedTeam == null) showWarning("team");
        name = selectedTeam.getName();
        gameTransferring= new Game(selectedTeam.getMons());
        switchScreen(ae,"PokemonShowdownMainView.fxml");
    }

    @FXML
    private void handleRandomGame(ActionEvent ae) throws IOException {
        name = "random";
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
        teamNameList.add(button.getText());
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