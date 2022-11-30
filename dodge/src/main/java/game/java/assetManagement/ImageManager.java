package game.java.assetManagement;

import game.java.entities.EntityID;
import java.awt.image.BufferedImage;

public class ImageManager {

    BufferedImageLoader loader = new BufferedImageLoader();
    SpriteSheet bossSprites = new SpriteSheet(loader.loadImage("/boss.png"));
    SpriteSheet playerSprites = new SpriteSheet(loader.loadImage("/player.png"));
    SpriteSheet bossProjectileSprites = new SpriteSheet(loader.loadImage("/slime1.png"));
    SpriteSheet playerProjectileSprites = new SpriteSheet(loader.loadImage("/fireball.png"));
    SpriteSheet playerSuperSprites = new SpriteSheet(loader.loadImage("/super.png"));
    BufferedImage[][] bossImages = new BufferedImage[2][8]; // Grid layout, first list = "row number", second = column
    BufferedImage[][] playerImages = new BufferedImage[2][4];
    BufferedImage[][] bossProjectiles = new BufferedImage[5][4];
    BufferedImage[][] playerProjectiles = new BufferedImage[1][4];
    BufferedImage[] playerSuperAttack = new BufferedImage[3];

    /** The constructor for ImageManager, loads graphics for matching ID*/
    public ImageManager(EntityID entityID){
        if(entityID == EntityID.BOSS) boss();
        if(entityID == EntityID.PLAYER) player();
        if(entityID == EntityID.BOSS_PROJECTILE) bossProjectile();
        if(entityID == EntityID.PLAYER_PROJECTILE) playerProjectile();
        if(entityID == EntityID.PLAYER_PROJECTILE) playerSuper();
    }

    /** Facing right = true<P> Facing left = false*/
    public BufferedImage[] bossGraphics(boolean right) {
        if (right){
            return bossImages[0];
        }
        else   return bossImages[1];
    }

    /** Facing right = true<P> Facing left = false*/
    public BufferedImage[] playerGraphics(boolean right){
        if(right){
            return playerImages[0];
        }
        else return playerImages[1];
    }

    /** 0 = straight down, 1&2 right, 3&4 left*/
    public BufferedImage[] bossProjectileGraphics(int velX){
        if(velX == 0) return bossProjectiles[0];
        else return bossProjectiles[0];
    }

    public BufferedImage[] playerProjectileGraphics(){
        return playerProjectiles[0];
    }

    public BufferedImage[] playerSuperGraphics(){
        return playerSuperAttack;
    }

    private void player(){
        playerImages[0][0] =playerSprites.getSprite(1,1, 32, 32);
        playerImages[0][1] =playerSprites.getSprite(1,2, 32, 32);
        playerImages[0][2] =playerSprites.getSprite(1,3, 32, 32);
        playerImages[0][3] =playerSprites.getSprite(1,4, 32, 32);
        playerImages[1][0] =playerSprites.getSprite(2,4, 32, 32);
        playerImages[1][1] =playerSprites.getSprite(2,3, 32, 32);
        playerImages[1][2] =playerSprites.getSprite(2,2, 32, 32);
        playerImages[1][3] =playerSprites.getSprite(2,1, 32, 32);
    }

    private void boss(){
        bossImages[0][0] = bossSprites.getSprite(2,1,80,80);
        bossImages[0][1] = bossSprites.getSprite(2,2,80,80);
        bossImages[0][2] = bossSprites.getSprite(2,3,80,80);
        bossImages[0][3] = bossSprites.getSprite(2,4,80,80);
        bossImages[0][4] = bossSprites.getSprite(2,5,80,80);
        bossImages[0][5] = bossSprites.getSprite(2,6,80,80);
        bossImages[0][6] = bossSprites.getSprite(2,7,80,80);
        bossImages[0][7] = bossSprites.getSprite(2,8,80,80);
        bossImages[1][0] = bossSprites.getSprite(1,8,80,80);
        bossImages[1][1] = bossSprites.getSprite(1,7,80,80);
        bossImages[1][2] = bossSprites.getSprite(1,6,80,80);
        bossImages[1][3] = bossSprites.getSprite(1,5,80,80);
        bossImages[1][4] = bossSprites.getSprite(1,4,80,80);
        bossImages[1][5] = bossSprites.getSprite(1,3,80,80);
        bossImages[1][6] = bossSprites.getSprite(1,2,80,80);
        bossImages[1][7] = bossSprites.getSprite(1,1,80,80);
    }

    private void bossProjectile(){
        bossProjectiles[0][0] = bossProjectileSprites.getSprite(1,1,20,20);
        bossProjectiles[0][1] = bossProjectileSprites.getSprite(1,2,20,20);
        bossProjectiles[0][2] = bossProjectileSprites.getSprite(1,3,20,20);
        bossProjectiles[0][3] = bossProjectileSprites.getSprite(1,4,20,20);
    }

    private void playerProjectile(){
        playerProjectiles[0][0] = playerProjectileSprites.getSprite(1,1,160,580);
        playerProjectiles[0][1] = playerProjectileSprites.getSprite(1,2,160,580);
        playerProjectiles[0][2] = playerProjectileSprites.getSprite(2,1,160,580);
        playerProjectiles[0][3] = playerProjectileSprites.getSprite(2,2,160,580);
    }

    private void playerSuper(){
        playerSuperAttack[0] = playerSuperSprites.getSprite(1,1,190,190);
        playerSuperAttack[1] = playerSuperSprites.getSprite(1,2,190,190);
        playerSuperAttack[2] = playerSuperSprites.getSprite(1,3,190,190);
    }
}
