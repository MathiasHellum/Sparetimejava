package game.java.controls;

import game.java.Game;
import game.java.GameState;
import game.java.Handler;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class KeyInput extends KeyAdapter {

    private final Handler handler;
    private final boolean[] keyDown = new boolean[4];
    private final Game game;

    /**
     * The constructor for KeyInput. Takes handle and game.
     * Handles player movement and general game logic based on users' key presses.
     * @param handler
     * @param game
     */
    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e){
        // Movement in a directions sets a coordinate drift i.e. velocity, in that direction on keypress.
        // Key release stops the velocity, ensuring smooth movement.
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == EntityID.PLAYER){
                // Specifies which velocity to set and a positive or negative value
                if(key == KeyEvent.VK_W){
                    tempObject.setVelocity(-handler.speed);
                    keyDown[0] = true;
                }
                if(key == KeyEvent.VK_S){
                    tempObject.setVelocity(handler.speed);
                    keyDown[1] = true;
                }
                if(key == KeyEvent.VK_D){
                    tempObject.setTrajectory(handler.speed);
                    keyDown[2] = true;
                    handler.setObjectDirection("player",true);
                }
                if(key == KeyEvent.VK_A){
                    tempObject.setTrajectory(-handler.speed);
                    keyDown[3] = true;
                    handler.setObjectDirection("player",false);
                }
                if(key == KeyEvent.VK_SPACE){
                    tempObject.setattacking(true);
                }
                if(key == KeyEvent.VK_C){
                    tempObject.setSuper(true);
                }
            }
        }

        // Pauses/unpauses the game
        if(key == KeyEvent.VK_P){
            if(game.getGameState() == GameState.ACTIVE_GAME){
                game.setGameState(GameState.PAUSED);
            }
            else if(game.getGameState() == GameState.PAUSED){
                game.setGameState(GameState.ACTIVE_GAME);
            }
        }

        // Game exit on escape at any point
        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }

        // Enters/leaves the in-game store
        if(key == KeyEvent.VK_B){
            if(game.getGameState() == GameState.ACTIVE_GAME){
                game.setGameState(GameState.STORE);
            }
            else if(game.getGameState() == GameState.STORE){
                game.setGameState(GameState.ACTIVE_GAME);
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == EntityID.PLAYER){
                if(key == KeyEvent.VK_W){
                    keyDown[0] = false;
                }
                if(key == KeyEvent.VK_S){
                    keyDown[1] = false;
                }
                if(key == KeyEvent.VK_D){
                    keyDown[2] = false;
                }
                if(key == KeyEvent.VK_A){
                    keyDown[3] = false;
                }
                if(key == KeyEvent.VK_SPACE){
                    tempObject.setattacking(false);
                }
                if(key == KeyEvent.VK_C){
                    tempObject.setSuper(false);
                }

                //vertical movement
                if(!keyDown[0] && !keyDown[1]){
                    tempObject.setVelocity(0);
                }
                //horizontal movement
                if(!keyDown[2] && !keyDown[3]){
                    tempObject.setTrajectory(0);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "KeyInput{" +
                "keyDown=" + Arrays.toString(keyDown) +
                '}';
    }
}
