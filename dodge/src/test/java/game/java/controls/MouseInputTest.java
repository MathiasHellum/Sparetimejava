package game.java.controls;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MouseInputTest {

    /**
     * Tester for visuelle ting slik som i menyen eller kjøp i in-game store er enklest testet ved å
     * kjøre applikasjonen og visuelt testse. Derfor er det ikke tester for slikt.
     */

    @Test
    void mouseOver() {
        // Within coordinate range for both x and y
        assertTrue(mouseOver(10, 10,5,5,20,20));

        // Within coordinate range for y but not x, thus outside the box
        assertFalse(mouseOver(126, 326,200,200, 150, 150));

        // Within coordinate range for x but not y, thus outside the box
        assertFalse(mouseOver(268, 211, 200, 300, 100, 100));

        // Outside coordinate range for both x and y
        assertFalse(mouseOver(654, 472, 300, 300, 100, 100));
    }






    private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height){
        if(mouseX > x && mouseX < x + width){
            if(mouseY > y && mouseY < y + height){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }
}