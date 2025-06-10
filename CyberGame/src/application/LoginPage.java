package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;

public class LoginPage extends Application {

    private static final String DB_URL = "jdbc:sqlite:/C:/Users/HP/OneDrive/Desktop/gamelogindata.db"; // Full path!

    private Scene welcomeScene, loginScene, signupScene;
    private VBox loginLayout, signupLayout;
    private Button gameStartLogin;
    private Button gameStartSignup;

    @Override
    public void start(Stage primaryStage) {
        try {
            createTableIfNotExists();

            // Load background image
            BackgroundImage bgImage = new BackgroundImage(
                    new Image("file:/C:/Users/HP/Downloads/Loginpagebg.png"), // <-- change to your BG image path
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false)
            );
            // Initialize global gameStart button here!
            gameStartLogin = createStyledButton("Enter Game");
            gameStartLogin.setVisible(false);
            gameStartLogin.setOnAction(e -> {
                Main obj = new Main();
                obj.start(primaryStage);
            });
            
            gameStartSignup = createStyledButton("Enter Game");
            gameStartSignup.setVisible(false);
            gameStartSignup.setOnAction(e -> {
                Main obj = new Main();
                obj.start(primaryStage);
            });

            // Welcome Layout
            VBox welcomeLayout = new VBox(15);
            welcomeLayout.setAlignment(Pos.CENTER);
            welcomeLayout.setBackground(new Background(bgImage));

            Text welcomeText = new Text("WELCOME TO CYBER HEIST");
            welcomeText.setFill(Color.WHITE);
            welcomeText.setFont(Font.font("Verdana", 35));
            welcomeText.setEffect(new DropShadow(10, Color.CYAN));

            Button loginButton = createStyledButton("Login");
            Button signupButton = createStyledButton("Sign Up");

            loginButton.setOnAction(e -> primaryStage.setScene(loginScene));
            signupButton.setOnAction(e -> primaryStage.setScene(signupScene));

            welcomeLayout.getChildren().addAll(welcomeText, loginButton, signupButton);
            welcomeScene = new Scene(welcomeLayout, 800, 600);

            // Login Layout
            loginLayout = new VBox(20);
            loginLayout.setAlignment(Pos.CENTER);
            loginLayout.setPadding(new Insets(20));
            loginLayout.setBackground(new Background(bgImage));
            
            Label loginTitle = new Label("Login to Your Account");
            loginTitle.setFont(Font.font("Arial", 24));
            loginTitle.setTextFill(Color.WHITE);

            TextField loginNameField = new TextField();
            loginNameField.setPromptText("Enter your User Name");
            loginNameField.setMaxWidth(250);

            PasswordField loginPasswordField = new PasswordField();
            loginPasswordField.setPromptText("Enter your Password");
            loginPasswordField.setMaxWidth(250);

            Button loginSubmit = createStyledButton("Login");
            Button loginBack = createStyledButton("Back");

            Label messageLabel = new Label();
            messageLabel.setTextFill(Color.YELLOW);
            messageLabel.setFont(Font.font(16));
            
            loginSubmit.setOnAction(e -> {
                if (checkUser(loginNameField.getText().trim(), loginPasswordField.getText().trim())) {
                    messageLabel.setText("Login Successful! 🎯");
                    gameStartLogin.setVisible(true);
                } else {
                    messageLabel.setText("User not found or wrong password! ❌");
                }
            });

            loginBack.setOnAction(e -> {
                messageLabel.setText("");
                primaryStage.setScene(welcomeScene);
            });

            loginLayout.getChildren().addAll(loginTitle, loginNameField, loginPasswordField, loginSubmit, loginBack, messageLabel, gameStartLogin);
            loginScene = new Scene(loginLayout, 800, 600);

            // Signup Layout
            signupLayout = new VBox(15);
            signupLayout.setAlignment(Pos.CENTER);
            signupLayout.setBackground(new Background(bgImage));

            Label signupTitle = new Label("Create a New Account");
            signupTitle.setFont(Font.font("Arial", 24));
            signupTitle.setTextFill(Color.WHITE);

            TextField signupNameField = new TextField();
            signupNameField.setPromptText("Choose a User Name");
            signupNameField.setMaxWidth(250);

            PasswordField signupPasswordField = new PasswordField();
            signupPasswordField.setPromptText("Choose a Password");
            signupPasswordField.setMaxWidth(250);

            TextField signupEmailField = new TextField();
            signupEmailField.setPromptText("Enter your Email ID");
            signupEmailField.setMaxWidth(250);

            Button signupSubmit = createStyledButton("Sign Up");
            Button signupBack = createStyledButton("Back");

            Label signupMessageLabel = new Label();
            signupMessageLabel.setTextFill(Color.LIGHTGREEN);
            signupMessageLabel.setFont(Font.font(16));

            signupSubmit.setOnAction(e -> {
                if (!signupNameField.getText().trim().isEmpty() && !signupPasswordField.getText().trim().isEmpty() && !signupEmailField.getText().trim().isEmpty()) {
                    insertUserData(signupNameField.getText().trim(), signupPasswordField.getText().trim(), signupEmailField.getText().trim());
                    signupMessageLabel.setText("Account Created Successfully! 🚀");
                    gameStartSignup.setVisible(true);
                } else {
                    signupMessageLabel.setText("Please fill all fields!");
                }
            });

            signupBack.setOnAction(e -> {
                signupMessageLabel.setText("");
                primaryStage.setScene(welcomeScene);
            });

            signupLayout.getChildren().addAll(signupTitle, signupNameField, signupPasswordField, signupEmailField, signupSubmit, signupBack, signupMessageLabel,gameStartSignup);
            signupScene = new Scene(signupLayout, 800, 600);

            primaryStage.setScene(welcomeScene);
            primaryStage.setTitle("Cyber Heist Login Portal");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    // Method to create table if not exists
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS logindata ("
                   + "UserName TEXT NOT NULL, "
                   + "Password TEXT NOT NULL, "
                   + "EmailID TEXT NOT NULL"
                   + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to insert user data into table
    private void insertUserData(String name, String password, String email) {
        String insertSql = "INSERT INTO logindata(UserName, Password, EmailID) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            insertStmt.setString(1, name);
            insertStmt.setString(2, password);
            insertStmt.setString(3, email);
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to check user existence in database
    private boolean checkUser(String name, String password) {
        String sql = "SELECT * FROM logindata WHERE UserName = ? AND Password = ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // returns true if user found
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Connect to DB
    private Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Styled Button
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #222; -fx-text-fill: cyan; -fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 8;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: cyan; -fx-text-fill: black; -fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 8;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #222; -fx-text-fill: cyan; -fx-font-size: 18px; -fx-font-weight: bold; -fx-background-radius: 8;"));
        return button;
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}


