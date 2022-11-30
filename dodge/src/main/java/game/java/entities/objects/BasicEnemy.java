package game.java.entities.objects;

import game.java.Game;
import game.java.Handler;
import game.java.assetManagement.SpriteSheet;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {

    private final BufferedImage entityImage;

    /**
     * The constructor for BasicEnemy.
     * Also grabs desired sprite.
     * @param x
     * @param y
     * @param entityId
     * @param handler
     */
    public BasicEnemy(int x, int y, EntityID entityId, Handler handler) {
        super(x, y, entityId);
        trajectory = 5;
        velocity = 5;
        SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet);
        entityImage = spriteSheet.getVaryingSprite(1, 2, 16, 16);
    }

    /**
     * Overload for testing only
     * @param entityID
     * @param entityImage
     */
    public BasicEnemy(EntityID entityID, BufferedImage entityImage){
        super((entityID));
        this.entityImage = entityImage;
    }

    /**
     * Controls the movement pattern of the BasicEnemy.
     * Inverts velocity when hitting game borders.
     */
    public void tick() {
        x += trajectory;
        y += velocity;
        // Inverts the entities' speed when hitting a border
        if(y <= 0 || y >= Game.HEIGHT - 48) velocity *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) trajectory *= -1;
    }

    /**
     * Handles the sprite rendering of the BasicEnemy
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(entityImage, x, y, null);
    }

    /**
     * Handles the boundaries of the BasicEnemy
     * @return Coordinates and shape/size of type Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
