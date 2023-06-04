package org.example.component.panel;

import org.example.component.ButtonActions;
import org.example.component.ButtonAdditons;
import org.example.constants.NinjaConstants;
import org.example.entity.Problem;
import org.example.module.execution.common.Program;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestCaseExtraPanel extends JPanel implements ButtonAdditons {
    private List<Problem.Test> tests;
    private Integer failureCount = 0;
    private ButtonActions btnActions;
    private Program program;
    private JPanel testPanel;
    private JScrollPane testCaseScrollPane;

    public TestCaseExtraPanel(Program program, ButtonActions btnActions, List<Problem.Test> tests, JPanel testPanel, JScrollPane testCaseScrollPane) {
        this.btnActions = btnActions;
        this.tests = tests;
        this.program = program;
        this.testPanel = testPanel;
        this.testCaseScrollPane = testCaseScrollPane;
        postInit();
    }

    private void postInit() {
        List<JButton> btns = createButtons();
        addButtons(btns);
    }


    @Override
    public void addButtons(List<JButton> buttons) {
        JPanel buttonListsPanelForTestPanel = new JPanel();
        buttonListsPanelForTestPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        for ( JButton button: buttons ) {
            buttonListsPanelForTestPanel.add(button);
        }

        this.add(buttonListsPanelForTestPanel, BorderLayout.PAGE_START);
    }

    @Override
    public List<JButton> createButtons() {
        List<JButton> buttonList    = new ArrayList<>();
        JButton addTestBtn            = new JButton(NinjaConstants.TestCase.ADD_TEST);
        addTestBtn.setPreferredSize(new Dimension(70, 20));

        addTestBtn.addActionListener( e-> {
            btnActions.addANewTest(program, testPanel, testCaseScrollPane);
        });
        buttonList.add(addTestBtn);

        return buttonList;
    }
}
