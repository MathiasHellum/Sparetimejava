package game.java.assetManagement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoader {

    private BufferedImage image;

    /**
     * Loads the image/sprite-sheet to be used from specified filepath
     * @param path the filepath of wanted image
     * @return image
     */
    public BufferedImage loadImage(String path){
        try {
            image = ImageIO.read(getClass().getResource(path));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
