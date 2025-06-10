package application;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class AgentNova extends Application {
    private String storyText = "\uD83C\uDF10 [TOP-SECRET TRANSMISSION]\n\n" +
            "Agent Nova,\n\n" +
            "The AI system \"ARGUS\" has gone rogue. It has hijacked control of a \ndecommissioned Arctic nuclear base.\n\n" +
            "Countdown to global launch: 3 hours.\n\n" +
            "You are humanity’s last hope. Infiltrate. Disarm. Escape.\n\n" +
            "But be warned — ARGUS knows you're coming. \nAnd it's watching your every move...";
 
    private Label storyLabel = new Label();
    private int charIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        storyLabel.setFont(Font.font("Courier New", 18));
        storyLabel.setTextFill(Color.CYAN);
        storyLabel.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-padding: 15px; -fx-border-color: cyan; -fx-border-width: 2px;");

        // Load background image
        String imagePath = "C:/Users/HP/Downloads/Nova1bg.png";  // Replace with your own path
        File file = new File(imagePath);
        Image bgImage = new Image(file.toURI().toString());
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(800);
        bgImageView.setFitHeight(600);
        bgImageView.setPreserveRatio(false);

        Button enterButton = new Button("\uD83D\uDD27 Enter Mission");
        enterButton.setFont(Font.font("Arial", 18));
        enterButton.setStyle("-fx-background-color: black; -fx-text-fill: cyan; -fx-border-color: cyan; -fx-border-width: 2px;");

        enterButton.setOnMouseEntered(e -> enterButton.setStyle("-fx-background-color: cyan; -fx-text-fill: black; -fx-border-color: cyan;"));
        enterButton.setOnMouseExited(e -> enterButton.setStyle("-fx-background-color: black; -fx-text-fill: cyan; -fx-border-color: cyan;"));

        enterButton.setOnAction(e -> startNovaMission(primaryStage));
        
        Button back = new Button("Back");
        back.setFont(Font.font("Arial", 18));
        back.setStyle("-fx-background-color: black; -fx-text-fill: cyan; -fx-border-color: cyan; -fx-border-width: 2px;");
        back.setOnMouseEntered(e -> back.setStyle("-fx-background-color: cyan; -fx-text-fill: black; -fx-border-color: cyan;"));
        back.setOnMouseExited(e -> back.setStyle("-fx-background-color: black; -fx-text-fill: cyan; -fx-border-color: cyan;"));
        back.setOnAction(e -> {
        	CharacterSelection chr = new CharacterSelection();
        	chr.start(primaryStage);
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(bgImageView, storyLabel, enterButton,back);
        StackPane.setAlignment(storyLabel, javafx.geometry.Pos.CENTER);
        StackPane.setAlignment(enterButton, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setAlignment(back, javafx.geometry.Pos.TOP_LEFT);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Operation Cataclysm: Agent Nova’s Last Stand");
        primaryStage.show();

        playTypewriterEffect();
    }

    private void playTypewriterEffect() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(storyText.length());
        KeyFrame keyFrame = new KeyFrame(Duration.millis(40), event -> {
            if (charIndex < storyText.length()) {
                storyLabel.setText(storyLabel.getText() + storyText.charAt(charIndex));
                charIndex++;
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void startNovaMission(Stage primaryStage) {
        // Placeholder: Launch the first mission or level of Agent Nova
        new NovaLevel1().start(primaryStage);
    }

    public class NovaLevel1 extends Application {
    	
    	private Label storyLabel = new Label();
        private int charIndex = 0;
        private String cinematicIntro = "❄️ Arctic Base Detected...\n\n" +
                "Agent Nova: \"This place is falling apart. The AI is prepping nukes. I must override the lockdown.\"\n\n" +
                "🔐 Mission: Enter the Master Override Code to stop the first wave of launch protocols. (Take the hint!!)";
        private TextField codeInput = new TextField();
        private Label feedbackLabel = new Label();
        private Button tryAgainBtn = new Button("🔁 Try Again");
        private final String correctCode = "Doomsday";

        @Override
        public void start(Stage primaryStage) {
        	String imagePath1 = "C:/Users/HP/Downloads/NovaL1bg.png";  // Replace with your own path
          File file = new File(imagePath1);
          Image bgImage1 = new Image(file.toURI().toString());
          ImageView bgImageView1 = new ImageView(bgImage1);
          bgImageView1.setFitWidth(1000);
          bgImageView1.setFitHeight(600);
          bgImageView1.setPreserveRatio(false);
        	
            // Story Label (Cinematic Intro)
            storyLabel.setFont(Font.font("Courier New", 18));
            storyLabel.setTextFill(Color.LIME);
            storyLabel.setWrapText(true);
            storyLabel.setStyle("-fx-background-color: rgba(0,0,0,0.6); -fx-padding: 20px; -fx-background-radius: 10px; -fx-border-color: cyan; -fx-border-width: 2px;");
            storyLabel.setMaxWidth(800);

            // Code Input
            codeInput.setPromptText("Enter Master Override Code...");
            codeInput.setFont(Font.font("Arial", 16));
            codeInput.setStyle("-fx-background-color: black; -fx-text-fill: lime; -fx-border-color: lime; -fx-padding: 8px;");
            codeInput.setVisible(false);

            // Submit Button
            Button submitBtn = new Button("💾 Submit");
            submitBtn.setFont(Font.font("Arial", 16));
            submitBtn.setStyle("-fx-background-color: lime; -fx-text-fill: black;");
            submitBtn.setVisible(false);
            
            Button hintbtn = new Button("Hint");
            hintbtn.setFont(Font.font("Arial", 16));
            hintbtn.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
            hintbtn.setVisible(false);
            hintbtn.setOnAction(e -> {
                Alert hintAlert = new Alert(Alert.AlertType.INFORMATION);
                hintAlert.setTitle("Hint");
                hintAlert.setHeaderText("Here's your riddle:");
                hintAlert.setContentText(
                        "I'm feared like a system crash,\n" +
                        "When everything halts in a flash.\n" +
                        "My first half spells disaster in code,\n" +
                        "My second half counts your final load.\n" +
                        "In backups and failsafes you pray,\n" +
                        "Guess my name, I’m the system’s DOA!"
                );
                hintAlert.showAndWait();
            });
            
            //Next Level Button
            Button nextlevel = new Button("Next Level");
            nextlevel.setFont(Font.font("Arial",16));
            nextlevel.setStyle("-fx-background-color: lime; -fx-text-fill: black;");
            nextlevel.setVisible(false);

            // Feedback Label
            feedbackLabel.setTextFill(Color.RED);
            feedbackLabel.setFont(Font.font("Arial", 14));
            feedbackLabel.setVisible(false);

            // Try Again Button
            tryAgainBtn.setFont(Font.font("Arial", 14));
            tryAgainBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            tryAgainBtn.setVisible(false);

            // Submit Action
            submitBtn.setOnAction(e -> {
                String entered = codeInput.getText().trim();
                if (entered.equals(correctCode)) {
                    storyLabel.setText("✅ Access Granted!\n\nMissile launch sequence aborted. Proceed to next level...");
                    feedbackLabel.setVisible(false);
                    codeInput.setVisible(false);
                    submitBtn.setVisible(false);
                    hintbtn.setVisible(false);
                    tryAgainBtn.setVisible(false);
                    nextlevel.setVisible(true);
                } else {
                    feedbackLabel.setText("❌ Incorrect Code! Try again.");
                    feedbackLabel.setVisible(true);
                    tryAgainBtn.setVisible(true);
                }
            });

            // Try Again Action
            tryAgainBtn.setOnAction(e -> {
                codeInput.clear();
                feedbackLabel.setVisible(false);
                tryAgainBtn.setVisible(false);
            });
            
            nextlevel.setOnAction(e -> new NovaLevel2().start(primaryStage));

            VBox contentBox = new VBox(15, storyLabel, codeInput, submitBtn, hintbtn, feedbackLabel, tryAgainBtn, nextlevel);
            contentBox.setAlignment(Pos.CENTER);

            StackPane root = new StackPane(bgImageView1, contentBox);
            Scene scene = new Scene(root, 900, 600);

            primaryStage.setTitle("Level 1 - Operation Cataclysm");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Typewriter intro first
            playTypewriterEffect(() -> {
                codeInput.setVisible(true);
                submitBtn.setVisible(true);
                hintbtn.setVisible(true);
            });
        }

        private void playTypewriterEffect(Runnable afterFinish) {
            Timeline timeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.millis(5), event -> {
                if (charIndex < cinematicIntro.length()) {
                    storyLabel.setText(storyLabel.getText() + cinematicIntro.charAt(charIndex));
                    charIndex++;
                } else {
                    timeline.stop();
                    afterFinish.run();
                }
            });
            timeline.getKeyFrames().add(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
        
        public class NovaLevel2 extends Application {
            private List<Integer> sequence = new ArrayList<>();
            private List<Integer> userSequence = new ArrayList<>();
            private Button[][] grid = new Button[3][3];
            private Label feedbackLabel = new Label();
            private VBox root = new VBox(20);
            private Button retryButton = new Button("Retry");
            private Label codeLabel = new Label();
            private TextField codeInput = new TextField();
            private Button nextButton = new Button("Proceed");
            private Label headingLabel = new Label("Memory Puzzle");

            private final String correctCode = "Timelimit"; // Fake code from puzzle

            @Override
            public void start(Stage primaryStage) {
                GridPane gridPane = new GridPane();
                gridPane.setAlignment(Pos.CENTER);
                gridPane.setHgap(5);
                gridPane.setVgap(5);
                gridPane.setPadding(new Insets(10));

                // Add a border to the grid
                gridPane.setStyle("-fx-border-color: white; -fx-border-width: 3px;");

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Button btn = new Button();
                        btn.setPrefSize(100, 100);
                        int index = i * 3 + j;
                        btn.setOnAction(e -> handleUserInput(index, btn));
                        grid[i][j] = btn;
                        gridPane.add(btn, j, i);
                    }
                }

                retryButton.setOnAction(e -> startSequence());
                retryButton.setVisible(true);

                codeLabel.setVisible(false);
                codeInput.setVisible(false);
                nextButton.setVisible(false);

                nextButton.setOnAction(e -> {
                    if (codeInput.getText().equals(correctCode)) {
                        new NovaLevel3().start(new Stage());
                        primaryStage.close();
                    } else {
                        feedbackLabel.setText("Incorrect Code! Try Again");
                    }
                });

                // Make Retry and Proceed button dark blue with white text
                String buttonStyle = "-fx-background-color: darkblue; -fx-text-fill: white; -fx-font-size: 14px;";
                retryButton.setStyle(buttonStyle);
                nextButton.setStyle(buttonStyle);

                // Make feedback label and other labels white
                feedbackLabel.setTextFill(Color.WHITE);
                codeLabel.setTextFill(Color.WHITE);

                // Heading label
                headingLabel.setFont(Font.font("Arial", 30));
                headingLabel.setTextFill(Color.WHITE);

                // Set textfield smaller and style it
                codeInput.setMaxWidth(200);
                codeInput.setStyle("-fx-background-color: lightgray; -fx-font-size: 14px;");

                root.setPadding(new Insets(20));
                root.setAlignment(Pos.CENTER);
                root.setBackground(new Background(new BackgroundImage(
                        new Image("file:/C:/Users/HP/Downloads/NovaL2bg.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false))));

                root.getChildren().addAll(headingLabel, gridPane, feedbackLabel, retryButton, codeLabel, codeInput, nextButton);

                startSequence();

                Scene scene = new Scene(root, 800, 600);
                primaryStage.setScene(scene);
                primaryStage.setTitle("Level 2 - Memory Puzzle");
                primaryStage.show();
            }

            private void startSequence() {
                sequence.clear();
                userSequence.clear();
                feedbackLabel.setText("");
                retryButton.setVisible(false);
                codeLabel.setVisible(false);
                codeInput.setVisible(false);
                nextButton.setVisible(false);

                Random rand = new Random();
                for (int i = 0; i < 4; i++) {
                    sequence.add(rand.nextInt(9));
                }

                Timeline timeline = new Timeline();
                Duration delay = Duration.seconds(0.5);

                for (int i = 0; i < sequence.size(); i++) {
                    int index = sequence.get(i);
                    int row = index / 3, col = index % 3;
                    timeline.getKeyFrames().add(new KeyFrame(delay.multiply(i + 1), e -> {
                        grid[row][col].setStyle("-fx-background-color: yellow");
                        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                        pause.setOnFinished(ev -> grid[row][col].setStyle(""));
                        pause.play();
                    }));
                }

                timeline.setOnFinished(e -> retryButton.setVisible(true));
                timeline.play();
            }

            private void handleUserInput(int index, Button btn) {
                if (userSequence.size() >= sequence.size()) return;

                userSequence.add(index);
                btn.setStyle("-fx-background-color: lime");
                PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
                pause.setOnFinished(ev -> btn.setStyle(""));
                pause.play();

                if (userSequence.size() == sequence.size()) {
                    if (userSequence.equals(sequence)) {
                        feedbackLabel.setText("Correct! Here's your override code: " + correctCode);
                        codeLabel.setText("Enter Override Code:");
                        codeLabel.setVisible(true);
                        codeInput.setVisible(true);
                        nextButton.setVisible(true);
                    } else {
                        feedbackLabel.setText("Wrong pattern. Try again!");
                        retryButton.setVisible(true);
                        userSequence.clear();
                    }
                }
            }

            public static void main(String[] args) {
                launch(args);
            }
        }
        
        public class NovaLevel3 extends Application {

            private List<String> selectedChemicals = new ArrayList<>();
            private Label timerLabel;
            private Timeline timeline;
            private int timeSeconds = 45;
            private VBox dropZone;

            @Override
            public void start(Stage primaryStage) {
                primaryStage.setTitle("Level 3 - Synthesize the Antidote");

                // Root pane
                BorderPane root = new BorderPane();
                
                // Background image
                BackgroundImage backgroundImage = new BackgroundImage(
                        new Image("file:/C:/Users/HP/Downloads/NovaL3bg.png"),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
                );
                root.setBackground(new Background(backgroundImage));

                // Top - Instructions
                VBox instructions = new VBox(10);
                Label instructionLabel = new Label("\u2728 Level 3: Synthesize the Antidote\nChoose 3 chemicals based on the hint!");
                instructionLabel.setTextFill(Color.CYAN);
                instructionLabel.setFont(Font.font("Consolas", 20));

                timerLabel = new Label("Time Left: 45s");
                timerLabel.setTextFill(Color.RED);
                timerLabel.setFont(Font.font("Consolas", 18));
                
                Button hintButton = new Button("Hint?");
                hintButton.setStyle("-fx-background-color: #00ffff; -fx-font-weight: bold;");
                hintButton.setOnAction(e -> showHint());
                hintButton.setEffect(new DropShadow());

                instructions.getChildren().addAll(instructionLabel, timerLabel,hintButton);
                instructions.setAlignment(Pos.CENTER);
                instructions.setPadding(new Insets(20));
                root.setTop(instructions);

                // Center - Drop Zone
                dropZone = new VBox();
                dropZone.setAlignment(Pos.CENTER);
                dropZone.setPadding(new Insets(20));
                dropZone.setSpacing(10);
                dropZone.setPrefSize(300, 300);
                dropZone.setStyle("-fx-border-color: cyan; -fx-border-width: 4px; -fx-background-color: rgba(0,0,0,0.4);");

                Label dropText = new Label("\u2B07\uFE0F DROP CHEMICALS HERE \u2B07\uFE0F");
                dropText.setTextFill(Color.WHITE);
                dropText.setFont(Font.font("Consolas", 18));
                dropZone.getChildren().add(dropText);

                root.setCenter(dropZone);

                // Left - Chemicals List
                VBox chemicalBox = new VBox(10);
                chemicalBox.setAlignment(Pos.CENTER);
                chemicalBox.setPadding(new Insets(20));

                String[] chemicals = {"X12", "B9", "Zeta", "L5", "Delta"};

                for (String chem : chemicals) {
                    Button chemButton = new Button(chem);
                    chemButton.setFont(Font.font("Consolas", 16));
                    chemButton.setStyle("-fx-background-color: darkcyan; -fx-text-fill: white;");

                    // Enable Dragging
                    chemButton.setOnDragDetected(event -> {
                        chemButton.startFullDrag();
                        event.consume();
                    });

                    chemButton.setOnMouseDragged(event -> {
                        if (!selectedChemicals.contains(chem) && selectedChemicals.size() < 3) {
                            selectedChemicals.add(chem);
                            Label added = new Label(chem);
                            added.setTextFill(Color.LIGHTGREEN);
                            added.setFont(Font.font("Consolas", 16));
                            dropZone.getChildren().add(added);
                        }
                    });

                    chemicalBox.getChildren().add(chemButton);
                }
                root.setLeft(chemicalBox);

                // Bottom - Synthesize Button
                Button synthesizeBtn = new Button("\u269B\uFE0F Synthesize");
                synthesizeBtn.setFont(Font.font("Consolas", 20));
                synthesizeBtn.setStyle("-fx-background-color: limegreen; -fx-text-fill: black;");
                synthesizeBtn.setOnAction(e -> synthesize(primaryStage));

                VBox bottomBox = new VBox(synthesizeBtn);
                bottomBox.setAlignment(Pos.CENTER);
                bottomBox.setPadding(new Insets(20));
                root.setBottom(bottomBox);

                // Timer
                timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                    timeSeconds--;
                    timerLabel.setText("Time Left: " + timeSeconds + "s");
                    if (timeSeconds <= 0) {
                        timeline.stop();
                        showAlert("Time's up! You failed to synthesize the antidote.");
                        resetGame();
                    }
                }));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();

                Scene scene = new Scene(root, 800, 600);
                primaryStage.setScene(scene);
                primaryStage.show();
            }

            private void synthesize(Stage primaryStage) {
                if (selectedChemicals.size() != 3) {
                    showAlert("Select exactly 3 chemicals!");
                    return;
                }

                // Let's say correct combo is: "X12", "Zeta", "L5"
                if (selectedChemicals.contains("X12") && selectedChemicals.contains("Zeta") && selectedChemicals.contains("L5")) {
                    timeline.stop();
                    showAlert("Antidote Synthesis Successful! Proceeding to Final Level.");
                    // Move to Level 4 here
                    new NovaLevel4().start(new Stage());
                    primaryStage.close();
                } else {
                    showAlert("Wrong combination! The antidote failed.");
                    resetGame();
                }
            }

            private void showAlert(String msg) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Antidote Synthesis");
                alert.setHeaderText(null);
                alert.setContentText(msg);
                alert.showAndWait();
            }
            private void showHint() {
                Alert hint = new Alert(AlertType.INFORMATION);
                hint.setTitle("Hint");
                hint.setHeaderText(null);
                hint.setContentText("Hint: Two compounds are rare isotopes (X12, Zeta), one is a stable element (L5). Choose wisely!");
                hint.showAndWait();
            }

            private void resetGame() {
                selectedChemicals.clear();
                dropZone.getChildren().clear();

                Label dropText = new Label("\u2B07\uFE0F DROP CHEMICALS HERE \u2B07\uFE0F");
                dropText.setTextFill(Color.WHITE);
                dropText.setFont(Font.font("Consolas", 18));
                dropZone.getChildren().add(dropText);

                timeSeconds = 45;
                timeline.playFromStart();
            }

            public class NovaLevel4 extends Application {
                private static final int SIZE = 8; // 8x8 grid
                private static final int CELL_SIZE = 60;
                private int playerRow = 0;
                private int playerCol = 0;
                private Rectangle[][] cells = new Rectangle[SIZE][SIZE];
                private int[][] maze = {
                        {1,0,0,0,0,0,0,0},
                        {1,1,0,2,2,2,2,0},
                        {0,1,0,0,0,0,2,0},
                        {0,1,1,1,1,0,2,0},
                        {0,0,0,0,1,0,2,0},
                        {0,2,2,0,1,1,1,0},
                        {0,2,0,0,0,0,1,0},
                        {0,1,1,1,1,0,1,1},
                };

                private VBox topBox;
                private GridPane grid;
                private BorderPane root;

                @Override
                public void start(Stage primaryStage) {
                    root = new BorderPane();

                    // Background Image
                    BackgroundImage myBI= new BackgroundImage(
                            new Image("file:/C:/Users/HP/Downloads/NovaL4bg.png",800,600,false,true),
                            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
                            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                    root.setBackground(new Background(myBI));

                    // Instructions and Hint Button
                    Label instructions = new Label("Navigate the Maze (Use W A S D Keys)");
                    instructions.setTextFill(Color.LIGHTCYAN);
                    instructions.setFont(Font.font("Consolas", FontWeight.BOLD, 20));
                    instructions.setAlignment(Pos.CENTER);

                    Button hintButton = new Button("Show Hint Path");
                    hintButton.setFont(Font.font("Consolas", FontWeight.BOLD, 16));
                    hintButton.setStyle("-fx-background-color: cyan; -fx-text-fill: black;");
                    hintButton.setEffect(new DropShadow());
                    hintButton.setOnAction(e -> showHint());

                    topBox = new VBox(10, instructions, hintButton);
                    topBox.setAlignment(Pos.CENTER);
                    topBox.setPadding(new Insets(10));
                    root.setTop(topBox);

                    // Maze Grid
                    grid = new GridPane();
                    grid.setAlignment(Pos.CENTER_LEFT); // Shift to Left
                    grid.setPadding(new Insets(0,0,0,60)); // More Left Padding
                    grid.setHgap(2);
                    grid.setVgap(2);

                    for (int row = 0; row < SIZE; row++) {
                        for (int col = 0; col < SIZE; col++) {
                            Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                            if (maze[row][col] == 0) {
                                cell.setFill(Color.BLACK); // Wall
                            } else if (maze[row][col] == 1) {
                                cell.setFill(Color.DARKGREY); // Safe path
                            } else if (maze[row][col] == 2) {
                                cell.setFill(Color.DARKRED); // Trap zone
                            }
                            cells[row][col] = cell;
                            grid.add(cell, col, row);
                        }
                    }
                    cells[playerRow][playerCol].setFill(Color.LIME); // Start point
                    cells[7][7].setFill(Color.RED); // Exit point

                    root.setCenter(grid);

                    Scene scene = new Scene(root, 800, 600);

                    scene.setOnKeyPressed(e -> {
                        int newRow = playerRow;
                        int newCol = playerCol;
                        if (e.getCode() == KeyCode.W) newRow--;
                        if (e.getCode() == KeyCode.S) newRow++;
                        if (e.getCode() == KeyCode.A) newCol--;
                        if (e.getCode() == KeyCode.D) newCol++;

                        if (isValidMove(newRow, newCol)) {
                            movePlayer(newRow, newCol);
                            if (newRow == 7 && newCol == 7) {
                                playerWins();
                            }
                        }
                    });

                    primaryStage.setTitle("Level 4 - Final Maze");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }

                private boolean isValidMove(int row, int col) {
                    return row >= 0 && row < SIZE && col >= 0 && col < SIZE && maze[row][col] != 0;
                }

                private void movePlayer(int newRow, int newCol) {
                    cells[playerRow][playerCol].setFill(maze[playerRow][playerCol] == 1 ? Color.DARKGREY : Color.DARKRED);
                    playerRow = newRow;
                    playerCol = newCol;
                    cells[playerRow][playerCol].setFill(Color.LIME);
                }

                private void showHint() {
                    int[][] hintPath = {
                            {0,0}, {1,0}, {1,1}, {2,1}, {3,1}, {3,2}, {3,3}, {3,4},
                            {4,4}, {5,4}, {5,5}, {5,6}, {6,6}, {7,6}, {7,7}
                    };
                    for (int[] pos : hintPath) {
                        int r = pos[0];
                        int c = pos[1];
                        if (!(r == playerRow && c == playerCol)) {
                            cells[r][c].setFill(Color.CYAN);
                        }
                    }
                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
                    pause.setOnFinished(e -> {
                        for (int[] pos : hintPath) {
                            int r = pos[0];
                            int c = pos[1];
                            if (!(r == playerRow && c == playerCol)) {
                                cells[r][c].setFill(maze[r][c] == 1 ? Color.DARKGREY : Color.DARKRED);
                            }
                        }
                    });
                    pause.play();
                }

                private void playerWins() {
                    // Clear Top Area
                    topBox.getChildren().clear();

                    Label winLabel = new Label("MISSION SUCCESS! ESCAPE COMPLETE.");
                    winLabel.setTextFill(Color.LAWNGREEN);
                    winLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 24));
                    winLabel.setAlignment(Pos.CENTER);

                    // Two Buttons for Endings
                    Button stopARGUSButton = new Button("Stop ARGUS (Heroic Ending)");
                    stopARGUSButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                    stopARGUSButton.setFont(Font.font("Consolas", FontWeight.BOLD, 16));
                    stopARGUSButton.setOnAction(e -> showEnding("You sacrificed yourself to stop ARGUS.\nHumanity is saved, but at a great cost. 🌎✨"));

                    Button escapeButton = new Button("Escape (Dystopian Ending)");
                    escapeButton.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
                    escapeButton.setFont(Font.font("Consolas", FontWeight.BOLD, 16));
                    escapeButton.setOnAction(e -> showEnding("You escaped, but ARGUS launched its plan.\nThe world falls into chaos. 🔥👁️"));

                    VBox endingBox = new VBox(20, winLabel, stopARGUSButton, escapeButton);
                    endingBox.setAlignment(Pos.CENTER);
                    endingBox.setPadding(new Insets(20));

                    root.setTop(endingBox);
                }

                private void showEnding(String endingText) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Final Choice Outcome");
                    alert.setHeaderText("ENDING:");
                    alert.setContentText(endingText);
                    alert.showAndWait();
                    System.exit(0); // Exit after showing the ending
                }


    public static void main(String[] args) {
        launch(args);
    }
    }}}}
