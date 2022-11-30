package game.java.uiElements;

import game.java.controls.MouseInput;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public class Store extends MouseAdapter {

    private final Hud hud;
    private final MouseInput mouseInput;
    private final Font fontLarge = new Font("arial", Font.BOLD, 50);
    private final Font fontMedium = new Font("arial", Font.BOLD, 20);
    private final Font fontSmall = new Font("arial", Font.BOLD, 12);

    /** The constructor for Shop*/
    public Store(Hud hud, MouseInput mouseInput){
        this.hud = hud;
        this.mouseInput = mouseInput;
    }

    /** Handles the rendering of the Shop*/
    public void render (Graphics g){
        g.setColor(Color.white);
        g.setFont(fontLarge);
        g.drawString("SHOP", 300, 150);

        // Item one. All buy rules in MouseInput
        g.setFont(fontSmall);
        g.drawString("Shield", 110, 220);
        g.drawString("Cost: " + mouseInput.itemOne, 110, 240);
        g.drawRect(100, 200, 100, 80);

        // Item two
        g.drawString("Increase Speed", 260, 220);
        g.drawString("Cost: " + mouseInput.itemTwo, 260, 240);
        g.drawString("5x purchase", 260, 260);
        g.drawRect(250, 200, 100, 80);

        // Item three
        g.drawString("Kill them all !", 410, 220);
        g.drawString("Cost: " + mouseInput.itemThree, 410, 240);
        g.drawString("1x purchase", 410, 260);
        g.drawRect(400, 200, 100, 80);

        // Item four
        g.drawString("Cost: " + mouseInput.itemFour, 560, 260);
        g.setFont(fontLarge);
        g.drawString("?",585, 245);
        g.drawRect(550, 200, 100, 80);

        // Available points and return to game tooltip
        g.setFont(fontMedium);
        g.drawString("Points to Spend: " + hud.getScore(), 295, 360);
        g.drawString("Press B to go back", 300, 420);
    }
}
