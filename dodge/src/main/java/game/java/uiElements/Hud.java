package game.java.uiElements;

import game.java.HelperFunctions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Objects;

public class Hud {

    public int health = 100;
    public int shield;
    private int points;
    private int level = 1;
    public int bossHP1 = 100;
    public int bossHP2 = 100;
    public int ammo = 20;
    private int intraRoundAmmo;

    /**
     * The tick function for Hud.
     * Handles score, health and shield.
     */
    public void tick(){
        health = HelperFunctions.clamp(health, 0, 100);
        bossHP1 = HelperFunctions.clamp(bossHP1, 0, bossHP1);
        bossHP2 = HelperFunctions.clamp(bossHP2, 0, bossHP2);
        // Setting the rgb green value relative to health gives the option to
        // have a "darkening/reddening" health bar as it gets low.
        shield = HelperFunctions.clamp(shield, 0, 100);
        // Score increases with every tick
        points++;
        intraRoundAmmo++;
        if(intraRoundAmmo >= 150){
            intraRoundAmmo = 0;
            if(ammo < 20){
                ammo += 1;
            }
        }
    }

    /**
     * The render function for Hud
     * @param g
     */
    public void render(Graphics g){
        // Backdrop for health bar
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 25);

        // Health bar. New color every tick gives a redder health bar as it gets lower
        g.setColor(HelperFunctions.getColor(health * 1.0 / 100));
        // Multiplied by 2 to always stay proportionate to the health bar
        g.fill3DRect(15, 15, health * 2, 25,true);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 25);

        // Backdrop for armor bar
        g.setColor(Color.gray);
        g.fillRect(15, 50, 200, 25);
        // Armor bar
        g.setColor(new Color(134,155,240));// sexy blue xD
        g.fill3DRect(15, 50, shield * 2, 25,true);
        g.setColor(Color.white);
        g.drawRect(15, 50,200, 25);

        // Stat display
        g.drawString(health + "%", 100, 32);
        g.setFont(new Font("arial", Font.BOLD, 18));
        g.drawString("Score: " + points, 15, 100);
        g.drawString("Level: " + level, 15, 120);
        g.drawString("Ammo: " + ammo, 15, 140);
    }

    /**
     * Getter for score
     * @return score
     */
    public int getScore(){
        return points;
    }

    /**
     * Setter for score
     * @param score
     */
    public void setScore(int score){
        this.points = score;
    }

    /**
     * Getter for level
     * @return level
     */
    public int getLevel(){
        return level;
    }

    /**
     * Setter for level
     * @param level
     */
    public void setLevel(int level){
        this.level = level;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hud)) return false;
        Hud hud = (Hud) o;
        return health == hud.health && shield == hud.shield && points == hud.points && level == hud.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, shield, points, level);
    }

    @Override
    public String toString() {
        return "Hud{" +
                "health=" + health +
                ", shield=" + shield +
                ", points=" + points +
                ", level=" + level +
                '}';
    }
}
