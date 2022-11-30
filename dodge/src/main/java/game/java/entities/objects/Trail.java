package game.java.entities.objects;

import game.java.Handler;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.Rectangle;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Trail extends GameObject {

    private float alpha = 1;
    private final float life;
    private final Handler handler;
    private final Color color;
    private final int width, height;

    /** The constructor for Trail. Takes spawn coordinates, ID, color, size, "life expectancy" and handler*/
    public Trail(int x, int y, EntityID entityId, Color color, int width, int height, float life, Handler handler) {
        super(x, y, entityId);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    public void tick() {
        if(alpha > life){
            alpha -= (life - 0.0001f);
        }
        else
            handler.removeObject(this);
    }

    /**
     * Handles the rendering of the Trail
     * @param g
     */
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillOval(x, y, width, height);
        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    /**
     * Handles the boundaries of the Trail
     * @return Coordinates and shape/size of type Rectangle
     */
    public Rectangle getBounds() {
        return null;
    }
}
