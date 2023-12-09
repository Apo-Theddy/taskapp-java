package apo.taskapp;

import apo.taskapp.views.Login;

public class App {
    
    public static void main(String[] args) {
        Login loginView = new Login();
        loginView.setVisible(true);
        loginView.setLocationRelativeTo(null);
    }
}
