package ui;

import user.User;
import utils.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements ActionListener {

    JButton loginButton = new JButton(new ImageIcon("imgs/login/login.png"));
    JButton registerButton = new JButton(new ImageIcon("imgs/login/register.png"));
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField veriCode = new JTextField();
    JLabel correctCode = new JLabel();

    // input username
    String inputUsername;
    // input password
    String inputPassword;
    // input verification code
    String inputcode;
    // verification code
    String code = Code.getCode();


    static ArrayList<User> list = new ArrayList<>();
    static {
        list.add(new User("koukau", "541880"));
    }

    public LoginJFrame(){
        initJFrame();
        initView();

        this.setVisible(true);
    }


    private void initJFrame(){
        this.setSize(488,430);
        // set title
        this.setTitle("Puzzle Game Login");
        // set frame always on top
//        this.setAlwaysOnTop(true);
        // set frame to middle
        this.setLocationRelativeTo(null);
        // set the close model
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setAlwaysOnTop(true);
    }
    private void initView(){
        // add username
        JLabel usernameText = new JLabel(new ImageIcon("imgs/login/username.png"));
        usernameText.setBounds(100, 135, 86, 29);
        this.getContentPane().add(usernameText);
        // username input box
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        // add password
        JLabel passwordText = new JLabel(new ImageIcon("imgs/login/password.png"));
        passwordText.setBounds(100, 196, 84, 29);
        this.getContentPane().add(passwordText);
        // password input box
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        // add verification code
        JLabel veriCodeText = new JLabel(new ImageIcon("imgs/login/verification code.png"));
        veriCodeText.setBounds(92, 250, 94, 47);
        this.getContentPane().add(veriCodeText);
        // verification code input box
        veriCode.setBounds(195, 256, 100, 30);
        this.getContentPane().add(veriCode);
        // show code
        correctCode.setText(code);
        Font newFont = new Font("Arial", Font.PLAIN, 15);
        correctCode.setFont(newFont);
        correctCode.setForeground(Color.CYAN);
        correctCode.setBounds(300, 256, 50, 30);
        this.getContentPane().add(correctCode);

        // add login button
        loginButton.setBounds(123, 310, 128, 47);
//        loginButton.setIcon(new ImageIcon(""));
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        this.getContentPane().add(loginButton);
        loginButton.addActionListener(this);

        // add register button
        registerButton.setBounds(256, 310, 128, 47);
//        registerButton.setIcon(new ImageIcon(""));
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        this.getContentPane().add(registerButton);
        registerButton.addActionListener(this);

        // add bcakground
        JLabel background = new JLabel(new ImageIcon("imgs/login/background.jpg"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }

    public void showJDialog(String content){
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);

        JLabel warning = new JLabel();
        warning.setText(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object  obj = e.getSource();
        if (obj == loginButton){
            boolean flag = checkLogin(list);
            if (flag){
                this.setVisible(false);
                new GameJFrame();
            }else {
                code = Code.getCode();
                correctCode.setText(code);
            }
        }else if (obj == registerButton){
            showJDialog("Register function has not been finished yet.");
        }
    }

    public boolean checkLogin(ArrayList<User> list){
        inputUsername = username.getText();
        inputPassword = password.getText();
        inputcode = veriCode.getText();

        if (! inputcode.equalsIgnoreCase(code)){
            showJDialog("Verification code is wrong");
            return false;
        }

        // if username or password is void
        if (inputcode.isEmpty()){
            showJDialog("Please input username");
            return false;
        }
        if (inputPassword.isEmpty()){
            showJDialog("Please input password");
            return false;
        }

        // if username exit
        if (!User.contains(list, inputUsername)) {
            showJDialog("Username doesn't exit, please register");
            return false;
        }

        User userInfo = new User(inputUsername, inputPassword);

        boolean flag = User.checkUserInfo(list, userInfo);
        if (! flag){
            showJDialog("Username or Password is wrong, please input again");
            return false;
        }

        return true;
    }


}
