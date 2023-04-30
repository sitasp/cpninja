package org.example.component;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class MainPanel extends JPanel {

    private JPanel codePanel;
    private JPanel terminalPanel;
    private JPanel testcasePanel;
    private JSplitPane codeRightSplitter;
    private JSplitPane terminalTestCaseSplitter;

    public MainPanel() {
        codePanel = new JPanel();
        terminalPanel = new JPanel();
        testcasePanel = new JPanel();

        addTextInTheComponent(codePanel, "Code Panel");
        addTextInTheComponent(terminalPanel, "Terminal Panel");
        addTextInTheComponent(testcasePanel, "Test case panel");

        terminalTestCaseSplitter = new JSplitPane(SwingConstants.HORIZONTAL, terminalPanel, testcasePanel);
        codeRightSplitter = new JSplitPane(SwingConstants.VERTICAL, codePanel, terminalTestCaseSplitter);
    }

    private void addTextInTheComponent(JPanel panel, String title) {
        JLabel label = new JLabel(title);
        panel.add(label);
    }
}
