package game.java.entities.objects;

import game.java.Animation;
import game.java.Game;
import game.java.Handler;
import game.java.HelperFunctions;
import game.java.assetManagement.ImageManager;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import game.java.uiElements.Hud;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends GameObject {

    private final Handler handler;
    private final Random r = new Random();
    private int timer = Game.HEIGHT / 11; // initial movement before it starts strafing.
    private final int bossSize = 160;
    private int bosshp = 100;
    private boolean first_boss;
    private final Animation[] animations = new Animation[2];

    /** The constructor for the Boss class*/
    public Boss(int x, int y, EntityID entityId, Handler handler, Hud hud) {
        super(x, y, entityId);
        this.handler = handler;
        trajectory = 0;
        velocity = 2;
        ImageManager imageManager = new ImageManager(entityId);
        animations[0] = new Animation(6, imageManager.bossGraphics(true));
        animations[1] = new Animation(6, imageManager.bossGraphics(false));
        if(handler.getNumberOfBossesAlive() == 0){
            first_boss = true;
        }
    }

    /** Controls boss movement behaviour*/
    public void tick() {
        x += trajectory;
        y += velocity;

        // Stops the initial vertical velocity of the boss after coming into frame
        if(timer < 0){
            velocity = 0;
            if(trajectory == 0){
                // Initiates horizontal velocity of entity.
                trajectory = 2;
            }
            int projectiles = r.nextInt(10); // Larger number = less projectiles
            if(projectiles == 0){
                handler.addObject(new BossProjectile((int) (x + 40), y +60, EntityID.BOSS_PROJECTILE, handler));
            }
        }
        else{
            timer--;
        }
        // Reverses horizontal velocity when borders are hit.
        if(x <= 0 || x >= Game.WIDTH - (bossSize * 0.85)){
            trajectory *= -1;
        }
        collisionCheck();
        animations[0].runAnimation();
        animations[1].runAnimation();

        // Sets the number of bosses alive down by 1 whenever a boss dies in addition to removing it.
        // Done in order to keep separate HuD trackers for each boss.
        if(bosshp <= 0){
            handler.removeObject(this);
            bosshp = 100;
            handler.incrementNumberOfBossesAlive(-1);
        }
    }

    /** Collision-checker for boss*/
    public void collisionCheck(){
        for (int i = 0; i < handler.playerProjectiles.size(); i++) {
            GameObject tempObject = handler.playerProjectiles.get(i);
            if(tempObject.getId() == EntityID.PLAYER_PROJECTILE){
                if (getBounds().intersects(tempObject.getBounds())){
                    bosshp -= 1;
                }
            }
        }
    }

    /** Boss render method*/
    public void render(Graphics g) {
        if (trajectory >= 0){
            animations[0].drawAnimation(g, x - 20, y - 40, bossSize, bossSize);
        }
        else {
            animations[1].drawAnimation(g, x - 20, y - 40, bossSize, bossSize);
        }

        if(first_boss || handler.getNumberOfBossesAlive() == 1){
            g.setColor(Color.gray);
            g.fillRect(1240,150,20,300);
            g.setColor(HelperFunctions.getColor(bosshp * 1.0 / 100));
            g.fill3DRect(1240,150,20, bosshp * 3, true);
        }
        else {
            g.setColor(Color.gray);
            g.fillRect(1215,150,20,300);
            g.setColor(HelperFunctions.getColor(bosshp * 1.0 / 100));
            g.fill3DRect(1215,150,20, bosshp * 3,true);
        }
    }

    /** Boss boundary handler
     * @return Coordinates and shape/size of type Rectangle*/
    public Rectangle getBounds() {
        return new Rectangle(x, y, bossSize - 40, bossSize - 60);
    }



}

