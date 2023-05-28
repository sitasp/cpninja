package org.example.component.panel;

import lombok.Data;
import org.example.component.ButtonActions;
import org.example.component.panel.CodePanel;
import org.example.constants.NinjaConstants;
import org.example.entity.Problem;
import org.example.module.execution.common.Program;
import org.example.utils.CommonUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Optional;

@Data
public class MainPanel extends JPanel {

    private JPanel codePanel;
    private JPanel terminalPanel;
    private JPanel testcasePanel;
    private ButtonActions buttonActions;
    private JSplitPane codeRightSplitter;
    private JSplitPane terminalTestCaseSplitter;
    private JScrollPane scrollPane;
    private Program program;
    private Double testCaseHeight = NinjaConstants.TestCase.SCROLLPANE_HEIGHT;
    private Double testCaseWidth = NinjaConstants.TestCase.SCROLLPANE_WIDTH;
    
    public MainPanel(Problem problem) {
        buttonActions = new ButtonActions();
        program = new Program();
        codePanel = new CodePanel(buttonActions, problem, program);
        terminalPanel = new TerminalPanel(buttonActions, program);
        testcasePanel = new JPanel(new GridLayout(0, 1));
        buttonActions.setCodePanel(codePanel);
        buttonActions.setTerminalPanel(terminalPanel);

        int testsLength = 2;
        if(Objects.nonNull(problem) && Objects.nonNull(problem.getTests())) {
            testsLength = problem.getTests().size();
        }
        for(int i=0;i<testsLength;i++){
            int finalI = i;
            testcasePanel.add(new TestCasePanel(i+1, buttonActions, program,
                    Optional.ofNullable(problem)
                            .map(Problem::getTests)
                            .map(e -> e.get(finalI))
                            .orElse(null)));
                        //Optional.ofNullable(problem).map(e -> e.getTests()).map(e -> e.get(i)).orElse(null)));
                        //problem.getTests().get(i)));
        }
        postInit();
    }

    private void postInit() {
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
