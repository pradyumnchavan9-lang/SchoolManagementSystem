package theme;

import java.awt.*;
import javax.swing.*;

public class ThemeManager {
    public static final Color PRIMARY_COLOR = new Color(51, 102, 204);
    public static final Color SECONDARY_COLOR = new Color(245, 245, 245);
    public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 20);
    public static final Font LABEL_FONT = new Font("SansSerif", Font.PLAIN, 14);

    public static void styleButton(JButton button) {
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(LABEL_FONT);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    }
}
