import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.media.*;
import java.util.ArrayList;
import java.util.List;

public class GameMenu extends Application {

    private static final String VIDEO_PATH = "resources/pokemon_bg.mp4";
    private static final String FONT_PATH = "/fonts/Bungee-Regular.ttf";


    // Store questions and answers
    private final List<String> questions = new ArrayList<>();
    private final List<String> answers = new ArrayList<>();

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
                e -> startGame(),
                e -> primaryStage.setScene(createInputScene(primaryStage)),
                e -> exitGame(primaryStage),
                FontUtils.loadFont(FONT_PATH, 32)
        );

        StackPane root = new StackPane(mediaView, menuBox);
        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        return scene;
    }


    private Scene createInputScene(Stage primaryStage) {
        QuestionInputScreen inputScreen = new QuestionInputScreen(questions, answers);
        return inputScreen.createQuestionInputScene(primaryStage, e -> primaryStage.setScene(createMainScene(primaryStage)));
    }

    private void startGame() {
        System.out.println("Starting the game...");
    }

    private void exitGame(Stage stage) {
        System.out.println("Exiting the game...");
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
