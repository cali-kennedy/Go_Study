import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.InputStream;
import java.util.List;

public class QuestionInputScreen {
    private List<String> questions;
    private List<String> answers;

    public QuestionInputScreen(List<String> questions, List<String> answers) {
        this.questions = questions;
        this.answers = answers;
    }

    public Scene createQuestionInputScene(Stage primaryStage, EventHandler<ActionEvent> backToMenuAction) {
       // Create background video
        MediaView mediaView = VideoUtils.createBackgroundVideo("resources/pokemon_bg.mp4");

        // Create a vertical box to display gui components
        VBox inputBox = createInputBox();

        // create a message label to hold output
        Label messageLabel = createMessageLabel();

        // Create a scrollPane so all answers can be displayed without being hidden behind another component
        ScrollPane messageScrollPane = createMessageScrollPane(messageLabel);

        // Create input fields
        TextField questionField = createInputField("Enter your question here");
        questionField.getStyleClass().add("input-field");
        TextField answerField = createInputField("Enter the corresponding answer");
        answerField.getStyleClass().add("input-field");
        // Create fields for deleting questions
        TextField questionIndexField = createInputField("Enter question number to delete");
        questionIndexField.setVisible(false); // set the text field for deleting a question to hidden initially

        // Create buttons
        Button addButton = createAddButton(questionField, answerField, messageLabel);
        Button deleteButton = createDeleteButton(questionIndexField, messageLabel);
        Button displayButton = createDisplayButton(messageLabel);
        Button backButton = createBackButton(backToMenuAction);

        // Create title
        Label title = createTitleLabel("Input Study Questions");


        // Add components to the input layout
        inputBox.getChildren().addAll(
                title,
                questionField,
                answerField,
                messageScrollPane,
                questionIndexField,
                addButton,
                deleteButton,
                displayButton,
                backButton

        );

        // Create a StackPane to layer the video background and the inputBox
        StackPane root = new StackPane(mediaView, inputBox);

        // Initialize the scene
        Scene scene = new Scene(root, 700, 400);

        // Add the css Style Sheet to the scene so that it can be used
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        return scene;
    }

    // Helper method to load a custom font
    private Font loadFont(String fontPath, double size) {
        InputStream fontStream = getClass().getResourceAsStream(fontPath);
        return Font.loadFont(fontStream, size);
    }

    // Helper method to create a VBox for input fields
    private VBox createInputBox() {
        VBox inputBox = new VBox(10);
        inputBox.setStyle("-fx-alignment: center; -fx-padding: 20;");
        return inputBox;
    }

    // Helper method to create a message label
    private Label createMessageLabel() {
        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("error-label");
        return messageLabel;
    }

    // Helper method to create a scrollable message label
    private ScrollPane createMessageScrollPane(Label messageLabel) {
        ScrollPane scrollPane = new ScrollPane(messageLabel);
        scrollPane.setFitToWidth(true); // Make sure it fits the width of the container
        scrollPane.setPrefHeight(200);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        return scrollPane;
    }

    // Helper method to create a title label
    private Label createTitleLabel(String text) {
        Label titleLabel = new Label(text);
        titleLabel.getStyleClass().add("title-label");
        return titleLabel;
    }

    // Helper method to create a text input field
    private TextField createInputField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        return textField;
    }

    // Helper method to create a styled button with default styles
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("button");
        return button;
    }

    // Helper method to create the "Add Question" button
    private Button createAddButton(TextField questionField, TextField answerField, Label messageLabel) {
        Button addButton = createStyledButton("Add Question");

        addButton.setOnAction(e -> {
            String question = questionField.getText().trim();
            String answer = answerField.getText().trim();

            if (!question.isEmpty() && !answer.isEmpty()) {
                questions.add(question);
                answers.add(answer);
                messageLabel.setText("Added: " + question + " -> " + answer);
                messageLabel.setStyle("-fx-text-fill: green;");
                questionField.clear();
                answerField.clear();
            } else {
                messageLabel.setText("Question or answer cannot be empty.");
                messageLabel.setStyle("-fx-text-fill: red;");

            }
        });
        return addButton;
    }
    // Helper method to create the "Delete Question" button
    private Button createDeleteButton(TextField questionIndexField, Label messageLabel) {
        Button deleteButton = createStyledButton("Delete Question");

        deleteButton.setOnAction(e -> {
            if (!questionIndexField.isVisible()) {
                // Show the text field if it's hidden
                questionIndexField.setVisible(true);
                messageLabel.setText("Enter the question number to delete."); // Provide guidance
                messageLabel.setStyle("-fx-text-fill: black;");
            }

            String indexText = questionIndexField.getText().trim();

            // Validate the input to ensure it is a number and a valid index
            try {
                int index = Integer.parseInt(indexText) - 1; // Convert to zero-based index

                if (index >= 0 && index < questions.size()) {
                    String removedQuestion = questions.remove(index);
                    String removedAnswer = answers.remove(index);

                    messageLabel.setText("Deleted: " + removedQuestion + " -> " + removedAnswer);
                    messageLabel.getStyleClass().add("success-label");

                    questionIndexField.clear();
                    questionIndexField.setVisible(false); // Hide the text field again after deletion
                } else {
                    messageLabel.setText("Invalid question number. Please enter a number between 1 and " + questions.size());
                    messageLabel.getStyleClass().add("error-label");
                }
            } catch (NumberFormatException ex) {
                StringBuilder questionsAndAnswers = new StringBuilder("Current Questions and Answers:\n");

                for (int i = 0; i < questions.size(); i++) {
                    questionsAndAnswers.append(i + 1)
                            .append(". Q: ")
                            .append(questions.get(i))
                            .append(" | A: ")
                            .append(answers.get(i))
                            .append("\n");
                }
                messageLabel.setText(questionsAndAnswers.toString());
                messageLabel.setStyle("-fx-text-fill: black;");
            }
        });
        return deleteButton;
    }

    // Helper method to create the "Show All Questions" button
    private Button createDisplayButton(Label messageLabel) {
        Button displayButton = createStyledButton("Show All Questions");

        displayButton.setOnAction(e -> {
            StringBuilder questionsAndAnswers = new StringBuilder("Current Questions and Answers:\n");
            for (int i = 0; i < questions.size(); i++) {
                questionsAndAnswers.append(i + 1)
                        .append(". Q: ")
                        .append(questions.get(i))
                        .append(" | A: ")
                        .append(answers.get(i))
                        .append("\n");
            }
            messageLabel.setText(questionsAndAnswers.toString());
            messageLabel.setStyle("-fx-text-fill: black;");
        });
        return displayButton;
    }

    // Helper method to create the "Back to Main Menu" button
    private Button createBackButton(EventHandler<ActionEvent> backToMenuAction) {
        Button backButton = createStyledButton("Back to Main Menu");

        backButton.setOnAction(backToMenuAction);
        return backButton;
    }
}
