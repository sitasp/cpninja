package org.example.module.loader;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

import java.util.Objects;
import java.util.Properties;

public class BootLoader {

    private static BootLoader instance;
    private static Properties properties;

    public static BootLoader getInstance() {
        if(Objects.isNull(instance)) {
            instance = new BootLoader();
        }
        return instance;
    }

    private BootLoader() {
        initLoader();
    }


    private void initLoader() {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        IconFontSwing.register(FontAwesome.getIconFont());
    }
}
