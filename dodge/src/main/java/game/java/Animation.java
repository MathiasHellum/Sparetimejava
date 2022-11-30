package game.java;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
    private final int speed;
    private final int frames;
    private int index = 0;
    private int count = 0;
    private final BufferedImage[] images;
    private BufferedImage currentImg;

    /** Main constructor for Animation. provide a tick-speed Images & a list of BufferedImages to rotate through.*/
    public Animation(int speed, BufferedImage... args){
        this.speed = speed;
        images = new BufferedImage[args.length];
        for(int i = 0; i < args.length; i ++){
            images[i] = args[i];
        }
        frames = args.length;
    }

    /** The second constructor for Animation. Draws in the order 1,2,3,4,5,4,3,2,1*/
    public Animation(int speed, boolean flip, BufferedImage... args) {
        if (flip) {
            this.speed = speed;
            images = new BufferedImage[(args.length * 2) - 2];
            for (int i = 0; i < args.length; i++) {
                images[i] = args[i];
            }
            BufferedImage[] temp = new BufferedImage[args.length - 2];
            for (int i = args.length - 2; i > 0; i--) {
                temp[args.length - i - 2] = images[i];
            }
            for (int i = args.length; i < images.length; i++) {
                images[i] = temp[i - args.length];
            }
            frames = images.length;
        }else{
            this.speed = speed;
            images = new BufferedImage[args.length];
            for (int i = 0; i < args.length; i++) {
                images[i] = args[i];
            }
            frames = args.length;
        }
    }

    /** Run method*/
    public void runAnimation(){
        index ++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }

    private void nextFrame(){
        for(int i = 0; i < frames; i ++){
            if(count == i)
                currentImg = images[i];
        }

        count ++;

        if(count >= frames)
            count = 0;
    }

    /** Render method. Scale can be whatever, however best results come when it is proportionate to the sprite*/
    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }
}