package game.java.entities.objects;

import game.java.Game;
import game.java.Handler;
import game.java.assetManagement.SpriteSheet;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FastEnemy extends GameObject {

    private final BufferedImage entityImage;

    /**
     * The constructor for FastEnemy. Takes spawn coordinates, ID and handler.
     * Also grabs desired sprite.
     * @param x
     * @param y
     * @param entityId
     * @param handler
     */
    public FastEnemy(int x, int y, EntityID entityId, Handler handler) {
        super(x, y, entityId);
        trajectory = 10;
        velocity = 10;
        SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet);
        entityImage = spriteSheet.getVaryingSprite(1, 3, 16, 16);
    }

    /**
     * Controls the movement pattern of the FastEnemy.
     * Inverts velocity when hitting game borders.
     */
    public void tick() {
        x += trajectory;
        y += velocity;
        if(y <= 0 || y >= Game.HEIGHT - 48) velocity *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) trajectory *= -1;
    }

    /**
     * Handles the sprite rendering of the FastEnemy
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(entityImage, x, y, 16, 16, null);
    }

    /**
     * Handles the boundaries of the FastEnemy
     * @return Coordinates and shape/size of type Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
