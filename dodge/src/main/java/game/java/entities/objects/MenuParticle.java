package game.java.entities.objects;

import game.java.Game;
import game.java.Handler;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {

    private final Handler handler;
    private final Color color;

    /**
     * The constructor for MenuParticle. Takes spawn coordinates, ID and handler.
     * Gives each MenuParticle a random velocity and color.
     * @param x
     * @param y
     * @param entityId
     * @param handler
     */
    public MenuParticle(int x, int y, EntityID entityId, Handler handler) {
        super(x, y, entityId);
        this.handler = handler;
        Random r = new Random();
        // Sets a random velocity for flavour
        trajectory = r.nextInt(8) - 4;
        velocity = r.nextInt(8) - 4;
        // Sets a velocity if the randomly assigned velocity is 0
        if(trajectory == 0) trajectory = 5;
        if(velocity == 0) velocity = 5;
        // Sets a random color to each MenuParticle for flavour
        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    /**
     * Controls the movement pattern of the MenuParticle.
     * Inverts velocity when hitting game borders.
     * <p>
     * Adds a Trail to the MenuParticle
     */
    public void tick() {
        x += trajectory;
        y += velocity;
        // Inverts the entities' speed when hitting a border
        if(y <= 0 || y >= Game.HEIGHT - 48) velocity *= -1;
        if(x <= 0 || x >= Game.WIDTH - 32) trajectory *= -1;
        handler.addObject(new Trail(x, y, EntityID.TRAIL, color, 16, 16, 0.05f, handler));
    }

    /**
     * Handles the rendering of the MenuParticle
     * @param g
     */
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 16, 16);
    }

    /**
     * Handles the boundaries of the MenuParticle
     * @return Coordinates and shape/size of type Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
