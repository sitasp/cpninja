package org.example.component.menuComponent.menu;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import org.example.component.menuComponent.menuitem.NinjaMenuItem;
import org.example.component.panel.TerminalPanel;
import org.example.module.render.UIRender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EditMenu extends JMenu{

    private NinjaMenuItem settingItem;

    public EditMenu() {
        super("Edit");
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        settingItem = new NinjaMenuItem(
                IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SETTINGS, 15, new Color(30, 32, 33)),
                "Settings",
                "ctrl+alt+S");
        add(settingItem);
    }
}
