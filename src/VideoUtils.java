import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class VideoUtils {
    // Utility method to create a MediaView for background video
    public static MediaView createBackgroundVideo(String videoPath) {
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
}
