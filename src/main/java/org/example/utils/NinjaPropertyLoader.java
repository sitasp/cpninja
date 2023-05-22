package org.example.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class NinjaPropertyLoader {
    private static NinjaPropertyLoader instance;
    private static Properties          properties;

    public static NinjaPropertyLoader getInstance() {
        if(Objects.isNull(instance)) {
            instance = new NinjaPropertyLoader();
        }
        return instance;
    }

    private NinjaPropertyLoader() {
        setupLoader();
        loadProperties();
    }

    private void loadProperties() {
        NinjaProperties.HEIGHT                                  = properties.getProperty("ninja.window.boot.height");
        NinjaProperties.WIDTH                                   = properties.getProperty("ninja.window.boot.width");
        NinjaProperties.DEFAULT_SELECTED_LANGUAGE               = properties.getProperty("ninja.program.default.language");
        NinjaProperties.WINDOW_TITLE                            = properties.getProperty("ninja.window.title");
        NinjaProperties.WINDOW_SUMMARY                          = properties.getProperty("ninja.window.summary");

        NinjaProperties.MenuItem.ICON_FONT_COLOR                = properties.getProperty("ninja.window.menu.item.icon.font.color");
        NinjaProperties.MenuItem.ICON_FONT_SIZE                 = Integer.parseInt(properties.getProperty("ninja.window.menu.item.icon.font.size"));

        NinjaProperties.MenuItem.SHORTCUT_FONT_COLOR            = properties.getProperty("ninja.window.menu.item.shortcut.font.color");
        NinjaProperties.MenuItem.SHORTCUT_FONT_SIZE             = Integer.parseInt(properties.getProperty("ninja.window.menu.item.shortcut.font.size"));

        NinjaProperties.MenuItem.TITLE_FONT_COLOR               = properties.getProperty("ninja.window.menu.item.title.font.color");
        NinjaProperties.MenuItem.TITLE_FONT_SIZE                = Integer.parseInt(properties.getProperty("ninja.window.menu.item.title.font.size"));
    }


    private void setupLoader() {
        properties = new Properties();
        try (InputStream inputStream = NinjaPropertyLoader.class.getResourceAsStream("/application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
