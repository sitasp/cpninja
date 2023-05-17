package org.example.component;

import lombok.Data;
import org.example.component.panel.TerminalPanel;

import javax.swing.*;

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

    public void compileCode() {
        ((TerminalPanel)terminalPanel).displayMessage("Hello from compileFunction()\n");
    }

    public void runCode() {
        ((TerminalPanel)terminalPanel).displayMessage("Hello from runFunction()\n");
    }

    public void compileAndRunCode() {
        ((TerminalPanel)terminalPanel).displayMessage("Hello from compileAndRunFunction()\n");
    }

    public void clearTerminal() {
        ((TerminalPanel)terminalPanel).clearMessage();
    }

    public void runIndividualTask(String title) {
        ((TerminalPanel)terminalPanel).displayMessage("Running input: " + title + " \n");
    }

    public void doNothing() {
    }

    public void deleteTestCase(String title) {
        ((TerminalPanel)terminalPanel).displayMessage("Deleting testcase for: " + title + " \n");
    }
}
