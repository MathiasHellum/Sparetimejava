package game.java;

import game.java.entities.EntityID;
import game.java.entities.GameObject;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Objects;

public class Handler {
    public ArrayList<GameObject> object = new ArrayList<>();
    // The initial movement speed/velocity of the player
    public int speed = 5;
    private final boolean[] facingRight = new boolean[3];
    private int numberOfBossesAlive;
    private int gameDifficulty; // normal = 0, hard = 1, testing = 2

    public ArrayList<GameObject> playerProjectiles = new ArrayList<>();

    /** Difficulty getter*/
    public int getGameDifficulty(){
        return gameDifficulty;
    }

    /** Difficulty setter*/
    public void setGameDifficulty(int difficulty){
        gameDifficulty = difficulty;
    }

    /**
     * The tick method for the Handler.
     * Ticks a copy of the ArrayList of GameObjects.
     */
    public void tick(){ //swapped to enhanced for loop here, can do same for below if this works.
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
        for (int i = 0; i < playerProjectiles.size(); i++) {
            GameObject tempProjectile = playerProjectiles.get(i);
            tempProjectile.tick();
        }
    }

    /** Right = true<P> Left = false*/
    public boolean getObjectDirection(String string){
        if(Objects.equals(string, "player")){
            return this.facingRight[0];
        }
        if(Objects.equals(string, "boss")){
            return this.facingRight[1];
        }
        return (false);
    }


    /** Right = true<P> Left = false*/
    public void setObjectDirection(String name, boolean direction){
        if(Objects.equals(name, "player")){
            this.facingRight[0] = direction;
        }
        if (Objects.equals(name, "boss")){
            this.facingRight[1] = direction;
        }
    }

    public int getNumberOfBossesAlive(){
        return this.numberOfBossesAlive;
    }

    public void incrementNumberOfBossesAlive(int number){
        this.numberOfBossesAlive += number;
    }

    /**
     * The render method for the Handler.
     * Renders a copy of the ArrayList of GameObjects.
     * @param g
     */
    public void render(Graphics g){
        for (GameObject tempObject : object) {
            tempObject.render(g);
        }
        for (GameObject tempProjectile : playerProjectiles
             ) {
            tempProjectile.render(g);
        }
    }

    public void addProjectile (GameObject object) {
        this.playerProjectiles.add(object);
    }

    public void removeProjectile (GameObject object) {
        this.playerProjectiles.remove(object);
    }

    /**
     * Adds a GameObject to the initial ArrayList of GameObjects
     * @param object the object to be added
     */
    public void addObject(GameObject object){
        this.object.add(object);
    }

    /**
     * Removes a GameObject from the initial ArrayList of GameObjects
     * @param object the object to be removed
     */
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    /**
     * Clears all entities except the player
     * <p>
     * An ArrayList of type GameObject tracking all objects, removes anything that does not have a player ID
     */
    public void clearGrunts() {
        ArrayList<GameObject> tempList = new ArrayList<>(object);
        for (GameObject tempObject : tempList) {
            if (tempObject.getId() != EntityID.PLAYER) {
                removeObject(tempObject);
            }
        }
    }

    /** yeet and delete*/
    public void bossDies() {
        ArrayList<GameObject> tempUnitList = new ArrayList<>(object);
        for (GameObject tempObject: tempUnitList
             ) {
            if (tempObject.getId() == EntityID.BOSS) {
                removeObject(tempObject);
            }
        }
    }
}