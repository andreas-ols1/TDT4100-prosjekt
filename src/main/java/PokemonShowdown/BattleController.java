package PokemonShowdown;

import java.util.stream.IntStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class BattleController {
    
    @FXML
    private GridPane playerTeamView,attacks;

    @FXML
    private TextArea opponentTeam;

    private Game game;

    @FXML
    private void initialize() {
        System.out.println("arash er dum");      
        game = PokemonShowdownController.gameTransferring;
        System.out.println(game);
        initializeGame(game);
    }

    
    

    @FXML
    private void handleSelectMon(ActionEvent ae) {
        Button button = (Button)ae.getSource();
        game.setActiveMon((int)GridPane.getRowIndex(button));
        System.out.println(game.getActiveMon());
        setMoveButtons();
    }


    private void setMoveButtons() {
        IntStream.range(0, 4).forEach(i -> attacks.add(createMoveButton(game.getActiveMon(), i), i, 0));
         
    }


    private void initializeGame(Game game) {
        IntStream.range(0,4).forEach(i -> playerTeamView.add(createPokemonButton(game,i), 0, i));
        opponentTeam.setText("");
        IntStream.range(0,3).forEach(i -> opponentTeam.appendText(game.getOpponentTeam().get(i).getName().substring(0,1).toUpperCase() + game.getOpponentTeam().get(i).getName().substring(1) + ", "));
        opponentTeam.appendText(game.getOpponentTeam().get(3).getName().substring(0,1).toUpperCase() + game.getOpponentTeam().get(3).getName().substring(1));
    }

    
    private Button createPokemonButton(Game game, int monIndex) {
        String monName = this.game.getPlayerTeam().get(monIndex).getName();
        Button button = new Button(monName.substring(0, 1).toUpperCase() + monName.substring(1));
        button.wrapTextProperty().setValue(true);
        button.setStyle("-fx-text-alignment: center;");
        button.setCursor(Cursor.HAND);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setOnAction((event) -> handleSelectMon(event));
        return button;
    }

    private Button createMoveButton(Pokemon mon, int index) {
        String moveName = this.game.getActiveMon().getMove(index).getName();
        Button button = new Button(moveName.substring(0, 1).toUpperCase() + moveName.substring(1));
        button.wrapTextProperty().setValue(true);
        button.setStyle("-fx-text-alignment: center;");
        button.setCursor(Cursor.HAND);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setOnAction((event) -> handleSelectMon(event));
        return button;
    }

    


}
