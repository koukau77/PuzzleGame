package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    // data is used to save random label of images
    int[][] data = new int[4][4];
    // correct data
    int[][] winData = new int[][]{
        {0, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16},
    };
    // location of image_1
    int x, y;
    // image path
    String path = "imgs\\hotori\\";
    // count your step before you win
    int step;

    JMenuItem replayItem = new JMenuItem("Restart game");
    JMenuItem reLoginItem = new JMenuItem("Relogin game");
    JMenuItem closeItem = new JMenuItem("Close game");
    JMenuItem emailItem = new JMenuItem("Email");
    JMenuItem ikuyo = new JMenuItem("ikuyo");
    JMenuItem nijika = new JMenuItem("nijika");
    JMenuItem ryo = new JMenuItem("ryo");
    JMenuItem hotori = new JMenuItem("hotori");


    public GameJFrame(){
        // initial frame
        initJFrame();
        // initial menuBar
        initJMenuBar();
        // initial data
        initData();
        // initial images
        initImage();

        this.setVisible(true);
    }

    private void initData(){
        int[] arr = {0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0){
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = arr[i];
        }
    }
    // initial images
    private void initImage() {
        // fresh image
        this.getContentPane().removeAll();

        if (victory()){
            JLabel winImg = new JLabel(new ImageIcon("imgs\\win.jpg"));
            winImg.setBounds(45, 50, 595, 680);
            this.getContentPane().add(winImg);
        }

        JLabel stepCount = new JLabel("Steps: " + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        // create one ImageIcon object
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int label = data[i][j];
                JLabel jLabel = new JLabel(new ImageIcon(path + label + ".JPG"));
                jLabel.setBounds(125 * j + 45, 182 * i + 50, 125, 182);
                // add border between the images
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }
        }

        // add background
        JLabel background = new JLabel(new ImageIcon("imgs/background.jpg"));
        background.setBounds(0, -40, 600, 877);
        this.getContentPane().add(background);

        // fresh ContentPane
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        // initial menu
        JMenuBar jMenuBar = new JMenuBar();
        // create two selection on first menu
        JMenu functionJMenu = new JMenu("Function");
        JMenu aboutJMenu = new JMenu("About us");

        JMenu changeImgItem = new JMenu("Change image");


        // add function item to function menu\
        functionJMenu.add(changeImgItem);
        functionJMenu.add(replayItem);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        // add iamges selection to changeImgItem
        changeImgItem.add(ikuyo);
        changeImgItem.add(nijika);
        changeImgItem.add(ryo);
        changeImgItem.add(hotori);
        // add accountItem to function menu
        aboutJMenu.add(emailItem);
        // add function menu to initial menu
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // add action to items
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        emailItem.addActionListener(this);
        ikuyo.addActionListener(this);
        hotori.addActionListener(this);
        ryo.addActionListener(this);
        nijika.addActionListener(this);

        // set menu for GameJFrame
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        // set frame's width and height
        this.setSize(600,900);
        // set title
        this.setTitle("Puzzle Game");
        // set frame always on top
        this.setAlwaysOnTop(true);
        // set frame to middle
        this.setLocationRelativeTo(null);
        // set the close model
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // cancel default middle loction
        this.setLayout(null);
        // add keyboard listener
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // check original image when always press A
        int code = e.getKeyCode();
        if (code == 65){
            this.getContentPane().removeAll();
            JLabel completeImg = new JLabel(new ImageIcon(path + "original.JPG"));
            completeImg.setBounds(45, 50, 500, 731);
            this.getContentPane().add(completeImg);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (victory()){
            return;
        }

        // left:37 up:38 right:39 down:40

        // x,y location of image_1
        // x+1, y location of the image which under the image1
        int code = e.getKeyCode();
        // left
        if (code == 37){
            if (y == 3){
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initImage();
            // up
        } else if (code == 38) {
            if (x == 3){
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initImage();
            // right
        } else if (code == 39) {
            if (y == 0){
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
            // down
        } else if (code == 40) {
            if (x == 0){
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
            // press out A, back to original img
        } else if (code == 65) {
            initImage();
            // press out W, pass game
        } else if (code == 87) {
            data = new int[][]{
                    {0, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16},
            };
            initImage();
        }

    }

    // determine if data is same as winData
    // if yes,return true, if no return false
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++){
                if (data[i][j] != winData[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object  obj = e.getSource();
        if (obj == replayItem){
            step = 0;
            initData();
            initImage();
        } else if (obj == reLoginItem) {
            // close gamejframe
            this.setVisible(false);
            // return login frame
            new LoginJFrame();
        } else if (obj == closeItem) {
            // close virtual system
            System.exit(0);
        } else if (obj == emailItem) {
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel("koukau77@gmail.com");
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(200, 110);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }else if (obj == hotori){
            path = "imgs\\hotori\\";
            step = 0;
            initData();
            initImage();
            this.getContentPane().repaint();
        } else if (obj == ikuyo) {
            path = "imgs\\ikuyo\\";
            step = 0;
            initData();
            initImage();
        } else if (obj == nijika) {
            path = "imgs\\nijika\\";
            step = 0;
            initData();
            initImage();
        } else if (obj == ryo) {
            path = "imgs\\ryo\\";
            step = 0;
            initData();
            initImage();
        }
    }
}
