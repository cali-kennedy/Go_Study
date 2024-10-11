import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.media.*;
import java.io.File;
import java.io.InputStream;

public class MainMenuBuilder {
    public VBox createMainMenu(EventHandler<ActionEvent> startAction,
                               EventHandler<ActionEvent> inputAction,
                               EventHandler<ActionEvent> exitAction,
                               Font titleFont) {
        Label titleLabel = new Label("< G O   S t u d y ! >");
        titleLabel.setFont(titleFont);
        titleLabel.setStyle("-fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-padding: 10px;");

        Button startGameButton = new Button("Start Game");
        startGameButton.setOnAction(startAction);

        Button inputQuestionsButton = new Button("Input Study Questions");
        inputQuestionsButton.setOnAction(inputAction);

        Button exitButton = new Button("Exit Game");
        exitButton.setOnAction(exitAction);

        // Shared button style
        String buttonStyle = "-fx-font-family: 'Monospaced'; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #FFA07A;";
        startGameButton.setStyle(buttonStyle);
        inputQuestionsButton.setStyle(buttonStyle);
        exitButton.setStyle(buttonStyle);

        VBox menuBox = new VBox(10, titleLabel, startGameButton, inputQuestionsButton, exitButton);
        menuBox.setSpacing(10);
        menuBox.setStyle("-fx-alignment: center; -fx-padding: 20;");

        return menuBox;
    }
}
