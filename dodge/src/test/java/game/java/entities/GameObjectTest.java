package game.java.entities;

import game.java.entities.objects.BasicEnemy;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameObjectTest {
    GameObject gameObject;
    ArrayList<GameObject> object = new ArrayList<>();

    /**
     * Checks that the getter for GameObjects work
     */
    @Test
    void getId() {
        object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));
        gameObject = object.get(0);
        assertEquals(EntityID.BASIC_ENEMY, gameObject.getId());
    }

    /**
     * Checks that the setter for GameObjects work
     */
    @Test
    void setId() {
        object.add(new BasicEnemy(EntityID.BASIC_ENEMY, null));
        gameObject = object.get(0);
        gameObject.setId(EntityID.HARD_ENEMY);
        assertEquals(EntityID.HARD_ENEMY, gameObject.getId());
    }
}
