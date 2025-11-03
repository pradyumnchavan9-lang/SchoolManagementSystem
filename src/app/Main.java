package app;

import javax.swing.SwingUtilities;
import gui.LoginGUI;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI().setVisible(true));
    }
}
