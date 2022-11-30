package game.java.controls;

import game.java.Game;
import game.java.GameState;
import game.java.Handler;
import game.java.assetManagement.AudioPlayer;
import game.java.uiElements.Hud;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private final Game game;
    private final Hud hud;
    private final Handler handler;
    private final AudioPlayer audioPlayer;
    // Source paths here due to ease of access, could be local otherwise
    private final String clickEffect = "src/main/resources/click_effect.wav";
    private final String coinCling = "src/main/resources/coin.wav";

    // prices and rules for in game shop handled here
    public int itemOne = 200;
    public int itemTwo = 400;
    public final int itemThree = 1500;
    public boolean itemThreeBoughtOut = false;
    public final int itemFour = 7000;
    public boolean itemFourBoughtOut = false;

    /**
     * The constructor for MouseInput.
     * Handles general game logic based on users' mouse clicks.<p>
     * Also calls on AudioPlayer for click sounds.
     */
    public MouseInput(Game game, Hud hud, Handler handler){
        this.game = game;
        this.hud = hud;
        this.handler = handler;
        this.audioPlayer =new AudioPlayer();
    }

    public void mouseReleased(MouseEvent e){
        // The coordinates of the mouse cursor
        int mouseX = e.getX();
        int mouseY = e.getY();

        if(game.getGameState() == GameState.WELCOME){
            // Click on play button. Enters screen for choosing difficulty.
            if(mouseOver(mouseX, mouseY, 80, 150, 200, 64)){
                audioPlayer.playClickSound(clickEffect);
                game.setGameState(GameState.SELECT_DIFFICULTY);
                return;
            }
            // Click on info button
            if(mouseOver(mouseX, mouseY, 80, 250, 200, 64)){
                audioPlayer.playClickSound(clickEffect);
                game.setGameState(GameState.INFO);
            }
            // Click on quit button
            if(mouseOver(mouseX, mouseY, 80, 350, 200, 64)){
                System.exit(1);
            }
        }

        if(game.getGameState() == GameState.SELECT_DIFFICULTY){
            // Choose normal difficulty
            if(mouseOver(mouseX, mouseY, 80, 150, 200, 64)){
                audioPlayer.playClickSound(clickEffect);
                game.setGameState(GameState.ACTIVE_GAME);
                handler.setGameDifficulty(0);
            }
            // Choose hard difficulty
            if(mouseOver(mouseX, mouseY, 80, 250, 200, 64)){
                audioPlayer.playClickSound(clickEffect);
                game.setGameState(GameState.ACTIVE_GAME);
                handler.setGameDifficulty(1);
            }
            // Choose testing difficulty
            if(mouseOver(mouseX, mouseY, 300, 250, 200, 64)){
                audioPlayer.playClickSound(clickEffect);
                game.setGameState((GameState.ACTIVE_GAME));
                handler.setGameDifficulty(2);
            }
            // Return to main menu from difficulty selection screen
            if(mouseOver(mouseX, mouseY, 80, 350, 200, 64)){
                game.setGameState(GameState.WELCOME);
                audioPlayer.playClickSound(clickEffect);
                return;
            }
        }
        // Return to main menu from info screen
        if(game.getGameState() == GameState.INFO){
            if(mouseOver(mouseX, mouseY, 80, 350, 200, 64)){
                audioPlayer.playClickSound(clickEffect);
                game.setGameState(GameState.WELCOME);
            }
        }
        // Try again after losing or winning. Resets all previously adjusted values
        if(game.getGameState() == GameState.GAME_OVER || game.getGameState() == GameState.VICTORY){
            if(mouseOver(mouseX, mouseY, 80, 350, 200, 64)){
                audioPlayer.playClickSound(clickEffect);
                game.setGameState(GameState.WELCOME);
                hud.setLevel(1);
                hud.setScore(0);
                // Reset purchases
                itemOne = 200;
                itemTwo = 400;
                handler.speed = 5;
                itemThreeBoughtOut = false;
                itemFourBoughtOut = false;
            }
        }
        // In store
        if(game.getGameState() == GameState.STORE){
            // Item one. Price += x per purchase
            if(mouseX >= 100 && mouseX <= 200){
                if(mouseY >= 200 && mouseY <= 280){
                    // Only allows buying shield meter if not already at cap
                    if(hud.getScore() >= itemOne && hud.shield < 100){
                        audioPlayer.playClickSound(coinCling);
                        hud.setScore(hud.getScore() - itemOne);
                        itemOne += 200;
                        hud.shield += 50;
                    }
                }
            }
            // Item two. Price += x per purchase
            if(mouseX >= 250 && mouseX <= 350){
                if(mouseY >= 200 && mouseY <= 280){
                    // Caps buying speed upgrade at 5 purchases
                    if(hud.getScore() >= itemTwo && handler.speed < 10){
                        audioPlayer.playClickSound(coinCling);
                        hud.setScore(hud.getScore() - itemTwo);
                        itemTwo += 400;
                        handler.speed++;
                    }
                }
            }
            // Item three. One time buy
            if(mouseX >= 400 && mouseX <= 500){
                if(mouseY >= 200 && mouseY <= 280){
                    if(hud.getScore() >= itemThree && itemThreeBoughtOut == false){
                        audioPlayer.playClickSound(coinCling);
                        // Kill them all
                        hud.setScore(hud.getScore() - itemThree);
                        itemThreeBoughtOut = true;
                        handler.clearGrunts();
                    }
                }
            }
            // Item four. Wins the game
            if(mouseX >= 550 && mouseX <= 650){
                if(mouseY >= 200 && mouseY <= 280){
                    if(hud.getScore() >= itemFour){
                        audioPlayer.playClickSound(coinCling);
                        itemFourBoughtOut = true;
                    }
                }
            }
        }
    }

    /**
     * Checks whether the coordinates of the mouse cursor is within the bounds of the element.
     * @param mouseX cursor x coordinate
     * @param mouseY cursor y coordinate
     * @param x field x coordinate
     * @param y field y coordinate
     * @param width field width
     * @param height field height
     * @return true if cursor is within specified location
     */
    private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height){
        if(mouseX > x && mouseX < x + width){
            if(mouseY > y && mouseY < y + height){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }
}
