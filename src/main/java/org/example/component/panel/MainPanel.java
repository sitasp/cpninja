package org.example.component.panel;

import lombok.Data;
import org.example.component.panel.CodePanel;
import org.example.utils.CommonUtils;

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
//        super(borderLayout);
        codePanel = CodePanel.getInstance();
        terminalPanel = TerminalPanel.getInstance();
        testcasePanel = new JPanel();

//        addTextInTheComponent(codePanel, "Code Panel");
//        addTextInTheComponent(terminalPanel, "Terminal Panel");
        addTextInTheComponent(testcasePanel, "Test case panel");

        terminalTestCaseSplitter = new JSplitPane(SwingConstants.HORIZONTAL, terminalPanel, testcasePanel);
        codeRightSplitter = new JSplitPane(SwingConstants.VERTICAL, codePanel, terminalTestCaseSplitter);
        codeRightSplitter.setPreferredSize(new Dimension(1100, 700));
        add(codeRightSplitter);
    }

    private void addTextInTheComponent(JPanel panel, String title) {
        JLabel label = new JLabel(title);
        panel.add(label);
    }
}
