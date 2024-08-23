package main;

import javax.swing.*;
import java.awt.*;

/**
 * @author _edd.ie_
 */
public class Game extends JPanel  implements Runnable{
    // Screen settings
    private static final int tile = 12;
    private static final int scale = 3;
    public static final int tileSize = tile * scale;
    public static int[] aspectRatio = {32, 18};
    public static int[] dimensions = {tileSize * aspectRatio[0], tileSize * aspectRatio[1]};


    // Game runtime
    public static Thread gameThread;
    public static final int FPS = 60;

    //Event listener
    public EventListener event = new EventListener();


    public Game(){
        this.setPreferredSize(new Dimension(dimensions[0], dimensions[1]));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(event);
        this.setFocusable(true); // Panel can focus on key presses
    }

    /**
     * Starts the game.
     * Creates a new thread and starts the game loop.
     * @see Thread#start()
     */
    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Runs this operation.
     * Starts a thread that runs parallel to the main thread.
     * The game loop is implemented in this method.
     * @see Thread#run()
     */
    @Override
    public void run() {
        double intervals = (double) 1000000000 /FPS, countdown=0;
        long lastUpdateTime = System.nanoTime(), timer = 0, currentTime,diff;
        int fpsCounter = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            diff = (currentTime - lastUpdateTime);
            timer += diff;
            countdown +=  (diff/intervals);
            lastUpdateTime = currentTime;

            if (countdown >= 1) { // Update game at 60 FPS
                update();
                repaint();
                countdown = 0;
                fpsCounter++;
            }

            if(timer >= 1000000000) { // Display fps
                System.out.println("FPS: " + fpsCounter);
                fpsCounter = 0;
                timer  = 0;
            }
        }
    }

    /**
     * Updates the game state.
     */
    public void update() {
    }

    /**
     * Paints the game on the screen.
     * @see JPanel#paintComponent(Graphics)
     * @param fx the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics fx) {
        super.paintComponent(fx);
        // Using graphics2D library to draw on the screen
        Graphics2D gfx = (Graphics2D) fx;

        //Example background delete for your game
        gfx.setColor(Color.black);
        gfx.fillRect(0,0, dimensions[0], dimensions[1]);
        gfx.setColor(Color.white);
        gfx.fillRect(tileSize,tileSize, tileSize, tileSize);

        gfx.setColor(Color.red);
        gfx.drawString("1 pixel size", tile+(2*tileSize), tileSize+(tileSize/2));

        // Draw background first, then characters on top of it


        // Disposes of this graphics context and releases any system resources that it is using
        gfx.dispose();
    }
}
