package org.example.component;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.example.component.objects.Message;
import org.example.component.panel.CodePanel;
import org.example.component.panel.TerminalPanel;
import org.example.component.panel.TestCasePanel;
import org.example.entity.Problem;
import org.example.module.execution.common.CodeDriver;
import org.example.module.execution.common.Program;
import org.example.utils.CommonUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class ButtonActions {
    private JPanel codePanel;
    private JPanel terminalPanel;
    private JPanel testcasePanel;
    public ButtonActions(JPanel codePanel, JPanel terminalPanel, JPanel testcasePanel) {
        this.codePanel = codePanel;
        this.terminalPanel = terminalPanel;
        this.testcasePanel = testcasePanel;
    }
    public ButtonActions() {
    }

    public void demoFunction() {
        ((TerminalPanel)terminalPanel).displayMessage("Hello from demoFunction()\n");
    }

    public void runCode(Program program) {
        ((TerminalPanel)terminalPanel).displayMessage("Hello from runFunction()\n");
    }

    public void compileAndRunCode(Program program) {
        ((TerminalPanel)terminalPanel).displayMessage("Hello from compileAndRunFunction()\n");
    }

    public void compileCode(Program program) {
        CodeDriver selectedDriver = CodeDriver.selectDriver();
        String writtenCode = ((CodePanel)codePanel).getCodeArea().getText();
        program.setContent(writtenCode);
        ((TerminalPanel)terminalPanel).displayMessage(new Message("Compiler", "Compilation started"));
        Message message = selectedDriver.compileCode(program);
        if(message.isSuccess()){
            ((TerminalPanel)terminalPanel).displaySuccessMessage(new Message("Compiler", "Compilation finished"));
            ((TerminalPanel)terminalPanel).displaySuccessMessage(message);
        }
        else {
            ((TerminalPanel)terminalPanel).displayMessage(new Message("Compile", "Compilation failed"));
            ((TerminalPanel)terminalPanel).displayErrorMessage(message);
        }
    }

    public void clearTerminal() {
        ((TerminalPanel)terminalPanel).clearMessage();
    }

    public void runIndividualTask(String title, Program program) {
        ((TerminalPanel)terminalPanel).displayMessage("Running input: " + title + " \n");
    }

    public void doNothing() {
    }

    public void deleteTestCase(String title) {
        ((TerminalPanel)terminalPanel).displayMessage("Deleting testcase for: " + title + " \n");
    }

    public void addANewTest(List<Problem.Test> tests, Program program, JPanel testcaseLayoutHelper, JScrollPane scrollPane) {
        TestCasePanel testCasePanel = new TestCasePanel(tests.size()+1, this, program, new Problem.Test());
        tests.add(new Problem.Test());
        testcaseLayoutHelper.add(testCasePanel);
        ((TerminalPanel)terminalPanel).displayMessage("Adding a new test case \n");

        scrollPane.setViewportView(testcaseLayoutHelper);
        scrollPane.revalidate();
        scrollPane.repaint();
    }
}
