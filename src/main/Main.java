package main;

import javax.swing.*;

/**
 * @author _edd.ie_
 */
public class Main extends JFrame{
    //Application window
    public static Game window = new Game();

    public Main(){
        this.setTitle("2D Java Template");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(window);
        this.pack();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        window.start();
    }


    public static void main(String[] args) {
        new Main();
    }
}
