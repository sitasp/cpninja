package org.example.component;

import org.example.component.panel.CodePanel;
import org.example.component.panel.TerminalPanel;
import org.example.module.swing.SwingUIModule;

public class ButtonActions {
    public static void demoFunction() {
        CodePanel codePanel = CodePanel.getInstance();
        TerminalPanel.displayMessage("Hello from demoFunction()\n");
    }

    public static void compileCode() {
        CodePanel codePanel = CodePanel.getInstance();
        TerminalPanel.displayMessage("Hello from compileFunction()\n");
    }

    public static void runCode() {
        CodePanel codePanel = CodePanel.getInstance();
        TerminalPanel.displayMessage("Hello from runFunction()\n");
    }

    public static void compileAndRunCode() {
        CodePanel codePanel = CodePanel.getInstance();
        TerminalPanel.displayMessage("Hello from compileAndRunFunction()\n");
    }

    public static void clearTerminal() {
        TerminalPanel.clearMessage();
    }
}
