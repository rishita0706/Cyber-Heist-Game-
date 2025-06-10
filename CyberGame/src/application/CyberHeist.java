package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.File;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CyberHeist extends Application {
    private String storyText = "📩 'A lost crypto wallet worth billions is hidden in a top-secret \ngovernment database.'\n\n"
                             + "Hack into the Digital Vault. The world depends on it.";
    private Label storyLabel = new Label();
    private int charIndex = 0;
    @Override
    public void start(Stage primaryStage) {
        // Set up UI
        storyLabel.setFont(new Font("Courier New", 18));
        //storyLabel.setTextFill(Color.BLACK);
        storyLabel.setTextFill(Color.WHITE);
        // Apply Border and Background using CSS
        storyLabel.setStyle(
            "-fx-border-color: limegreen; " +    // Border color
            "-fx-border-width: 3px; " +         // Border thickness
            "-fx-border-radius: 5px; " +        // Rounded corners
            "-fx-background-color: black; " +   // Background color
            "-fx-padding: 10px;"                // Padding inside the border
        );
        Button startButton = new Button("▶ Start Hacking");
        startButton.setFont(new Font("Arial", 18));
        startButton.setStyle("-fx-background-color: white; -fx-text-fill: limegreen; -fx-border-color: limegreen; -fx-padding: 10px;");
        startButton.setOnMouseEntered(e -> startButton.setStyle("-fx-background-color: limegreen; -fx-text-fill: black;"));
        startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: black; -fx-text-fill: limegreen; -fx-border-color: limegreen;"));
//      
        //startButton.setOnAction(e -> startGame(primaryStage)); // Moves to next scene
        startButton.setOnAction(e -> new EncryptionPuzzle().launch(primaryStage));
        
        Button back = new Button("Back");
        back.setStyle("-fx-background-color: white; -fx-text-fill: limegreen; -fx-border-color: limegreen; -fx-padding: 10px;");
        back.setOnMouseEntered(e -> back.setStyle("-fx-background-color: limegreen; -fx-text-fill: black;"));
        back.setOnMouseExited(e -> back.setStyle("-fx-background-color: black; -fx-text-fill: limegreen; -fx-border-color: limegreen;"));
        back.setOnAction(e -> {
        	CharacterSelection chr = new CharacterSelection();
        	chr.start(primaryStage);
        });
        
        String imagePath = "C:/Users/HP/OneDrive/Pictures/bg image.png"; 
        // Convert it to a URI format
        File file = new File(imagePath);
        Image bgImage = new Image(file.toURI().toString());
        // Use ImageView to set the background
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(800);  // Set width according to your scene
        bgImageView.setFitHeight(600); // Set height according to your scene
        bgImageView.setPreserveRatio(false);
        
        StackPane root = new StackPane();
        //root.setStyle("-fx-background-color: black;");
        root.getChildren().addAll(bgImageView, storyLabel, startButton, back);
        StackPane.setAlignment(startButton, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setAlignment(back, Pos.TOP_LEFT);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cyber Heist: The Digital Vault");
        primaryStage.setScene(scene);
        primaryStage.show();
        // Start typewriter effect
        playTypewriterEffect();
    }

    private void playTypewriterEffect() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            if (charIndex < storyText.length()) {
                storyLabel.setText(storyLabel.getText() + storyText.charAt(charIndex));
                charIndex++;
            }
        }));
        timeline.setCycleCount(storyText.length());
        timeline.play();
    }
    
    public class EncryptionPuzzle {
        public void launch(Stage stage) {
        	
        	String imagePath = "C:/Users/HP/Downloads/CipherXL1.png"; 
            // Convert it to a URI format
            File file = new File(imagePath);
            Image bgImage = new Image(file.toURI().toString());
            // Use ImageView to set the background
            ImageView bgImageView = new ImageView(bgImage);
            bgImageView.setFitWidth(800);  // Set width according to your scene
            bgImageView.setFitHeight(600); // Set height according to your scene
            bgImageView.setPreserveRatio(false);
            bgImageView.setSmooth(true);
            
            // Title
            Label titleLabel = new Label("🛡 Encryption Challenge: Firewall Breach");
            titleLabel.setFont(new Font("Courier New", 20));
            titleLabel.setTextFill(Color.LIMEGREEN);

            // Encrypted Message
            Label encryptedLabel = new Label("Encrypted Message: 3F 2D 7A 6E 34 2D 6F 20 74 69 20 2A 2D 7C");
            encryptedLabel.setFont(new Font("Courier New", 16));
            encryptedLabel.setTextFill(Color.WHITE);

            // Input Field
            TextField inputField = new TextField();
            inputField.setPromptText("Enter decrypted message");

            // Feedback Label
            Label feedbackLabel = new Label();
            feedbackLabel.setFont(new Font("Arial", 14));

            // Submit Button
            Button submitButton = new Button("Decrypt");
            submitButton.setFont(Font.font("Arial", 16));
            submitButton.setStyle("-fx-background-color: lime; -fx-text-fill: black;");
            
            Button nextlevel = new Button("Next Level");
            nextlevel.setFont(Font.font("Arial", 16));
            nextlevel.setStyle("-fx-background-color: lime; -fx-text-fill: black;");
            nextlevel.setVisible(false);
            nextlevel.setOnAction(e ->  new PasswordCracking().launch(stage));
            
            submitButton.setOnAction(e -> { 
                String answer = inputField.getText().trim().toUpperCase();
                if (answer.equals("THE VAULT HIDES THE FUTURE")) {
                    feedbackLabel.setText("✅ Access Granted!");
                    feedbackLabel.setTextFill(Color.LIMEGREEN);
                    nextlevel.setVisible(true);
                } else {
                    feedbackLabel.setText("❌ Access Denied. Try again.");
                    feedbackLabel.setTextFill(Color.RED);
                }
            });

            // Hint Button
            Button hintButton = new Button("Hint 🛈");
            hintButton.setFont(Font.font("Arial", 16));
            hintButton.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
            hintButton.setOnAction(e -> {
                Alert hintAlert = new Alert(Alert.AlertType.INFORMATION);
                hintAlert.setTitle("Encryption Hint");
                hintAlert.setHeaderText("How XOR Cipher Works:");
                hintAlert.setContentText(
                    "🔑 The key to decoding this message is the number **42**.\n\n"
                    + "📜 Each letter in the encrypted text was XORed with 42.\n"
                    + "💡 Apply the XOR operation in reverse to decrypt it!"
                );
                hintAlert.showAndWait();
            });

            // Layout
//            VBox layout = new VBox(15, titleLabel, encryptedLabel, inputField, submitButton, hintButton, feedbackLabel, nextlevel);
//            layout.setStyle("-fx-background-color: transparent; -fx-padding: 30;");
//            layout.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
            
            VBox layout1 = new VBox(15,titleLabel, encryptedLabel);
            VBox layout2 = new VBox(15,inputField, submitButton, hintButton, feedbackLabel, nextlevel);
            layout2.setAlignment(javafx.geometry.Pos.CENTER);
            VBox layout = new VBox(300,layout1, layout2);
            layout.setStyle("-fx-background-color: transparent; -fx-padding: 30;");
            layout.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);
            
            StackPane root = new StackPane();
            root.getChildren().addAll(bgImageView, layout);

            // Scene
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.setTitle("Firewall Decryption");
            stage.show();
        }
    }
    
    
    public class PasswordCracking {

        public void launch(Stage stage) {
        	String imagePath = "C:/Users/HP/Downloads/CipherXL2.png"; 
            // Convert it to a URI format
            File file = new File(imagePath);
            Image bgImage = new Image(file.toURI().toString());
            // Use ImageView to set the background
            ImageView bgImageView = new ImageView(bgImage);
            bgImageView.setFitWidth(800);  // Set width according to your scene
            bgImageView.setFitHeight(600); // Set height according to your scene
            bgImageView.setPreserveRatio(false);
            bgImageView.setSmooth(true);
            
            Label titleLabel = new Label("🔓 Password Cracking Challenge");
            titleLabel.setFont(new Font("Courier New", 20));
            titleLabel.setTextFill(Color.LIMEGREEN);

            Label riddleLabel = new Label(
            		"Riddle: I exist in the shadows, yet I hold the key. " +
            	            "I hide in bytes, but only the wise can see. What am I?");
            riddleLabel.setWrapText(true);
            riddleLabel.setFont(new Font("Courier New", 14));
            riddleLabel.setTextFill(Color.WHITE);

            TextField inputField = new TextField();
            inputField.setPromptText("Enter your answer");

            Label feedbackLabel = new Label();
            feedbackLabel.setFont(new Font("Arial", 14));

            Button submitButton = new Button("Submit");
            submitButton.setStyle("-fx-background-color: black; -fx-text-fill: limegreen; -fx-border-color: limegreen; -fx-padding: 8px;");
            submitButton.setOnMouseEntered(e -> submitButton.setStyle("-fx-background-color: limegreen; -fx-text-fill: black;"));
            submitButton.setOnMouseExited(e -> submitButton.setStyle("-fx-background-color: black; -fx-text-fill: limegreen; -fx-border-color: limegreen;"));

            submitButton.setOnAction(e -> {
                String answer = inputField.getText().trim().toLowerCase();
                if (answer.equals("encryption")) {
                    feedbackLabel.setText("✅ Password Accepted! Moving to Next Level...");
                    feedbackLabel.setTextFill(Color.LIMEGREEN);
//                    new AISecurityEvasion().launch(stage);
                } else {
                    feedbackLabel.setText("❌ Incorrect Password. Try again.");
                    feedbackLabel.setTextFill(Color.RED);
                }
            });
            submitButton.setOnAction(e -> moveToNextLevel(stage));
            

            VBox layout1 = new VBox(15, titleLabel);
            layout1.setStyle("-fx-background-color: black;");
            VBox layout2 = new VBox(15,riddleLabel, inputField, submitButton, feedbackLabel);
            layout2.setAlignment(Pos.CENTER);
            VBox layout = new VBox(300, layout1, layout2);
            layout.setStyle("-fx-background-color: transparent; -fx-padding: 30;");
            layout.setAlignment(Pos.CENTER);
            
            StackPane root = new StackPane();
            root.getChildren().addAll(bgImageView, layout);

            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.setTitle("Password Cracking");
        }

        private void moveToNextLevel(Stage stage) {
            Label nextSceneLabel = new Label("🛡️ Security Level 2 Breached! Proceeding...\n NEXT LEVEL");
            nextSceneLabel.setFont(new Font("Arial", 20));
            nextSceneLabel.setTextFill(Color.LIMEGREEN);
            nextSceneLabel.setOnMouseClicked(e ->  new AISecurityEvasion().launch(stage));

            VBox layout = new VBox(nextSceneLabel);
            layout.setAlignment(Pos.CENTER);
            layout.setStyle("-fx-background-color: black; -fx-padding: 30;");

            Scene nextScene = new Scene(layout, 800, 600);
            stage.setScene(nextScene);
        }
    }
    
    public class AISecurityEvasion {

        private int timeLeft;
        private Label timerLabel;
        private Label codeLabel;
        private TextField inputField;
        private Button bypassButton;
        private String correctCode;
        private Timeline countdown;
        
        public void launch(Stage stage) {
            timeLeft = 10; // Reset timer to 10 seconds
            timerLabel = new Label("⏳ Time Left: " + timeLeft + "s");
            codeLabel = new Label();
            inputField = new TextField();
            bypassButton = new Button("Bypass AI 🔓");
            
            String imagePath = "C:/Users/HP/Downloads/CipherXL3.png"; 
            // Convert it to a URI format
            File file = new File(imagePath);
            Image bgImage = new Image(file.toURI().toString());
            // Use ImageView to set the background
            ImageView bgImageView = new ImageView(bgImage);
            bgImageView.setFitWidth(800);  // Set width according to your scene
            bgImageView.setFitHeight(600); // Set height according to your scene
            bgImageView.setPreserveRatio(false);
            bgImageView.setSmooth(true);

            Label titleLabel = new Label("⚠ AI Security Patrol Incoming...");
            titleLabel.setFont(new Font("Courier New", 20));
            titleLabel.setTextFill(Color.RED);

            timerLabel.setFont(new Font("Courier New", 18));
            timerLabel.setTextFill(Color.LIMEGREEN);

            // Generate new random 6-character code each time
            correctCode = generateRandomCode();
            codeLabel.setText("🔑 Decrypt Code: " + correctCode);
            codeLabel.setFont(new Font("Courier New", 16));
            codeLabel.setTextFill(Color.WHITE);

            inputField.setPromptText("Enter the code...");
            inputField.setFont(new Font("Courier New", 16));

            bypassButton.setFont(new Font("Courier New", 16));
            bypassButton.setStyle("-fx-background-color: black; -fx-text-fill: limegreen; -fx-border-color: limegreen; -fx-padding: 8px;");
            bypassButton.setVisible(false);

            bypassButton.setOnMouseEntered(e -> bypassButton.setStyle("-fx-background-color: limegreen; -fx-text-fill: black;"));
            bypassButton.setOnMouseExited(e -> bypassButton.setStyle("-fx-background-color: black; -fx-text-fill: limegreen; -fx-border-color: limegreen;"));

            bypassButton.setOnAction(e -> moveToNextLevel(stage));

            Button submitButton = new Button("Submit");
            submitButton.setFont(Font.font("Arial", 16));
            submitButton.setStyle("-fx-background-color: lime; -fx-text-fill: black;");
            submitButton.setOnAction(e -> checkCode(stage));

            VBox layout = new VBox(15, titleLabel, timerLabel, codeLabel, inputField, submitButton, bypassButton);
            layout.setStyle("-fx-background-color: transparent; -fx-padding: 30;");
            layout.setAlignment(Pos.BOTTOM_CENTER);
            
            StackPane root = new StackPane();
            root.getChildren().addAll(bgImageView, layout);

            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.setTitle("AI Security Evasion");

            // Start Countdown Timer
            startCountdown(stage);
        }

        private void startCountdown(Stage stage) {
            countdown = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                timeLeft--;
                timerLabel.setText("⏳ Time Left: " + timeLeft + "s");

                if (timeLeft <= 0) {
                    timerLabel.setText("❌ AI Detected You! Restarting...");
                    restartLevel(stage);
                }
            }));
            countdown.setCycleCount(timeLeft); 
            countdown.play();
        }

        private void checkCode(Stage stage) {
            if (inputField.getText().trim().equals(correctCode)) {
                timerLabel.setText("✅ Code Accepted! AI Disabled!");
                bypassButton.setVisible(true); 
                countdown.stop(); // Stop timer since code is correct
            } else {
                timerLabel.setText("❌ Incorrect Code! Try Again...");
                inputField.clear();
            }
        }

        private void restartLevel(Stage stage) {
            countdown.stop(); // Stop previous countdown

            Label gameOverLabel = new Label("🚨 AI DETECTED YOU! TRY AGAIN");
            gameOverLabel.setFont(new Font("Arial", 20));
            gameOverLabel.setTextFill(Color.RED);

            Button retryButton = new Button("Retry");
            retryButton.setOnAction(e -> launch(stage)); // Properly restart everything

            VBox layout = new VBox(15, gameOverLabel, retryButton);
            layout.setAlignment(Pos.CENTER);
            layout.setStyle("-fx-background-color: black; -fx-padding: 30;");

            Scene gameOverScene = new Scene(layout, 800, 600);
            stage.setScene(gameOverScene);
        }

        private void moveToNextLevel(Stage stage) {
            Label successLabel = new Label("🔓 AI Security Disabled! Moving Forward...");
            successLabel.setFont(new Font("Arial", 20));
            successLabel.setTextFill(Color.LIMEGREEN);

            VBox layout = new VBox(successLabel);
            layout.setAlignment(Pos.CENTER);
            layout.setStyle("-fx-background-color: black; -fx-padding: 30;");

            Scene nextScene = new Scene(layout, 800, 600);
            stage.setScene(nextScene);
            
            NexusDialogue nexusScene = new NexusDialogue();
            nexusScene.start(stage); // Switch to AI conversation scene
        }

        private String generateRandomCode() {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            Random random = new Random();
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                code.append(chars.charAt(random.nextInt(chars.length())));
            }
            return code.toString();
        }
    }
    
    public class NexusDialogue extends Application {
        
        private Label aiLabel;
        private String fullText = "🔴 NEXUS: CipherX, you've been deceived.\n" +
                                  "This vault holds more than Bitcoin.\n" +
                                  "It contains secrets that could change the world.";
        private int textIndex = 0;
        private StringBuilder displayedText = new StringBuilder(); 

        public void start(Stage stage) {
            aiLabel = new Label();
            aiLabel.setFont(new Font("Courier New", 18));
            aiLabel.setTextFill(Color.LIMEGREEN);
            aiLabel.setWrapText(true);
            aiLabel.setBackground(new Background(
            	    new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)
            	));
            
            String imagePath = "C:/Users/HP/Downloads/CipherXl4.png"; 
            // Convert it to a URI format
            File file = new File(imagePath);
            Image bgImage = new Image(file.toURI().toString());
            // Use ImageView to set the background
            ImageView bgImageView = new ImageView(bgImage);
            bgImageView.setFitWidth(800);  // Set width according to your scene
            bgImageView.setFitHeight(600); // Set height according to your scene
            bgImageView.setPreserveRatio(false);
            bgImageView.setSmooth(true);

            Label choiceLabel = new Label("🤔 What will you do?");
            choiceLabel.setFont(new Font("Courier New", 18));
            choiceLabel.setTextFill(Color.WHITE);
            choiceLabel.setVisible(false); 

            Button takeBitcoinButton = new Button("💰 Take the Bitcoin & Escape");
            takeBitcoinButton.setFont(Font.font("Arial", 16));
            takeBitcoinButton.setStyle("-fx-background-color: lime; -fx-text-fill: black;");
            takeBitcoinButton.setOnAction(e -> showEnding(stage, "MILLIONAIRE"));
            takeBitcoinButton.setVisible(false); 

            Button exposeTruthButton = new Button("🔓 Expose the Truth");
            exposeTruthButton.setFont(Font.font("Arial", 16));
            exposeTruthButton.setStyle("-fx-background-color: lime; -fx-text-fill: black;");
            exposeTruthButton.setOnAction(e -> showEnding(stage, "TRUTH"));
            exposeTruthButton.setVisible(false);

            VBox layout = new VBox(20, aiLabel, choiceLabel, takeBitcoinButton, exposeTruthButton);
            layout.setStyle("-fx-background-color: transparent; -fx-padding: 30;");
            layout.setAlignment(javafx.geometry.Pos.CENTER);
            
            StackPane root = new StackPane();
            root.getChildren().addAll(bgImageView, layout);

            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();

            startTypewriterEffect(choiceLabel, takeBitcoinButton, exposeTruthButton);
        }

        private void startTypewriterEffect(Label choiceLabel, Button button1, Button button2) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
                if (textIndex < fullText.length()) {
                    displayedText.append(fullText.charAt(textIndex)); 
                    Platform.runLater(() -> aiLabel.setText(displayedText.toString())); 
                    textIndex++;
                }
            }));

            timeline.setCycleCount(fullText.length());
            timeline.setOnFinished(e -> {
                // Ensure UI updates after animation ends
                Platform.runLater(() -> {
                    choiceLabel.setVisible(true);
                    button1.setVisible(true);
                    button2.setVisible(true);
                });
            });

            timeline.play();
        }

        private void showEnding(Stage stage, String choice) {
            Label endingLabel = new Label();
            if (choice.equals("MILLIONAIRE")) {
                endingLabel.setText("💰 You transferred the Bitcoin to your account.\nYou're now a ghost millionaire. GAME OVER.");
            } else {
                endingLabel.setText("🔓 You exposed the data to the world.\nGovernments collapse. The truth is free. GAME OVER.");
            }
            endingLabel.setFont(new Font("Courier New", 20));
            endingLabel.setTextFill(Color.WHITE);
            endingLabel.setWrapText(true);

            VBox layout = new VBox(20, endingLabel);
            layout.setStyle("-fx-background-color: black; -fx-padding: 30;");
            layout.setAlignment(javafx.geometry.Pos.CENTER);

            Scene scene = new Scene(layout, 800, 600);
            stage.setScene(scene);
        }

        public static void main(String[] args) {
            launch(args);
        }
    }}
