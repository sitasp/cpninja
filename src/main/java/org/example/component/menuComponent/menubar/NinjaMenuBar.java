package org.example.component.menuComponent.menubar;

import org.example.component.menuComponent.menu.EditMenu;
import org.example.component.menuComponent.menu.FileMenu;
import org.example.component.menuComponent.menu.ViewMenu;

import javax.swing.*;

public class NinjaMenuBar extends JMenuBar {
    private FileMenu file;
    private EditMenu edit;
    private ViewMenu view;

    public NinjaMenuBar() {
        super();
        file = new FileMenu();
        edit = new EditMenu();
        view = new ViewMenu();

        add(file);
        add(edit);
        add(view);
    }
}
