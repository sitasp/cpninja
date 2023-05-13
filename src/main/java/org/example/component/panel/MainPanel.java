package org.example.component.panel;

import lombok.Data;
import org.example.component.panel.CodePanel;
import org.example.constants.NinjaConstants;
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
    private JScrollPane scrollPane;
    private Double testCaseHeight = NinjaConstants.TestCase.SCROLLPANE_HEIGHT;
    private Double testCaseWidth = NinjaConstants.TestCase.SCROLLPANE_WIDTH;
    
    public MainPanel() {
        codePanel = CodePanel.getInstance();
        terminalPanel = TerminalPanel.getInstance();
        testcasePanel = new JPanel(new GridLayout(0, 1));

        for(int i=0;i<10;i++){
            testcasePanel.add(new TestCasePanel(i+1));
        }

        scrollPane = new JScrollPane(testcasePanel);
        scrollPane.setPreferredSize(new Dimension((int)Math.floor(testCaseWidth),
                (int)Math.floor(testCaseHeight)));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        terminalTestCaseSplitter = new JSplitPane(SwingConstants.HORIZONTAL, terminalPanel, scrollPane);
        codeRightSplitter = new JSplitPane(SwingConstants.VERTICAL, codePanel, terminalTestCaseSplitter);
        codeRightSplitter.setPreferredSize(new Dimension(1100, 700));
        add(codeRightSplitter);
    }

    private void addTextInTheComponent(JPanel panel, String title) {
        JLabel label = new JLabel(title);
        panel.add(label);
    }
}
