package game.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    /**
     * Tests the clamping function
     */
    @Test
    void clamp() {
        int x =clamp(5, 10 ,15);
        assertEquals(10, x);

        int y = clamp(150, 1, 99);
        assertFalse(y> 100);
        assertEquals(y, 99);
    }

    // Dropper tester for getter & setter for Gamescreens, ettersom de kun omhandler enums,
    // som er svært enkelt å oppdage eventuelle feil ved.
    // start, stop & run trenger åpenbart ikke tester, kjører programmet så kjører det.


    public static int clamp(int parameter, int min, int max){
        if(parameter >= max){
            return parameter = max;
        }
        else if(parameter <= min){
            return  parameter = min;
        }
        else {
            return parameter;
        }
    }

}
