package game.java;

import java.awt.Color;

public class HelperFunctions {


    /** HSB color gamut from green to red.<P> Set power to be between 0.0 & 1.0, reflecting 0-100%*/
    public static Color getColor(double power){
        double H = power * 0.38; // Hue note 0.4 = Green
        double S = 0.9; // Saturation
        double B = 0.8; // Brightness
        return Color.getHSBColor((float)H, (float)S, (float)B);
    }

    /**
     * Clamping function for ints.<P>
     * If the given parameter is below minimum, set to minimum.
     * If the given parameter is above maximum, set to maximum.
     * @param parameter the given parameter
     * @param min lower threshold
     * @param max upper threshold
     * @return the given parameter or the threshold met
     */
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

//    /** Cosmetic function used in giving the boss a random appearance
//     * @return 1 or 4 */
//    public int randomBoss(){
//        int rng;
//        int temp = r.nextInt(2);
//        if(temp == 1){
//            rng = 1;
//        }
//        else {
//            rng = 4;// due to sub-image getter.
//        }
//        return rng;
//    }
}
