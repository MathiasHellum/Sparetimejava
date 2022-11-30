package game.java.entities.objects;

import game.java.Animation;
import game.java.Handler;
import game.java.assetManagement.ImageManager;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerProjectile extends GameObject {

    private final Handler handler;
    private final Animation[] animations = new Animation[1];

    /** The constructor for PlayerProjectile. Takes spawn coordinates, ID and handler.*/
    public PlayerProjectile(int x, int y, EntityID entityId, Handler handler) {
        super(x,y,entityId);
        this.handler = handler;
        velocity = 12;
        ImageManager imageManager = new ImageManager(entityId);
        animations[0] = new Animation(15,imageManager.playerProjectileGraphics());
    }

    @Override
    public void tick() {
        y -= velocity;
            if (y < -70) {
                handler.removeProjectile(this);
            }
        animations[0].runAnimation();
    }

    /** PlayerProjectile render*/
    public void render(Graphics g) {
        animations[0].drawAnimation(g, x, y,20, 72);
    }

    /** PlayerProjectile borders*/
    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }
}
