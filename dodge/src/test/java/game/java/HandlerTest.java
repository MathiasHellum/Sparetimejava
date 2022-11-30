package game.java;

import game.java.entities.EntityID;
import game.java.entities.GameObject;
import game.java.entities.objects.BasicEnemy;
import game.java.entities.objects.Player;
import game.java.entities.objects.SmartEnemy;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HandlerTest {

    ArrayList<GameObject> object = new ArrayList<>();

    /**
     * Tests different combinations and amounts of entities to make sure all enemy entities
     * always are removed and player entities never are. Tests based on entity IDs.
     * This is intentional as players won't be assigned enemy IDs, but enemies could be assigned IDs of other
     * enemies if desired, changing some dynamics of how the enemy functions.
     * Examples would be same behavior but different effect when in contact with player Ids.
     */
    @Test
    void clearGrunts(){

        /*
        Testing different combinations of entities.
         */
        object.add(new Player(EntityID.PLAYER, null, null, null));
        object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));
        object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));
        object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));

        clearGruntsCopy();
        assertEquals(1,object.size());
        object.clear();

        object.add((new Player(EntityID.PLAYER, null, null, null)));
        object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));
        object.add(new SmartEnemy(EntityID.SMART_ENEMY, null));

        clearGruntsCopy();
        // 1x player left
        assertEquals(1, object.size());
        object.clear();

        object.add((new Player(EntityID.PLAYER, null, null, null)));
        clearGruntsCopy();
        // only entity spawned is 1x player
        assertEquals(1, object.size());

        object.add(new SmartEnemy(EntityID.SMART_ENEMY, null));
        object.add(new SmartEnemy(EntityID.SMART_ENEMY, null));
        object.add(new SmartEnemy(EntityID.SMART_ENEMY, null));
        object.add((new Player(EntityID.PLAYER, null, null, null)));
        object.add((new Player(EntityID.PLAYER, null, null, null)));
        clearGruntsCopy();
        // 2x player spawned, but one remain from the last test
        assertEquals(3, object.size());
    }

    /**
     * Functions as clearGrunts. Uses the remove function directly.
     */
    void clearGruntsCopy(){
        ArrayList<GameObject> tempList = new ArrayList<>(object);
        for (GameObject tempObject : tempList) {
            if (tempObject.getId() != EntityID.PLAYER) {
                this.object.remove(tempObject);
            }
        }
    }

    // Unsure of test relevancy, but works like the actual addObject method
    @Test
    void addObject() {
        ArrayList<GameObject> tempList = new ArrayList<>(object);
        tempList.add(new SmartEnemy(EntityID.SMART_ENEMY, null));
        assertFalse(tempList.isEmpty());
    }
}
