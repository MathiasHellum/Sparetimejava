package game.java;

import game.java.entities.EntityID;
import game.java.entities.objects.*;
import game.java.uiElements.Hud;
import java.util.Random;

public class RoundManager {

    private final Handler handler;
    private final Hud hud;
    private final Game game;
    private final Random r = new Random();
    private int intraRoundScore;
    public boolean ROUND_1 = true;

    /** The constructor for RoundManager*/
    public RoundManager(Handler handler, Hud hud, Game game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    /** The tick method for RoundManager.<P> Handles what spawns when for each round.*/
    public void tick(){
        intraRoundScore++;

        // Initial spawn at round 1, depending on game difficulty
        if(ROUND_1 && handler.getGameDifficulty() == 0){
            handler.object.clear(); // for removing menu particle effect
            handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, EntityID.PLAYER, handler, hud));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
            ROUND_1 = false;
        }
        else if (ROUND_1 && handler.getGameDifficulty() == 1){
            handler.object.clear(); // for removing menu particle effect
            handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, EntityID.PLAYER, handler, hud));
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50), EntityID.HARD_ENEMY, handler));
            ROUND_1 = false;
        }
        else if (ROUND_1 && handler.getGameDifficulty() == 2){
            handler.object.clear();
            handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, EntityID.PLAYER, handler, hud));
            ROUND_1 = false;
        }
        // For all consecutive rounds, higher number = longer rounds
        if(intraRoundScore >= 150){
            intraRoundScore = 0;
            hud.setLevel(hud.getLevel() + 1);

            // Spawning schedule of normal difficulty
            if(handler.getGameDifficulty() == 0){
                normalSpawnManager();
            }

            // Spawning schedule of hard difficulty
            else if (handler.getGameDifficulty() == 1){
                hardSpawnManager();
            }
            // Spawning schedule of test difficulty
            else if (handler.getGameDifficulty() == 2){
                testSpawnManager();
                hud.health =100;
            }
        }
    }

    /** The spawning schedule for normal difficulty*/
    public void normalSpawnManager(){
        if(hud.getLevel() == 2){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 3){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 4){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 5){
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.SMART_ENEMY, handler));
        }
        else if(hud.getLevel() == 6){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 7){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 8){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 10){
            handler.clearGrunts();
            handler.addObject(new Boss((Game.WIDTH / 2) - 48, -120, EntityID.BOSS, handler, hud));
            handler.incrementNumberOfBossesAlive(1);
        }
        else if(hud.getLevel() == 12){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 14){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 16){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 18){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 20){
            handler.clearGrunts();
            handler.addObject(new Boss((Game.WIDTH / 9) - 48, -120, EntityID.BOSS, handler, hud));
            handler.incrementNumberOfBossesAlive(1);
            handler.addObject(new Boss((Game.WIDTH / 2) - 48, -120, EntityID.BOSS, handler, hud));
            handler.incrementNumberOfBossesAlive(1);
        }
        else if(hud.getLevel() == 22){
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.SMART_ENEMY, handler));
        }
        else if(hud.getLevel() == 24){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 26){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 28){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 30){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 32){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 34){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 36){
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.SMART_ENEMY, handler));
        }
        else if(hud.getLevel() == 38){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 40){
            handler.addObject(new Boss((Game.WIDTH / 2) - 48, -120, EntityID.BOSS, handler, hud));
            handler.incrementNumberOfBossesAlive(1);

        }
    }

    /** The spawning schedule for hard difficulty*/
    public void hardSpawnManager(){
        if(hud.getLevel() == 2){
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.HARD_ENEMY, handler));
        }
        else if(hud.getLevel() == 3){
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.HARD_ENEMY, handler));
        }
        else if(hud.getLevel() == 4){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 5){
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.SMART_ENEMY, handler));
        }
        else if(hud.getLevel() == 6){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 7){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 10){
            handler.clearGrunts();
            handler.addObject(new Boss((Game.WIDTH / 2) - 48, -120, EntityID.BOSS, handler, hud));
            handler.incrementNumberOfBossesAlive(1);

        }
        else if(hud.getLevel() == 12){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 14){
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.HARD_ENEMY, handler));
        }
        else if(hud.getLevel() == 16){
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.SMART_ENEMY, handler));
        }
        else if(hud.getLevel() == 18){
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.HARD_ENEMY, handler));
        }
        else if(hud.getLevel() == 20){
            handler.clearGrunts();
            handler.addObject(new Boss((Game.WIDTH / 2) - 48, -120, EntityID.BOSS, handler, hud));
            handler.incrementNumberOfBossesAlive(1);

            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.SMART_ENEMY, handler));
        }
        else if(hud.getLevel() == 22){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 24){
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.HARD_ENEMY, handler));
        }
        else if(hud.getLevel() == 26){
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.HARD_ENEMY, handler));
        }
        else if(hud.getLevel() == 28){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
        }
        else if(hud.getLevel() == 30){
            handler.addObject(new Boss((Game.WIDTH / 2) - 48, -120, EntityID.BOSS, handler, hud));
            handler.incrementNumberOfBossesAlive(1);

        }
        else if(hud.getLevel() == 32){
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.HARD_ENEMY, handler));
        }
        else if(hud.getLevel() == 34){
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.SMART_ENEMY, handler));
        }
        else if(hud.getLevel() == 36){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.BASIC_ENEMY, handler));
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.FAST_ENEMY, handler));
        }
        else if(hud.getLevel() == 38){
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.HARD_ENEMY, handler));
        }
        else if(hud.getLevel() == 40){
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), EntityID.HARD_ENEMY, handler));
        }
    }

    /** test environment manager*/
    public void testSpawnManager(){
        if(hud.getLevel() == 2){
            handler.addObject(new Boss((Game.WIDTH / 2) - 48, -120, EntityID.BOSS, handler, hud));
            handler.incrementNumberOfBossesAlive(1);
        }
        else if (hud.getLevel() == 3){
            handler.addObject(new Boss((Game.WIDTH / 2) - 48, -120, EntityID.BOSS, handler, hud));
            handler.incrementNumberOfBossesAlive(1);
        }
    }
}
