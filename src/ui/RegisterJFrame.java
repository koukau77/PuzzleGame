package ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {

    public RegisterJFrame(){
        this.setSize(488,500);
        // set title
        this.setTitle("Puzzle Game");
        // set frame always on top
//        this.setAlwaysOnTop(true);
        // set frame to middle
        this.setLocationRelativeTo(null);
        // set the close model
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
