package game.java.entities.objects;

import game.java.Animation;
import game.java.Game;
import game.java.Handler;
import game.java.HelperFunctions;
import game.java.assetManagement.ImageManager;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import game.java.uiElements.Hud;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

    private final Handler handler;
    private final Hud hud;
    private int intraShotCd, powerful_CD;
    private boolean powerful_UP;
    private final Animation[] animations = new Animation[2];

    /**
     * The constructor for Player. Takes spawn coordinates, ID, handler and hud.
     * Also grabs desired sprite.
     * @param x
     * @param y
     * @param entityId
     * @param handler
     * @param hud
     */
    public Player(int x, int y, EntityID entityId, Handler handler, Hud hud){
        super(x, y, entityId);
        this.handler = handler;
        this.hud = hud;
        ImageManager imageManager = new ImageManager(entityId);
        animations[0] = new Animation(10, imageManager.playerGraphics(true));
        animations[1] = new Animation(10, imageManager.playerGraphics(false));

    }

    /** Constructor for testing only.*/
    public Player(EntityID player, Handler handler, Hud hud, BufferedImage entityImage) {
        super(player);
        this.handler = handler;
        this.hud = hud;
    }

    /**
     * Controls the allowed movement of the player.
     * Also calls on the collisionCheck method.
     */
    public void tick() {
        x += trajectory;
        y += velocity;
        intraShotCd++;
        if(powerful_CD < 200) powerful_CD++;
        else powerful_UP = true;
        // border coordinate limits for the player. Magic numbers hard to avoid due to sprites having a fixed size
        x = HelperFunctions.clamp(x, -16, Game.WIDTH - 64);
        y = HelperFunctions.clamp(y, -16, Game.HEIGHT - 96);

        if(attacking && intraShotCd >= 10 && hud.ammo > 0){
            intraShotCd = 0;
            handler.addProjectile(new PlayerProjectile(x + 20, y + 128, EntityID.PLAYER_PROJECTILE, handler));
            hud.ammo -= 1;
        }
        if(powerful_attack && powerful_UP){
            powerful_CD = 0;
            powerful_UP = false;
            handler.addProjectile(new PlayerSuper(x - 64, y - 675, EntityID.PLAYER_PROJECTILE, handler));
        }

        collisionCheck();
        animations[0].runAnimation();
        animations[1].runAnimation();
    }

    /**
     * Tracks collision between the player and all enemies
     */
    public void collisionCheck(){
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            // Colliding with all grunts except HardEnemy. Steadily drains player health
            if (tempObject.getId() == EntityID.BASIC_ENEMY || tempObject.getId() == EntityID.FAST_ENEMY || tempObject.getId() == EntityID.SMART_ENEMY || tempObject.getId() == EntityID.BOSS_PROJECTILE){
                if (getBounds().intersects(tempObject.getBounds())) {
                    // What happens:
                    if(hud.shield > 0){
                        hud.shield -= 2;
                    }
                    else {
                        hud.health -= 2;
                    }
                }
            }
            // Colliding with HardEnemy. Ignore shields and drains player health 50% faster than regular grunts.
            if (tempObject.getId() == EntityID.HARD_ENEMY){
                if(getBounds().intersects(tempObject.getBounds())){
                    // What happens
                    hud.health -= 3;
                }
            }
            // Colliding with a boss. Rapidly drains player health
            if (tempObject.getId() == EntityID.BOSS) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    // What happens:
                    if(hud.shield > 0){
                        hud.shield -= 10;
                    }
                    else{
                        hud.health -= 10;
                    }
                }
            }
        }
    }

    /** Handles the sprite rendering of the Player*/
    public void render(Graphics g) {
        if(handler.getObjectDirection("player")){
            animations[0].drawAnimation(g, x, y, 64, 64);
        }
        else {
            animations[1].drawAnimation(g, x, y, 64, 64);
        }

        g.drawString(""+powerful_CD /5, 200,200);
    }

    /**
     * Handles the boundaries of the Player
     * @return Coordinates and shape/size of type Rectangle
     */
    public Rectangle getBounds(){
        return new Rectangle(x, y, 64, 64);
    }
}
