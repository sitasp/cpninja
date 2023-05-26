package org.example.component.menuComponent.menu;

import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;
import org.example.component.menuComponent.menuitem.NinjaMIv1;
import org.example.component.menuComponent.menuitem.NinjaMIv2;

import javax.swing.*;
import java.awt.*;

public class EditMenu extends JMenu{

    private  NinjaMIv2 settingItem;
    public EditMenu() {
        super("Edit");
        settingItem = NinjaMIv2.createNinjaMIv2(
                IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SETTINGS, 15),
                "Settings",
                "ctrl+alt+S"
        );
        add(settingItem);
    }
}
