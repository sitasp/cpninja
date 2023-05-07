package org.example;
import org.example.component.panel.TerminalPanel;
import org.example.module.socket.ServerSocketModule;
import org.example.module.swing.SwingUIModule;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUIModule uiModule = SwingUIModule.getInstance();
        ServerSocketModule serverModule = new ServerSocketModule(uiModule);

        new Thread(() -> {
            serverModule.startServer();
        }).start();
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab.contentMargins", new Insets(10, 100, 0, 0));
        TerminalPanel.displayMessage("Server started...");
    }
}

