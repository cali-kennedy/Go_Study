import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.media.*;

import java.io.File;
import java.io.InputStream;

import static java.awt.Color.*;

public class GameMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a label for the Title and set its font
        Label titleLabel = new Label("< G O   S t u d y ! >");
        Font sketchfont = loadFont("/fonts/Bungee-Regular.ttf", 32);
        titleLabel.setFont(sketchfont);

        // Create the scene
        primaryStage.setScene(createMainScene(primaryStage));
        primaryStage.setTitle("Go Study!");
        primaryStage.show();
    }

    private Scene createMainScene(Stage primaryStage) {
        MediaView mediaView = createBackgroundVideo("resources/pokemon_bg.mp4");
        MainMenuBuilder menuBuilder = new MainMenuBuilder();
        VBox menuBox = menuBuilder.createMainMenu(
                e -> startGame(),
                e -> inputStudyQuestions(),
                e -> exitGame(primaryStage),
                loadFont("/fonts/Bungee-Regular.ttf", 32)
        );

        StackPane root = new StackPane(mediaView, menuBox);
        return new Scene(root, 700, 400);
    }

    private MediaView createBackgroundVideo(String videoPath) {
        Media media = new Media(new File(videoPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Set the MediaView to take up the whole background
        mediaView.setFitWidth(1500);
        mediaView.setFitHeight(1500);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the video
        mediaPlayer.play();

        return mediaView;
    }

    private Font loadFont(String fontPath, double size) {
        InputStream fontStream = getClass().getResourceAsStream(fontPath);
        return Font.loadFont(fontStream, size);
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
