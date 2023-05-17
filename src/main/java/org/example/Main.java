package org.example;
import org.example.component.TabManager;
import org.example.component.panel.TerminalPanel;
import org.example.module.render.UIRender;
import org.example.module.socket.ServerSocketModule;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ServerSocketModule serverModule = new ServerSocketModule();
        UIRender.getInstance();

        new Thread(() -> {
            serverModule.startServer();
        }).start();
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab.contentMargins", new Insets(10, 100, 0, 0));
        UIRender.renderMessage("Server started...");
    }
}

