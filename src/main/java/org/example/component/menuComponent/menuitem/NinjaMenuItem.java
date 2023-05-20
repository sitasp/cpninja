package org.example.component.menuComponent.menuitem;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.event.MenuDragMouseEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class NinjaMenuItem extends JPanel implements ActionListener{
    private JLabel  iconLabel;
    private JLabel  titleLabel;
    private JLabel  shortcutLabel;

    public NinjaMenuItem(Icon icon, String text, String shortcutText){
        super();
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        titleLabel = new JLabel(text);
        shortcutLabel = new JLabel(shortcutText);
        customizeShortcuts();

        iconLabel = new JLabel(icon);
        setSize(400, 400);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(iconLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 5, 0, 5);
        add(titleLabel, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(0, 10, 0, 0);
        add(shortcutLabel, gbc);
    }

    private void customizeShortcuts() {
        Font currentFont = shortcutLabel.getFont();
        Font newFontWithSize = currentFont.deriveFont(currentFont.getSize() - 2f);
        shortcutLabel.setFont(newFontWithSize);
        shortcutLabel.setForeground(Color.DARK_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


//    @Override
//    public void actionPerformed(ActionEvent e) {
//    }
}
