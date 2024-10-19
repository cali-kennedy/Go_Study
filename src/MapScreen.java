import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MapScreen {
    private String selectedCharacter;

    private double cameraX = 0;
    private double cameraY = 0;
    private double characterX = 300;  // Initial character position (center of viewport)
    private double characterY = 300;

    // Constants for map and screen size
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;
    private static final double PLAYER_SPEED = 5;
    private final int viewportWidth = 600;
    private final int viewportHeight = 400;

    // Character image
    private Image characterImage;
    // Custom map image
    private Image mapImage;

    public MapScreen(String selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
        this.characterImage = createCharacterImage(selectedCharacter); // Load character image once
        this.mapImage = new Image(getClass().getResourceAsStream("/maps/td-map.png")); // Load the custom map
    }

    public Scene createMapScene(Stage primaryStage) {
        // Load the map and character image
        Canvas canvas = new Canvas(viewportWidth, viewportHeight);  // Viewport size (what is visible on screen)
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Set up a Pane for the whole map (including the player)
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        // Set up key event handling for player movement
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Ensure the pane has focus so it can detect key presses
        root.setOnMouseClicked(event -> root.requestFocus());
        root.requestFocus(); // Request focus when the scene starts

        scene.setOnKeyPressed(event -> handleKeyPress(event, gc));

        // Initial rendering of the game
        drawGame(gc);
        return scene;
    }

    private void updateCamera() {
        // Center the camera on the character
        cameraX = characterX - viewportWidth / 2;
        cameraY = characterY - viewportHeight / 2;

        // Clamp the camera so it doesn't go out of bounds of the map
        cameraX = Math.max(0, Math.min(cameraX, mapImage.getWidth() - viewportWidth));
        cameraY = Math.max(0, Math.min(cameraY, mapImage.getHeight() - viewportHeight));
    }

    // Handle key press events to move the player and adjust the camera
    private void handleKeyPress(KeyEvent event, GraphicsContext gc) {
        double movementSpeed = PLAYER_SPEED;

        // Check if SHIFT is pressed to make the character run
        if (event.isShiftDown()) {
            movementSpeed *= 2;  // Double the speed for running
        }

        switch (event.getCode()) {
            case W:  // Move up
                characterY = Math.max(0, characterY - movementSpeed);
                break;
            case S:  // Move down
                characterY = Math.min(mapImage.getHeight() - 20, characterY + movementSpeed);  // Adjust for character height
                break;
            case A:  // Move left
                characterX = Math.max(0, characterX - movementSpeed);
                break;
            case D:  // Move right
                characterX = Math.min(mapImage.getWidth() - 20, characterX + movementSpeed);  // Adjust for character width
                break;
            default:
                return;
        }

        // Update the camera position after the character moves
        updateCamera();

        // Redraw the game after movement
        drawGame(gc);
    }

    private void drawGame(GraphicsContext gc) {
        // Clear the canvas
        gc.clearRect(0, 0, viewportWidth, viewportHeight);

        // Draw the part of the custom map visible in the viewport
        gc.drawImage(mapImage, cameraX, cameraY, viewportWidth, viewportHeight, 0, 0, viewportWidth, viewportHeight);

        // Draw the character relative to the camera's position
        double characterWidth = 20;  // Character width
        double characterHeight = 20; // Character height
        gc.drawImage(characterImage, characterX - cameraX, characterY - cameraY, characterWidth, characterHeight);
    }

    // Create the player character image
    private Image createCharacterImage(String character) {
        String imagePath = "/characters/soot.png";
        if (character.equals("Calcifer")) {
            imagePath = "/characters/fire.png";
        }
        return new Image(getClass().getResourceAsStream(imagePath));
    }
}
