package game.java.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x, y;
    protected int trajectory, velocity;
    protected EntityID entityId;
    protected boolean attacking, powerful_attack;

    /** The constructor for the abstract class GameObject*/
    public GameObject(int x, int y, EntityID entityId){
        this.x = x;
        this.y = y;
        this.entityId = entityId;
    }

    /** Overload for testing only*/
    public GameObject(EntityID entityId){
        this.entityId = entityId;
    }

    /**
     * Controls the movement of the entity.
     * Also adds possible functions of entities.
     */
    public abstract void tick();

    /**
     * Handles the rendering of the entity
     * @param g
     */
    public abstract void render(Graphics g);

    /**
     * Handles the boundaries of the entity
     * @return Coordinates and shape/size of type Rectangle
     */
    public abstract Rectangle getBounds();

    /**
     * Gets the x value
     * @return the x value
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the y value
     * @return the y value
     */
    public int getY(){
        return y;
    }

    /**
     * Sets the x value
     * @param x
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Sets the y value
     * @param y
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Gets the EntityID
     * @return the EntityID
     */
    public EntityID getId(){
        return entityId;
    }

    /**
     * Sets the EntityID
     * @param entityId
     */
    public void setId(EntityID entityId){
        // Use case: option to change entity id of an object to change properties in-session.
        this.entityId = entityId;
    }

    /**
     * Gets the velocity of x
     * @return velocity of x
     */
    public int getTrajectory(){
        // Samme forsvar som for setId over
        return trajectory;
    }

    /**
     * Gets the velocity of y
     * @return velocity of y
     */
    public int getVelocity(){
        // samme forsvar som for setId over
        return velocity;
    }

    /**
     * Sets the velocity of x
     * @param trajectory
     */
    public void setTrajectory(int trajectory){
        this.trajectory = trajectory;
    }

    /**
     * Sets the velocity of y
     * @param velocity
     */
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }

    public Boolean getattacking(){
        return attacking;
    }

    public void setattacking(boolean attacking){
        this.attacking = attacking;
    }

    public void setSuper(boolean attacking){
        this.powerful_attack = attacking;
    }
}
