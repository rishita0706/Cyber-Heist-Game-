package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.File;

import javafx.geometry.Pos;

public class CharacterSelection {

	public void start(Stage stage) {
	    Label title = new Label("Choose Your Character");
	    title.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
	    title.setTextFill(Color.LIME);

	    String imagePath = "C:/Users/HP/Downloads/CharaSelbg.png";
	    File file = new File(imagePath);
	    Image bgImage = new Image(file.toURI().toString());

	    ImageView bgImageView = new ImageView(bgImage);
	    bgImageView.setFitWidth(800);
	    bgImageView.setFitHeight(600);
	    bgImageView.setPreserveRatio(false);

	    // CipherX Description
	    Button cipherXButton = new Button("🕶️ CipherX");
	    Label cipherDesc = new Label("Play as CipherX – a rogue hacker\nUnravel a dark conspiracy hidden in government servers.");
	    cipherDesc.setTextFill(Color.WHITE);
	    cipherDesc.setFont(Font.font("Courier New", 14));
	    cipherDesc.setWrapText(true);

	    cipherXButton.setOnAction(e -> {
	        CyberHeist game = new CyberHeist();
	        game.start(stage);
	    });

	    // Agent Nova Description
	    Button novaButton = new Button("🔍 Agent Nova");
	    Label novaDesc = new Label("Play as Agent Nova – a stealth agent\nSabotage from the inside and trace corrupted system logs.");
	    novaDesc.setTextFill(Color.WHITE);
	    novaDesc.setFont(Font.font("Courier New", 14));
	    novaDesc.setWrapText(true);

	    novaButton.setOnAction(e -> {
	        AgentNova novaGame = new AgentNova();
	        novaGame.start(stage);
	    });

	    // Styling
	    cipherXButton.setStyle("-fx-background-color: black; -fx-text-fill: lime; -fx-border-color: lime;");
	    novaButton.setStyle("-fx-background-color: black; -fx-text-fill: cyan; -fx-border-color: cyan;");

	    VBox cipherBox = new VBox(10, cipherXButton, cipherDesc);
	    VBox novaBox = new VBox(10, novaButton, novaDesc);
	    cipherBox.setAlignment(Pos.CENTER);
	    novaBox.setAlignment(Pos.CENTER);

	    HBox choices = new HBox(40, cipherBox, novaBox);
	    choices.setAlignment(Pos.CENTER);

	    VBox content = new VBox(40, title, choices);
	    content.setAlignment(Pos.CENTER);
	    content.setStyle("-fx-padding: 50;");

	    // Now use StackPane to layer background behind everything
	    StackPane root = new StackPane();
	    root.getChildren().addAll(bgImageView, content);

	    Scene scene = new Scene(root, 800, 600);
	    stage.setScene(scene);
	    stage.setTitle("Character Selection");
	    stage.show();
	}
}


