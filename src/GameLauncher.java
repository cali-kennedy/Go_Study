import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.media.*;
import java.util.ArrayList;
import java.util.List;

public class GameLauncher extends Application {

    // Constant variables to hold the background video and font for titles
    private static final String VIDEO_PATH = "resources/pokemon_bg.mp4";

    // Store questions and answers
    private final List<String> questions = new ArrayList<>();
    private final List<String> answers = new ArrayList<>();
    private String selectedCharacter;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(createMainScene(primaryStage));
        primaryStage.setTitle("Go Study!");
        primaryStage.show();
    }

    private Scene createMainScene(Stage primaryStage) {
        MediaView mediaView = VideoUtils.createBackgroundVideo(VIDEO_PATH);
        MainMenuBuilder menuBuilder = new MainMenuBuilder();
        VBox menuBox = menuBuilder.createMainMenu(
                e -> primaryStage.setScene(createCharacterSelectionScene(primaryStage)),
                e -> primaryStage.setScene(createInputScene(primaryStage)),
                e -> exitGame(primaryStage)
        );

        // Create a StackPane to layer the video background and the inputBox
        StackPane root = new StackPane(mediaView, menuBox);

        // Initialize the scene
        Scene scene = new Scene(root, 700, 400);

        // Add the css Style Sheet to the scene so that it can be used
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        return scene;
    }
    private Scene createCharacterSelectionScene(Stage primaryStage) {
        CharacterScreen characterScreen = new CharacterScreen();
        return characterScreen.createCharacterSelectionScene(
                primaryStage,
                e -> primaryStage.setScene(createMainScene(primaryStage)), // Back to menu action
                e -> {
                    selectedCharacter = characterScreen.getSelectedCharacter(); // Get selected character
                    primaryStage.setScene(createMapScene(primaryStage)); // Transition to map screen
                }
        );}



    private Scene createInputScene(Stage primaryStage) {
        QuestionInputScreen inputScreen = new QuestionInputScreen(questions, answers);

        // Sets up the user interface for the question input screen.
        // It requires two parameters:
        // - 'primaryStage': This represents the main window (stage) where the scene will be displayed.
        // - 'e -> primaryStage.setScene(createMainScene(primaryStage))': This lambda expression handles the action
        //   for returning to the main menu. When the "Back to Main Menu" button is clicked, it sets the scene of the
        //   primaryStage back to the main menu scene created by 'createMainScene(primaryStage)'.
        return inputScreen.createQuestionInputScene(primaryStage, e -> primaryStage.setScene(createMainScene(primaryStage)));
    }


    private Scene createMapScene(Stage primaryStage) {
        MapScreen mapScreen = new MapScreen(selectedCharacter);
        return mapScreen.createMapScene(primaryStage);
    }


    private void exitGame(Stage stage) {
        System.out.println("Exiting the game...");
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
