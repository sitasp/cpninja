package org.example.component.menuComponent.menuitem;

import org.example.utils.NinjaProperties;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

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

        customizeShortcut();
        customizeIcon();
        customizeTitle();
    }

    public static NinjaMIv2 createNinjaMIv2(Icon icon, String text, String shortcutText) {
        String calculatedText = "    " + text + "  " + shortcutText + "  ";
        return new NinjaMIv2(icon, text, shortcutText, calculatedText);
    }

    private void customizeShortcut() {
        Font currentFont = shortcutLabel.getFont();
        Font newFontWithSize = currentFont.deriveFont(NinjaProperties.MenuItem.SHORTCUT_FONT_SIZE + 0f);
        shortcutLabel.setFont(newFontWithSize);

        setColorCode(NinjaProperties.MenuItem.SHORTCUT_FONT_COLOR, shortcutLabel);
    }

    private void customizeTitle() {
        Font currentFont = titleLabel.getFont();
        Font newFontWithSize = currentFont.deriveFont(NinjaProperties.MenuItem.TITLE_FONT_SIZE + 0f);
        titleLabel.setFont(newFontWithSize);

        setColorCode(NinjaProperties.MenuItem.TITLE_FONT_COLOR, titleLabel);
    }

    private void customizeIcon() {
        Font currentFont = iconLabel.getFont();
        Font newFontWithSize = currentFont.deriveFont(NinjaProperties.MenuItem.ICON_FONT_SIZE + 0f);
        iconLabel.setFont(newFontWithSize);
        setColorCode(NinjaProperties.MenuItem.ICON_FONT_COLOR, iconLabel);
    }

    private static void setColorCode(String colorCode, JLabel label) {
        try {
            Field field = Color.class.getDeclaredField(colorCode);
            field.setAccessible(true);
            Color color = (Color) field.get(null);
            System.out.println("Color: " + color);
            label.setForeground(color);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
