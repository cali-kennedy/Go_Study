import javafx.scene.text.Font;

import java.io.InputStream;

public class FontUtils {

    /**
     * Loads a custom font from the specified path with the given size.
     *
     * @param fontPath The path to the font file within the resources folder.
     * @param size     The desired font size.
     * @return The loaded Font object, or a default font if the custom font cannot be loaded.
     */
    public static Font loadFont(String fontPath, double size) {
        InputStream fontStream = FontUtils.class.getResourceAsStream(fontPath);
        if (fontStream != null) {
            return Font.loadFont(fontStream, size);
        } else {
            System.err.println("Font file not found: " + fontPath + ". Using default font.");
            return Font.font(size); // Fall back to a default font if the custom font cannot be loaded
        }
    }
}
