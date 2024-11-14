package edu.ufsj;

import edu.ufsj.view.login.JLogin;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JLogin jLogin = new JLogin();
        jLogin.setVisible(true);
        jLogin.setLocationRelativeTo(null);
        jLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}