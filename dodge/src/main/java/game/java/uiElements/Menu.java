package game.java.uiElements;

import game.java.Game;
import game.java.GameState;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {

    private final Game game;
    private final Hud hud;
    private final Font fontLarge = new Font("arial", Font.BOLD, 50);
    private final Font fontMedium = new Font("arial", Font.BOLD, 30);
    private final Font fontSmall = new Font("arial", Font.BOLD, 15);
    private final Font fontTiny = new Font("arial", Font.BOLD, 12);

    /** The constructor for Menu*/
    public Menu(Game game, Hud hud){
        this.game = game;
        this.hud = hud;
    }

    /** Handles the rendering of the Menu*/
    public void render(Graphics g){
        // Text at welcome
        if(game.getGameState() == GameState.WELCOME){
            g.setFont(fontLarge);
            g.setColor(Color.white);
            g.drawString("Menu", 120, 100);

            g.setFont(fontMedium);
            g.drawRect(80, 150, 200, 64);
            g.drawString("Play", 150, 190);

            g.drawRect(80, 250, 200, 64);
            g.drawString("Help", 150, 290);

            g.drawRect(80, 350, 200, 64);
            g.drawString("Quit", 150, 390);
        }
        // Text at info
        else if(game.getGameState() == GameState.INFO){
            g.setFont(fontLarge);
            g.setColor(Color.white);
            g.drawString("Info", 120, 100);

            g.setFont(fontSmall);
            g.drawString("Keybindings", 80, 140);
            g.drawString("WASD to move around and dodge enemies", 80, 160);
            g.drawString("Press P to pause the game and B to enter store", 80, 175);
            g.drawString("Press Escape at any time to close the application", 80, 190);
            g.drawString("Press Space for regular attack, C for powerful attack. Only bosses take damage", 80, 205);
            g.drawString("Ammunition is continually replenished", 80, 220);

            // Creative commons references
            g.drawString("credit to asset creators:", 80, 250);
            g.setFont(fontTiny);
            g.drawString("Sound effects and bgm from creative commons 1.0 & 3.0",80, 263);
            g.drawString("Click sound: https://profiles.google.com/jun66le", 80, 276);
            g.drawString("Coin cling: https://freesound.org/people/BeezleFM/sounds/512133/", 80, 289);
            g.drawString("BGM: https://freesound.org/s/428858/", 80, 302);
            g.drawString("cute slime: https://opengameart.org/content/blob-sprite", 80, 315);
            g.drawString("fireball: https://www.behance.net/gallery/58874769/Fireball-Sprite-sheet", 80,328);
            g.drawString("super attack: https://www.pngitem.com/middle/wobRbx_sprite-fire-animaatio-gamemaker-fire-sprites-png-transparent/", 80, 341);

            g.setFont(fontMedium);
            g.drawRect(80, 350, 200, 64);
            g.drawString("Back", 150, 390);
        }
        // Text at game over
        else if(game.getGameState() == GameState.GAME_OVER){
            g.setFont(fontLarge);
            g.setColor(Color.white);
            g.drawString("Game Over", 80, 100);

            g.setFont(fontMedium);
            g.drawString("You got to level: " + hud.getLevel(), 80, 200);
            g.drawString("With a score of: " + hud.getScore(), 80, 240);

            g.drawRect(80, 350, 200, 64);
            g.drawString("Try Again", 120, 390);
        }
        // Text at difficulty selection
        else if(game.getGameState() == GameState.SELECT_DIFFICULTY){
            g.setFont(fontLarge);
            g.setColor(Color.white);
            g.drawString("Difficulty", 80, 100);

            g.setFont(fontMedium);
            g.drawRect(80, 150, 200, 64);
            g.drawString("Normal", 150, 190);

            g.drawRect(80, 250, 200, 64);
            g.drawString("Hard", 150, 290);

            g.drawRect(80, 350, 200, 64);
            g.drawString("Back", 150, 390);

            g.drawRect(300, 250, 200, 64);
            g.drawString("Testing", 350, 290);
        }
        // Text at pause screen
        else if (game.getGameState() == GameState.PAUSED){
            g.setFont(fontLarge);
            g.setColor(Color.white);
            g.drawString("Paused", 300, 150);
        }
        // Text at victory screen
        else if (game.getGameState() == GameState.VICTORY){
            g.setFont(fontLarge);
            g.setColor(Color.white);
            g.drawString("Victory!", 90, 300);

            g.setFont(fontMedium);
            g.drawRect(80, 350, 200, 64);
            g.drawString("Play again", 110, 390);
        }
    }
}
