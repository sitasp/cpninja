package org.example.component.menuComponent.menu;

import org.example.module.render.UIRender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ViewMenu extends JMenu implements ActionListener {
    private JMenuItem appearanceItem;
    public ViewMenu() {
        super("View");
        appearanceItem = new JMenuItem("Appearance");
        appearanceItem.addActionListener(this);
        add(appearanceItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getSource(), appearanceItem)) {
            UIRender.renderMessage("appearance btn clicked");
        }
    }
}
