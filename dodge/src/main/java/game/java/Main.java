package game.java;

import javax.swing.JFrame;
import java.awt.Dimension;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Dodge");
        frame.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        frame.setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        frame.setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        frame.pack();
        game.start();
    }
}
