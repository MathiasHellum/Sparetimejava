package game.java.entities.objects;

import game.java.Game;
import game.java.Handler;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.*;
import java.util.Random;

public class BossProjectile extends GameObject {

    private final Handler handler;

    /**
     * The constructor for BossProjectile. Takes spawn coordinates, ID and handler.
     * @param x
     * @param y
     * @param entityId
     * @param handler
     */
    public BossProjectile(int x, int y, EntityID entityId, Handler handler) {
        super(x, y, entityId);
        this.handler = handler;
        Random r = new Random();
        //Gives a random number from -2 to (not including) 3
        trajectory = r.nextInt(6) - 2; // Projectile trajectory
        velocity = 5; // Projectile velocity
    }

    /**
     * Gets the randomly generated trajectory of the boss projectile
     */
    int getVelX(){
        int angle;
        if (trajectory < -1){
            angle = 2;
        }
        else if(trajectory > -2 && trajectory < 2){
            angle = 3;
        }
        else
            angle = 1;
        return angle;
    }

    /**
     * Controls the movement pattern of projectiles.
     * Eventually removes the projectiles when they travel out of frame
     */
    public void tick() {
        x += trajectory;
        y += velocity;

        // Removes the projectile when it reaches screen borders
        if(y > Game.HEIGHT || (x < 0 || x > Game.WIDTH)){
            handler.removeObject(this);
        }
    }

    /** Handles the rendering of the projectile*/
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(x,y,20,20);
    }

    /**
     * Handles the boundaries of the projectile
     * @return Coordinates and shape/size of type Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }
}
