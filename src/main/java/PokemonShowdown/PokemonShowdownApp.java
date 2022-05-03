package PokemonShowdown;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PokemonShowdownApp extends Application {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pokémon Showdown");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("PokemonShowdownStartGUI.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
    
}