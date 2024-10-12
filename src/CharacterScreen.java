import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CharacterScreen {
    private String selectedCharacter;

    public CharacterScreen() {
        // Constructor can be used for initializing any data if needed.
    }

    public Scene createCharacterSelectionScene(Stage primaryStage, EventHandler<ActionEvent> backToMenuAction) {
        MediaView mediaView = VideoUtils.createBackgroundVideo("resources/pokemon_bg.mp4");
        VBox characterBox = createCharacterBox();
        Label titleLabel = createTitleLabel("Select Your Character");
        Label selectedCharacterLabel = new Label();
        selectedCharacterLabel.getStyleClass().add("selectedCharacter-label");


        // Create a button to confirm the selection and proceed
        Button confirmButton = createConfirmButton(primaryStage, selectedCharacterLabel);
        Button backButton = createBackButton(backToMenuAction);

        // Create character selection buttons
        Button character1Button = createCharacterButton("Character 1", "/characters/soot.png", selectedCharacterLabel);



        HBox characterSelectionBox = new HBox(20, character1Button);
        characterSelectionBox.setStyle("-fx-alignment: center; -fx-padding: 20;");

        // Add components to the layout
        characterBox.getChildren().addAll(titleLabel, characterSelectionBox, selectedCharacterLabel, confirmButton, backButton);

        // Create a StackPane to overlay the media view and the character box
        StackPane root = new StackPane(mediaView, characterBox);
        Scene scene = new Scene(root, 700, 400);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        return scene;
    }

    private VBox createCharacterBox() {
        VBox characterBox = new VBox(10);
        characterBox.setStyle("-fx-alignment: center; -fx-padding: 20;");
        return characterBox;
    }

    private Label createTitleLabel(String text) {
        Label titleLabel = new Label(text);
        titleLabel.getStyleClass().add("title-label");
        Font titleFont = FontUtils.loadFont("/fonts/Bungee-Regular.ttf", 32);
        titleLabel.setFont(titleFont);
        return titleLabel;
    }

    private Button createCharacterButton(String characterName, String imagePath, Label selectedCharacterLabel) {
        Button characterButton = new Button(characterName);
        characterButton.setGraphic(createCharacterImage(imagePath));
        characterButton.getStyleClass().add("character-button");
        characterButton.setOnAction(e -> {
            selectedCharacter = characterName;
            selectedCharacterLabel.setText("Selected: " + characterName);
           // selectedCharacterLabel.getStyleClass().add("success-label");
        });
        return characterButton;
    }

    private ImageView createCharacterImage(String imagePath) {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        return imageView;
    }

    private Button createConfirmButton(Stage primaryStage, Label selectedCharacterLabel) {
        Button confirmButton = new Button("Confirm Selection");
        confirmButton.getStyleClass().add("button");
        confirmButton.setOnAction(e -> {
            if (selectedCharacter != null) {
                System.out.println("Character confirmed: " + selectedCharacter);
                // Proceed to the next part of the game, e.g., start the game scene
            } else {
                selectedCharacterLabel.setText("Please select a character.");
                selectedCharacterLabel.getStyleClass().add("error-label");
            }
        });
        return confirmButton;
    }

    private Button createBackButton(EventHandler<ActionEvent> backToMenuAction) {
        Button backButton = new Button("Back to Main Menu");
        backButton.getStyleClass().add("button");
        backButton.setOnAction(backToMenuAction);
        return backButton;
    }
}
