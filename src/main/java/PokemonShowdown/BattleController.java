package PokemonShowdown;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;

public class BattleController {
    
    private Game game;
    private final String frontSpritePath = "PokemonShowdown/frontSprites";
    private final String backSpritePath = "PokemonShowdown/backSprites";
    private PrintStream ps;
    private int activeMonButtonIndex;
    private List<Button> pokemonButtonList = new ArrayList<>();
    private List<Button> attackButtonList = new ArrayList<>();
    private int turnCount;
    
    @FXML
    private GridPane playerTeamView,attacks;

    @FXML
    private TextArea opponentTeam, console;

    @FXML
    private ImageView playerMon, opponentMon;

    @FXML
    private ProgressBar playerMonHealthBar, opponentMonHealthBar;
    
    @FXML
    private Label playerMonHealthPercentage,opponentMonHealthPercentage,playerMonName,opponentMonName,endScreenTitle;

    @FXML
    private Pane endScreen;

    @FXML
    private Button backToMenu;

    @FXML
    private void initialize() {      
        game = PokemonShowdownController.gameTransferring;
        System.out.println(game);
        initializeGame(game);
        opponentMon.setImage(new Image(getFrontSprite()));
        opponentMonHealthBar.setStyle("-fx-accent: green;");
        opponentMonHealthBar.setProgress(1);
        opponentMonHealthPercentage.setText("100%");
        opponentMonName.setText(game.getActiveOpponentMon().getName().substring(0,1).toUpperCase() + game.getActiveOpponentMon().getName().substring(1));
        this.ps = new PrintStream(new Console(console));
        System.setOut(ps);
        System.out.println(game);

    }

    @FXML
    private void handleSelectMon(ActionEvent ae) throws IOException {
        Button button = (Button)ae.getSource();
        activeMonButtonIndex = (int)GridPane.getRowIndex(button);
        if (game.getActiveMon() != null) {
            if (!game.getActiveMon().isDead()) {
                game.setActiveMon(activeMonButtonIndex);
                setMoveButtons();
                playerMon.setImage(new Image(getBackSprite()));
                playerMonName.setText(game.getActiveMon().getName().substring(0,1).toUpperCase() + game.getActiveMon().getName().substring(1));
                playerMonHealthBar.setVisible(true); 
                enableAttackButtons();
                updateHealthBars();
                if (turnCount != 0) {
                    turnCount ++;
                    game.getActiveOpponentMon().attack(game.getActiveMon(), ThreadLocalRandom.current().nextInt(4));
                    animateOpponentMon();
                    updateMonStatus();
                    if (gameEnded()) endScreen();
                }
            }
            else {
                game.setActiveMon(activeMonButtonIndex);
                setMoveButtons();
                playerMon.setImage(new Image(getBackSprite()));
                playerMonName.setText(game.getActiveMon().getName().substring(0,1).toUpperCase() + game.getActiveMon().getName().substring(1));
                playerMonHealthBar.setVisible(true); 
                enableAttackButtons();
                updateHealthBars();
            }
        }
        else {
            game.setActiveMon(activeMonButtonIndex);
            setMoveButtons();
            playerMon.setImage(new Image(getBackSprite()));
            playerMonName.setText(game.getActiveMon().getName().substring(0,1).toUpperCase() + game.getActiveMon().getName().substring(1));
            playerMonHealthBar.setVisible(true); 
            enableAttackButtons();
            updateHealthBars();
            turnCount ++;
        }
    }

    @FXML
    private void handleMove(ActionEvent ae) { 
        Button button = (Button)ae.getSource();
        int moveIndex = (int)GridPane.getColumnIndex(button);
        turn(moveIndex);
    }

    @FXML
    public void switchScreen(ActionEvent ae, String file) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(file));
        Stage stage = (Stage)((Node) ae.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleBackToMenu(ActionEvent ae) throws IOException {
        switchScreen(ae, "PokemonShowdownStartGUI.fxml");
    }

    private void turn(int moveIndex) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        if (game.getActiveMon().getSpeed() > game.getActiveOpponentMon().getSpeed()) {
            game.getActiveMon().attack(game.getActiveOpponentMon(), moveIndex);
            animatePlayerMon();
            if (game.getActiveOpponentMon().isDead()) {
                System.out.println(game.getActiveOpponentMon().getName() + " fainted.");
                updateMonStatus();
                if (gameEnded()) endScreen();
            }
            else {   
                executorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        animateOpponentMon();
                    }
                }, 1, TimeUnit.SECONDS);
                game.getActiveOpponentMon().attack(game.getActiveMon(), ThreadLocalRandom.current().nextInt(4));
                updateMonStatus();
                if (gameEnded()) endScreen();
            }
        }
        else if (game.getActiveMon().getSpeed() < game.getActiveOpponentMon().getSpeed()) {
            game.getActiveOpponentMon().attack(game.getActiveMon(), ThreadLocalRandom.current().nextInt(4));
            animateOpponentMon();
            if (game.getActiveMon().isDead()) {
                System.out.println(game.getActiveMon().getName() + " fainted.");
                updateMonStatus();
                if (gameEnded()) endScreen();
            }
            else {    
                executorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        animatePlayerMon();
                    }
                }, 1, TimeUnit.SECONDS);
                game.getActiveMon().attack(game.getActiveOpponentMon(), moveIndex);
                updateMonStatus();
                if (gameEnded()) endScreen();
            }
        }
        else {
            Double rand = ThreadLocalRandom.current().nextDouble(1);
            if (rand <= 0.5) {
                game.getActiveMon().attack(game.getActiveOpponentMon(), moveIndex);
                animatePlayerMon();
                if (game.getActiveOpponentMon().isDead()) {
                    System.out.println(game.getActiveOpponentMon().getName() + " fainted.");
                    updateMonStatus();
                    if (gameEnded()) endScreen();
                }
                else {    
                    executorService.schedule(new Runnable() {
                        @Override
                        public void run() {
                            animateOpponentMon();
                        }
                    }, 1, TimeUnit.SECONDS);
                    game.getActiveOpponentMon().attack(game.getActiveMon(), ThreadLocalRandom.current().nextInt(4));
                    updateMonStatus();
                    if (gameEnded()) endScreen();
                }
            }
            else {
                game.getActiveOpponentMon().attack(game.getActiveMon(), ThreadLocalRandom.current().nextInt(4));
                animateOpponentMon();
                if (game.getActiveMon().isDead()) {
                    System.out.println(game.getActiveMon().getName() + " fainted.");
                    updateMonStatus();
                    if (gameEnded()) endScreen();
                }
                else {    
                    executorService.schedule(new Runnable() {
                        @Override
                        public void run() {
                            animatePlayerMon();
                        }
                    }, 1, TimeUnit.SECONDS);
                    game.getActiveMon().attack(game.getActiveOpponentMon(), moveIndex);
                    updateMonStatus();
                    if (gameEnded()) endScreen();
                }
            }
        }
    }
    
    private void updateMonStatus() {
        updateHealthBars();
        if (game.getActiveOpponentMon().isDead()) {
            game.setActiveOpponentMon();
            opponentMon.setImage(new Image(getFrontSprite()));
            opponentMonName.setText(game.getActiveOpponentMon().getName().substring(0,1).toUpperCase() + game.getActiveOpponentMon().getName().substring(1));
            opponentMonHealthBar.setVisible(true); 
            updateHealthBars();
        }
        if (game.getActiveMon().isDead()) {
            pokemonButtonList.get(activeMonButtonIndex).setDisable(true);
            disableMoveButtons();
        }
    }

    private void updateHealthBars() {
        Double playerMonHealthNum = Double.valueOf(game.getActiveMon().getHp())/Double.valueOf(game.getActiveMon().getMaxHp());
        Double opponentMonHealthNum = Double.valueOf(game.getActiveOpponentMon().getHp())/Double.valueOf(game.getActiveOpponentMon().getMaxHp());
        if (playerMonHealthNum <= 0.1) playerMonHealthBar.setStyle("-fx-accent: red;");
        else playerMonHealthBar.setStyle("-fx-accent: green;");
        if (opponentMonHealthNum <= 0.1) opponentMonHealthBar.setStyle("-fx-accent: red;");
        else opponentMonHealthBar.setStyle("-fx-accent: green;");
        playerMonHealthBar.setProgress(playerMonHealthNum);
        playerMonHealthPercentage.setText((int)(playerMonHealthNum*100) + "%");
        opponentMonHealthBar.setProgress(opponentMonHealthNum);
        opponentMonHealthPercentage.setText((int)(opponentMonHealthNum*100) + "%");
    }

    private String getBackSprite() {
        return "/" + backSpritePath + "/" + game.getActiveMon().getName() +"Back.gif";
        // return "backSprites/" +game.getActiveMon().getName() +"Back.png";
    }

    private String getFrontSprite() {
        return "/" + frontSpritePath + "/" + game.getActiveOpponentMon().getName() + "Front.gif";
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
        pokemonButtonList.add(button);
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
        attackButtonList.add(button);
        button.wrapTextProperty().setValue(true);
        button.setStyle("-fx-text-alignment: center;");
        button.setCursor(Cursor.HAND);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        button.setOnAction((ae) -> handleMove(ae));
        return button;
    }

    private void endScreen() {
        pokemonButtonList.stream().forEach(button -> button.setDisable(true));
        attackButtonList.stream().forEach(button -> button.setDisable(true));
        if (game.getActiveMon().isDead()) endScreenTitle.setText("You lost :(");
        else endScreenTitle.setText("You won!");
        endScreen.setVisible(true);
    }

    private boolean gameEnded() {
        int playerCount = 0;
        int opponentCount = 0;
        for (Pokemon mon : game.getPlayerTeam()) {
            if(mon.isDead()) playerCount ++;
        }
        for (Pokemon mon : game.getOpponentTeam()) {
            if(mon.isDead()) opponentCount ++;
        }
        return (playerCount == 4 || opponentCount == 4);
    }

    private void disableMoveButtons() {
        attackButtonList.stream().forEach(button -> {
            button.setDisable(true);
            button.setVisible(false);
        });
    }

    private void enableAttackButtons() {
        attackButtonList.stream().forEach(button -> button.setDisable(false));
    }

    private void animatePlayerMon() {
        TranslateTransition translate = new TranslateTransition(Duration.millis(100), playerMon);
        translate.setByX(300);
        translate.setByY(-100);
        translate.setAutoReverse(true);
        translate.setCycleCount(2);
        translate.play();
    }

    private void animateOpponentMon() {
        TranslateTransition translate = new TranslateTransition(Duration.millis(150), opponentMon);
        translate.setByX(-300);
        translate.setByY(100);
        translate.setAutoReverse(true);
        translate.setCycleCount(2);
        translate.play();
    }


    public class Console extends OutputStream {

        private TextArea console;

        public Console(TextArea console) { 
            this.console = console;
        }

        public void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));
        }

        @Override
        public void write(int b) throws IOException {
            appendText(String.valueOf((char)b));
        }
    }

}
