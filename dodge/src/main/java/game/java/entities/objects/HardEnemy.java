package game.java.entities.objects;

import game.java.Game;
import game.java.Handler;
import game.java.assetManagement.SpriteSheet;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class HardEnemy extends GameObject {

    private final BufferedImage entityImage;
    private final Random r = new Random();

    /**
     * The constructor for HardEnemy. Takes spawn coordinates, ID and handler.
     * Also grabs desired sprite.
     * @param x
     * @param y
     * @param entityId
     * @param handler
     */
    public HardEnemy(int x, int y, EntityID entityId, Handler handler) {
        super(x, y, entityId);
        trajectory = 5;
        velocity = 5;
        SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet);
        entityImage = spriteSheet.getVaryingSprite(2, 2, 16, 16);
    }

    /**
     * Controls the movement pattern of the HardEnemy.
     * Inverts velocity by a random set amount when hitting game borders.
     */
    public void tick() {
        x += trajectory;
        y += velocity;
        // (the movement of this enemy sometimes bugs out when hitting game borders)
        // Inverts the entities' speed but with a randomized velocity.

        // NB!! Matten bak denne tilfeldiggjøringen av hastighet fikk jeg hjelp til av en venn utenfor studiet.

        if(x <= 0 || x >= Game.WIDTH - 32){
            if(trajectory <= 0){
                trajectory = -(r.nextInt(10) + 1) * -1;
            }
            else {
                trajectory = (r.nextInt(10) + 1) * -1;
            }
        }
        if(y <= 0 || y >= Game.HEIGHT - 48){
            if (velocity <= 0){
                velocity = -(r.nextInt(10) + 1) * -1;
            }
            else {
                velocity = (r.nextInt(10) + 1) * -1;
            }
        }
    }

    /**
     * Handles the sprite rendering of the HardEnemy
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(entityImage, x, y, null);
    }

    /**
     * Handles the boundaries of the HardEnemy
     * @return Coordinates and shape/size of type Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
