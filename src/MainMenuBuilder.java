import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class MainMenuBuilder {
    public VBox createMainMenu(EventHandler<ActionEvent> startAction,
                               EventHandler<ActionEvent> inputAction,
                               EventHandler<ActionEvent> exitAction) {

        Label titleLabel = new Label("< G O   S t u d y ! >");

        titleLabel.getStyleClass().add("title-label");

        Button startGameButton = new Button("Start Game");
        startGameButton.setOnAction(startAction);
        startGameButton.getStyleClass().add("button");

        Button inputQuestionsButton = new Button("Input Study Questions");
        inputQuestionsButton.setOnAction(inputAction);
        inputQuestionsButton.getStyleClass().add("button");

        Button exitButton = new Button("Exit Game");
        exitButton.setOnAction(exitAction);
        exitButton.getStyleClass().add("exit-button");


        VBox menuBox = new VBox(10, titleLabel, startGameButton, inputQuestionsButton, exitButton);
        menuBox.setSpacing(10);
        menuBox.setStyle("-fx-alignment: center; -fx-padding: 20;");

        return menuBox;
    }
}
