package game.java.entities.objects;

import game.java.Handler;
import game.java.entities.EntityID;
import game.java.entities.GameObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

public class PlayerTest {
    ArrayList<GameObject> object = new ArrayList<>();
    Handler handler;
    GameObject gameObject;

    // Slet med å få test av denne til å fungere

//    @Test
//    void collisionCheck() {
//        object.add(new Player(1, 1, EntityID.PLAYER, null, null, null));
//        object.add(new BasicEnemy(1, 1, EntityID.BASIC_ENEMY, null));
//
//        for (int i = 0; i < handler.object.size(); i++) {
//            GameObject tempObject = handler.object.get(i);
//
//            // Colliding with all grunts except HardEnemy. Steadily drains player health
//            if (tempObject.getId() == EntityID.BASIC_ENEMY || tempObject.getId() == EntityID.FAST_ENEMY || tempObject.getId() == EntityID.SMART_ENEMY || tempObject.getId() == EntityID.BOSS_PROJECTILE) {
//                if (gameObject.getBounds().intersects(tempObject.getBounds())) {
//                    // What happens:
//                    assertTrue(true);
//                }
//            }
//        }
//
//    }
}
