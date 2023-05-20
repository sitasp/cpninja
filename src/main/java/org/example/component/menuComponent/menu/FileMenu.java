package org.example.component.menuComponent.menu;

import org.example.module.render.UIRender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class FileMenu extends JMenu implements ActionListener {
    private JMenuItem exit;
    public FileMenu() {
        super("File");
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        add(exit);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getSource(), exit)) {
            UIRender.renderMessage("Exit button is clicked");
        }

    }
}
