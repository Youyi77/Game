package game.Main;

import javax.swing.JFrame;

/**
 *
 * @author trifissy
 */
public class Main {

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Games and AI");
        frame.add(new GamePanel());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

}
