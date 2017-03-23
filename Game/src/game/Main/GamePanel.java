package game.Main;

import game.State.StateManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author trifissy
 */
public class GamePanel extends JPanel implements Runnable, KeyListener, ActionListener {

    // Dimensions
    public static final int WIDTH = 500;
    public static final int HEIGHT = 350;
    public static final int SCALE = 2;

    // game thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private BufferedImage image;
    private Graphics2D g;
    private StateManager stateManager;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        //addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;
        stateManager = new StateManager();
    }

    @Override
    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        // game loop
        while (running) {

            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;
            if (wait < 0) {
                wait = 5;
            }

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
                
            }

        }

    }

    private void update() {
        stateManager.update();
    }

    private void draw() {
        stateManager.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g2.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        stateManager.keyTyped(ke.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_SPACE)
        System.out.println(ke.getKeyCode());
        stateManager.keyPressed(ke.getKeyCode());
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        stateManager.keyReleased(ke.getKeyCode());
    }

}
