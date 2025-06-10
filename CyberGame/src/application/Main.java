package application;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // This will show the character selection screen first
        CharacterSelection cs = new CharacterSelection();
        cs.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args); // This starts the JavaFX application
    }
}
