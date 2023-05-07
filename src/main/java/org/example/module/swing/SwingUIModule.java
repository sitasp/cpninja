package org.example.module.swing;

import org.example.component.TabManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SwingUIModule extends JFrame {

    private static SwingUIModule instance;
    private static JTextArea messageArea;


    public static SwingUIModule getInstance() {
        if(Objects.isNull(instance)) {
            instance = new SwingUIModule();
        }
        return instance;
    }

    private SwingUIModule() {
        super("Server");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1200, 1080);
        window.setLocationRelativeTo(null);

        TabManager tabManager = new TabManager();
        JTabbedPane tabPane = TabManager.getTabPane();
        window.add(tabPane, BorderLayout.CENTER);

//        messageArea = new JTextArea();
//        messageArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(messageArea);
//        CodePanel codePanel = new CodePanel();
//        TabManager.currentActiveTabPanel().add(codePanel, BorderLayout.NORTH);
//        TabManager.currentActiveTabPanel().add(scrollPane, BorderLayout.CENTER);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //below two statements are used at the end
        window.pack();
        window.setVisible(true);
    }

    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            messageArea.append(message + "\n");
        });
    }
}
