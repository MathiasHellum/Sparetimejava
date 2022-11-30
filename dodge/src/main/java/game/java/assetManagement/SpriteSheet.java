package game.java.assetManagement;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private final BufferedImage bufferedImage;

    /** The constructor for SpriteSheet. */
    public SpriteSheet(BufferedImage bufferedImage){
        this.bufferedImage = bufferedImage;
    }

    /** Gets the desired sprite from the sprite-sheet. Takes coordinate and size.*/
    public BufferedImage getSprite(int row, int col, int width, int height){
        return bufferedImage.getSubimage((col * width) - width, (row * height) - height, width, height);
    }

    /** Sprite-sheet specific, gets sprites of varying sizes from the same sprite-sheet.*/
    public BufferedImage getVaryingSprite(int row, int col, int width, int height){
        return bufferedImage.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
    }
}
