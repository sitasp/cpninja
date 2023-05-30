package org.example.component.panel;

import lombok.Data;
import org.example.component.ButtonActions;
import org.example.component.ButtonAdditons;
import org.example.constants.NinjaConstants;
import org.example.entity.Problem;
import org.example.module.execution.common.Program;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
public class TestPanel extends JPanel {

    private ButtonActions btnActions;
    private List<Problem.Test> tests;

    private List<TestCasePanel> testCasePanels;
    private Program program;

    public TestPanel(Program program, ButtonActions btnActions, List<Problem.Test> tests) {
        this.btnActions = btnActions;
        this.tests = tests;
        this.program = program;

        int testLength = 2;
        if(Objects.nonNull(tests)) {
            testLength = tests.size();
        }

        this.setLayout(new GridLayout(0, 1));
        for(int i=0;i<testLength;i++) {
            Optional<Problem.Test> optionalTest = Optional.ofNullable(tests != null ? tests.get(i) : null);
            testCasePanels = new ArrayList<>();
            if (optionalTest.isPresent()) {
                TestCasePanel testCasePanel = new TestCasePanel(i + 1, btnActions, program, tests.get(i));
                testCasePanel.getTest().setActive(true);
                this.add(testCasePanel);
                testCasePanels.add(testCasePanel);
            } else {
                TestCasePanel testCasePanel = new TestCasePanel(i + 1, btnActions, program, new Problem.Test());
                testCasePanel.getTest().setActive(true);
                this.add(testCasePanel);
                testCasePanels.add(testCasePanel);
            }
        }
    }
}
