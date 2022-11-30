package game.java.entities.objects;

import game.java.Game;
import game.java.Handler;
import game.java.assetManagement.SpriteSheet;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject {

    private GameObject player;
    private final BufferedImage entityImage;

    /**
     * The constructor for SmartEnemy.
     * Also grabs desired sprite & tracks player IDs.
     *
     * @param x
     * @param y
     * @param entityId
     * @param handler
     */
    public SmartEnemy(int x, int y, EntityID entityId, Handler handler) {
        super(x, y, entityId);
        // Continuously checks all the entities present to find the player.
        for (int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() == EntityID.PLAYER){
                player = handler.object.get(i);
            }
        }
        SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet);
        entityImage = spriteSheet.getVaryingSprite(2, 1, 16, 16);
    }

    /**
     * Overload for testing only
     * @param entityID
     * @param entityImage
     */
    public SmartEnemy(EntityID entityID, BufferedImage entityImage){
        super((entityID));
        this.entityImage = entityImage;
    }

    /**
     * Controls the movement pattern of SmartEnemy.
     * <p>
     * Follows the player
     */
    public void tick() {
        x += trajectory;
        y += velocity;
        // X & Y distance between the SmartEnemy and the player
        float distanceX = x - player.getX() - 8; // Slight offset to not track top-left corner of player, which is its true coordinates
        float distanceY = y - player.getY() - 8;


        float distance = (float) Math.sqrt(((x - player.getX()) * (x - player.getX())) + ((y - player.getY()) * (y - player.getY())));
        // As velocities are cast to int, multiplying by more than 1 at the end avoids the risk of the int being 0 and thus stands still.
        trajectory = (int) ((-1.0 / distance) * distanceX*4); // "* distanceX" at end increases velocityX
        velocity = (int) ((-1.0 / distance) * distanceY*4); // "* distanceY" at end increases velocityY
    }

    /**
     * Handles the sprite rendering of the SmartEnemy
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(entityImage, x, y, null);
    }

    /**
     * Handles the boundaries of the SmartEnemy
     * @return Coordinates and shape/size of type Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
