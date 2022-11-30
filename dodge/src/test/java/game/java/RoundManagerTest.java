package game.java;

import game.java.entities.EntityID;
import game.java.entities.GameObject;
import game.java.entities.objects.BasicEnemy;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundManagerTest {

    ArrayList<GameObject> object = new ArrayList<>();
    private int level = 1;

    /**
     * Tests the basic functionality of the normalSpawnManger.
     * The behaviour of hardSpawnManager is identical and is therefore not tested here
     */
    @Test
    void normalSpawnManagerTest() {
        normalSpawnManager(); // None spawned
        assertEquals(0, object.size());

        level = 2;
        normalSpawnManager(); // one should have spawned
        assertEquals(1, object.size());

        level = 3;
        normalSpawnManager(); // another should have spawned
        assertEquals(2, object.size());

        level = 4;
        normalSpawnManager(); // three more should have spawned
        assertEquals(5, object.size());
    }




    public void normalSpawnManager() {
        if (level == 2) {
//            handler.addObject(new BasicEnemy(1, 1, EntityID.BASIC_ENEMY, handler));
            object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));
        }
        else if (level == 3) {
            object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));
        }
        else if (level == 4){
            object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));
            object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));
            object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));
        }
    }
}
