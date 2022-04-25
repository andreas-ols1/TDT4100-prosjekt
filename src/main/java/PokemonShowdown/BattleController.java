package PokemonShowdown;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.text.Element;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class BattleController {
    
    private Game game;
    private final String frontSpritePath = "./src/target/classes/PokemonShowdown/frontSprites";
    private final String backSpritePath = "PokemonShowdown/backSprites";
    
    @FXML
    private GridPane playerTeamView,attacks;

    @FXML
    private TextArea opponentTeam;

    @FXML
    private ImageView playerMon, opponentMon; 

    @FXML
    private AnchorPane images;

    @FXML
    private void initialize() {
        System.out.println("arash er dum");      
        game = PokemonShowdownController.gameTransferring;
        System.out.println(game);
        initializeGame(game);
    }

    
    

    @FXML
    private void handleSelectMon(ActionEvent ae) throws IOException {
        Button button = (Button)ae.getSource();
        game.setActiveMon((int)GridPane.getRowIndex(button));
        System.out.println(game.getActiveMon());
        setMoveButtons();
        System.out.println(getBackSprite());
        // playerMon.setImage((new Image(getBackSprite())));
        // File file = new File(getBackSprite());
        Image image = new Image(getBackSprite());
        // System.out.println(image.getWidth());
        // System.out.println(image.getHeight());
        playerMon.setImage(image);
        // playerMon.toFront();
        // playerMon.setCache(true);
        // BufferedImage img = ImageIO.read(new File(getBackSprite()));
        // playerMon.setImage(img);
        // playerMon.setImage(new Image("file:"+getBackSprite()));
        // try {
        //     String imgPath = getClass().getClassLoader().getResource("PokemonShowdown/"+getBackSprite()).toURI().toString();
        //     System.out.println(imgPath);
        //     Image img = new Image(imgPath);
        //     playerMon.setImage(img);
        // } catch (URISyntaxException e) {
        //     e.printStackTrace();
        // }
    }

    private String getBackSprite() {
        return backSpritePath + "/" + game.getActiveMon().getName() +"Back.png";
        // return "backSprites/" +game.getActiveMon().getName() +"Back.png";
    }

    private String getFrontSprite() {
        return frontSpritePath + "/" + game.getActiveOpponentMon().getName() + "Front.gif";
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
        button.setOnAction((event) -> {
            try {
                handleSelectMon(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
        button.setOnAction((event) -> {
            try {
                handleSelectMon(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return button;
    }

    


}
