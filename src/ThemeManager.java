import java.awt.*;
import javax.swing.*;

public class ThemeManager{

    //Colors
    public static final Color PRIMARY_COLOR=new Color(52,152,219); //Blue
    public static final Color SECONDARY_COLOR = new Color(41, 128, 185);
    public static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    public static final Color TEXT_COLOR = new Color(44, 62, 80);

    //Fonts
    public static final Font TITLE_FONT=new Font("Segoe UI", Font.BOLD, 22);
    public static final Font LABEL_FONT=new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font BUTTON__FONT=new Font("Segoe UI", Font.BOLD, 14);

    //Button Styling Helper
    public static void styleButton(JButton button)
    {
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(BUTTON__FONT);
        button.setBorder(BorderFactory.createEmptyBorder(8,15,8,15));
    }

    //Panel Background
    public static void setPanelBackground(JPanel panel)
    {
        panel.setBackground(BACKGROUND_COLOR);
    }
}