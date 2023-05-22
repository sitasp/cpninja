package org.example.component.menuComponent.menuitem;

import org.apache.commons.lang3.StringUtils;
import org.example.utils.NinjaProperties;

import javax.swing.*;
import java.awt.*;
import java.lang.annotation.Documented;
import java.lang.reflect.Field;
import java.util.Objects;

public class NinjaMIv2 extends JMenuItem {
    private JLabel iconLabel;
    private JLabel shortcutLabel;
    private JLabel titleLabel;

    public NinjaMIv2(Icon icon, String text, String shortcutText, String calculatedText) {

        //can handle if icon and shortcut text is empty or null
        super("  ".repeat(calculatedText.length()));
        setLayout(new BorderLayout());

        if(Objects.isNull(icon)){
            this.iconLabel = new JLabel("   ");
        }
        else {
            this.iconLabel = new JLabel(icon);
        }
        add(iconLabel, BorderLayout.WEST);
        customizeIcon();

        this.titleLabel = new JLabel(text);
        add(titleLabel, BorderLayout.CENTER);
        customizeTitle();

        if(Objects.nonNull(shortcutText) && StringUtils.isNotEmpty(shortcutText)){
            this.shortcutLabel = new JLabel(shortcutText);
            add(shortcutLabel, BorderLayout.EAST);
            customizeShortcut();
        }
    }

    public static NinjaMIv2 createNinjaMIv2(Icon icon, String text, String shortcutText) {
        String calculatedText = "    " + text + "  " + shortcutText + "  ";
        return new NinjaMIv2(icon, text, shortcutText, calculatedText);
    }

    public static NinjaMIv2 createNinjaMIv2(String text) {
        return createNinjaMIv2(text, null);
    }

    public static NinjaMIv2 createNinjaMIv2(String text, String shortcutText) {
        return createNinjaMIv2(null, text, shortcutText);
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
