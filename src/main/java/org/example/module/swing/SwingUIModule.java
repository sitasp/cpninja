package org.example.module.swing;

import org.example.component.CloseableTabbedPane;
import org.example.component.TabManager;

import javax.swing.*;
import java.awt.*;

public class SwingUIModule extends JFrame {

    private JTextArea messageArea;

    public SwingUIModule() {
        super("Server");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1200, 1080);
        window.setLocationRelativeTo(null);

        TabManager tabManager = new TabManager();
        JTabbedPane tabPane = TabManager.getTabPane();
        window.add(tabPane);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        TabManager.currentActiveTabPanel().add(scrollPane, BorderLayout.CENTER);

        window.setVisible(true);
    }

    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            messageArea.append(message + "\n");
        });
    }
}
