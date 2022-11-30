package game.java.entities.objects;

import game.java.Animation;
import game.java.Handler;
import game.java.assetManagement.ImageManager;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerSuper extends GameObject {

    private final Handler handler;
    private final Animation[] animations = new Animation[1];
    GameObject player;
    private int count;

    public PlayerSuper(int x, int y, EntityID entityId, Handler handler) {
        super(x, y, entityId);
        this.handler = handler;
        ImageManager imageManager = new ImageManager(entityId);
        animations[0] = new Animation(6, imageManager.playerSuperGraphics());
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == EntityID.PLAYER) {
                player = handler.object.get(i);
            }
        }
    }

    public void tick() {
        y -= velocity;
        count +=1;
        if (count > 75) {
            count = 0;
            handler.removeProjectile(this);
        }
        animations[0].runAnimation();
        setX(player.getX() - 64);
        setY(player.getY() - 680);
    }

    public void render(Graphics g) {
        animations[0].drawAnimation(g, x, y,190,700);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 190, 700);
    }
}
