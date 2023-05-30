package org.example.component.panel;

import lombok.Data;
import org.example.component.ButtonActions;
import org.example.constants.NinjaConstants;
import org.example.entity.Problem;
import org.example.module.execution.common.Program;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class MainPanel extends JPanel {

    private JPanel codePanel;
    private JPanel terminalPanel;
    private JPanel testPanel;
    private JPanel testHolderPanel;
    private JPanel testCaseExtraPanel;
    private ButtonActions buttonActions;
    private JSplitPane codeRightSplitter;
    private JSplitPane terminalTestCaseSplitter;
    private JScrollPane scrollPane;
    private Program program;
    private Double testCaseHeight = NinjaConstants.TestCase.SCROLLPANE_HEIGHT;
    private Double testCaseWidth = NinjaConstants.TestCase.SCROLLPANE_WIDTH;
    
    public MainPanel(Problem problem, String title) {
        buttonActions = new ButtonActions();
        program = new Program();
        program.setName(title);
        codePanel = new CodePanel(buttonActions, problem, program);
        terminalPanel = new TerminalPanel(buttonActions, program);

        List<Problem.Test> tests = null;
        if(problem != null){
            tests = problem.getTests();
        }
        else {
            tests = new ArrayList<>();
            tests.add(new Problem.Test());
            tests.add(new Problem.Test());
        }
        testPanel = new TestPanel(program, buttonActions, tests);

        scrollPane = new JScrollPane(testPanel);
        scrollPane.setPreferredSize(new Dimension((int)Math.floor(testCaseWidth),
                (int)Math.floor(testCaseHeight)));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        testHolderPanel = new JPanel(new BorderLayout());
        testCaseExtraPanel = new TestCaseExtraPanel(program, buttonActions, tests, testPanel, scrollPane);
        testHolderPanel.add(testCaseExtraPanel, BorderLayout.NORTH);
        buttonActions.setCodePanel(codePanel);
        buttonActions.setTerminalPanel(terminalPanel);

        testHolderPanel.add(scrollPane, BorderLayout.CENTER);
        terminalTestCaseSplitter = new JSplitPane(SwingConstants.HORIZONTAL, terminalPanel, testHolderPanel);
        codeRightSplitter = new JSplitPane(SwingConstants.VERTICAL, codePanel, terminalTestCaseSplitter);
        codeRightSplitter.setPreferredSize(new Dimension(1100, 700));
        add(codeRightSplitter);
    }
}
