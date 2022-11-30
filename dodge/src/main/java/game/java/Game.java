package game.java;

import game.java.assetManagement.AudioPlayer;
import game.java.assetManagement.BufferedImageLoader;
import game.java.controls.KeyInput;
import game.java.controls.MouseInput;
import game.java.entities.EntityID;
import game.java.entities.objects.MenuParticle;
import game.java.uiElements.Hud;
import game.java.uiElements.Menu;
import game.java.uiElements.Store;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1280, HEIGHT = 720;
    private Thread thread;
    private boolean running = false;
    private final Random r;
    private final Handler handler;
    private final Hud hud;
    private final RoundManager spawner;
    private final Menu menu;
    private final Store store;
    private GameState gameState;
    private final MouseInput mouseInput;
    // lazy, didn't reallocate the last sprite-sheet:
    public static BufferedImage spriteSheet;

    public Game(){
        BufferedImageLoader minionLoader = new BufferedImageLoader();
        spriteSheet = minionLoader.loadImage("/sprite-sheet.png");
        handler = new Handler();
        hud = new Hud();
        MouseInput mouseInput = new MouseInput(this, hud, handler);
        store = new Store(hud, mouseInput);
        menu = new Menu(this, hud);
        this.addMouseListener(store);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(mouseInput);
        AudioPlayer audioPlayer = new AudioPlayer();
    //    audioPlayer.playMusic("src/main/resources/bgm.wav");
        spawner = new RoundManager(handler, hud, this);
        r = new Random();
        this.gameState = GameState.WELCOME;
        // Menu particle effects at game launch
        for (int i = 0; i < 30; i++) {
            handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), EntityID.MENU_PARTICLE, handler));
        }
        this.mouseInput = mouseInput;
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /** Drift adjusting & locked at 60fps */
    public void run(){
        this.requestFocus();
        /* The use of delta related code prevents potential frame drifting.
        Calling render & tick every 1000/60ms to achieve 60 fps.
        If frames drop to 58 fps, delta will be 2 instead of 1 at 60 fps, thus calling tick twice to compensate.
        Ideally delta would always be 1, this is not always the case with threads, resulting in drifting.
        If the thread and the ticks are not synced, there won't be any loss of ticks as it adjusts delta and ticks to match.*/
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (running) {
            // Puts the thread to sleep between ticks to avoid unnecessary load on CPU
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long now = System.nanoTime();
            delta+= (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    /** The tick method for the game itself. Calls on relevant tick methods in different GameScreens.*/
    private void tick(){
        if(gameState == GameState.ACTIVE_GAME){
            hud.tick();
            spawner.tick();
            handler.tick();
            // What happens when the player dies
            if(hud.health <= 0){
                setGameState(GameState.GAME_OVER);
                hud.health = 100;
                spawner.ROUND_1 = !spawner.ROUND_1;
                handler.object.clear();
                // The same particle effects added at game over as in the menu
                for (int i = 0; i < 20; i++) {
                    handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), EntityID.MENU_PARTICLE, handler));
                }
            }
            if(mouseInput.itemFourBoughtOut){
                setGameState(GameState.VICTORY);
                hud.health = 100;
                hud.shield = 0;
                spawner.ROUND_1 = !spawner.ROUND_1;
                handler.object.clear();
                for (int i = 0; i < 20; i++) {
                    handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), EntityID.MENU_PARTICLE, handler));
                }
            }
        }
        else if(gameState == GameState.WELCOME || gameState == GameState.INFO || gameState == GameState.GAME_OVER || gameState == GameState.SELECT_DIFFICULTY || gameState == GameState.VICTORY){
            handler.tick();
        }
    }

    /**
     * The render method for the Game.
     * Calls on relevant render methods in different GameScreens.<p>
     * Utilizes BufferStrategy to have a few buffers of what is to be rendered
     */
    private void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        // If there is no BufferStrategy, creates an image/buffer off-screen that can be called later
        if(bufferStrategy == null){
            this.createBufferStrategy(3); // Req more than 1, few needed.
            return;
        }

        // Draws the whole background by using a native method to BufferStrategy
        Graphics g = bufferStrategy.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // What to render in different gamestates
        if(gameState == GameState.ACTIVE_GAME){
            handler.render(g);
            hud.render(g);
        }
        else if(gameState == GameState.PAUSED){
            handler.render(g);
            hud.render(g);
            menu.render(g);
        }
        else if(gameState == GameState.STORE){
            store.render(g);
        }
        // If in menu or at game over
        else if(gameState == GameState.WELCOME || gameState == GameState.INFO || gameState == GameState.GAME_OVER || gameState == GameState.SELECT_DIFFICULTY || gameState == GameState.VICTORY){
            handler.render(g);
            menu.render(g);
        }

        // Disposes the graphic of the previous render
        g.dispose();
        // Shows/renders the next buffer
        bufferStrategy.show();
    }

    /** Sets the game state.*/
    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    /** Gets the current game state.*/
    public GameState getGameState(){
        return gameState;
    }
}
