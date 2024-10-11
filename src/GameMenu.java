import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the label for the title
        Label titleLabel = new Label("===== Go Study! Menu =====");
        // Create buttons for menu options
        Button startGameButton = new Button("Start Game");
        Button inputQuestionsButton = new Button("Input Study Questions");
        Button exitButton = new Button("Exit Game");

        // Set button actions
        startGameButton.setOnAction(e -> startGame());
        inputQuestionsButton.setOnAction(e -> inputStudyQuestions());
        exitButton.setOnAction(e -> exitGame(primaryStage));

        // Create a VBox layout and add the components
        VBox root = new VBox(10, titleLabel, startGameButton, inputQuestionsButton, exitButton);
        root.setSpacing(10);
        root.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Create the scene
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Go Study!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startGame() {
        System.out.println("Starting the game...");
        // Transition to character selection screen or map
        // You can replace this with a new Scene or Stage
    }

    private void inputStudyQuestions() {
        System.out.println("Inputting study questions...");
        // Open a new window or scene for question input
        // This can be a TextArea for user input of questions and answers
    }

    private void exitGame(Stage stage) {
        System.out.println("Exiting the game...");
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
