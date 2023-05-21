package org.example.component.menuComponent.menuitem;

import javax.swing.*;
import java.awt.*;

public class NinjaMIv2 extends JMenuItem {
    private JLabel iconLabel;
    private JLabel shortcutLabel;
    private JLabel titleLabel;

    public NinjaMIv2(Icon icon, String text, String shortcutText, String calculatedText) {
        super("  ".repeat(calculatedText.length()));
        setLayout(new BorderLayout());

        this.iconLabel = new JLabel(icon);
        add(iconLabel, BorderLayout.WEST);

        this.titleLabel = new JLabel(text);
        add(titleLabel, BorderLayout.CENTER);

        this.shortcutLabel = new JLabel(shortcutText);
        add(shortcutLabel, BorderLayout.EAST);

        customizeShortcuts();
    }

    public static NinjaMIv2 createNinjaMIv2(Icon icon, String text, String shortcutText) {
        String calculatedText = "    " + text + "  " + shortcutText + "  ";
        return new NinjaMIv2(icon, text, shortcutText, calculatedText);
    }

    private void customizeShortcuts() {
        Font currentFont = shortcutLabel.getFont();
        Font newFontWithSize = currentFont.deriveFont(currentFont.getSize() - 2f);
        shortcutLabel.setFont(newFontWithSize);
        shortcutLabel.setForeground(Color.DARK_GRAY);
    }
}
