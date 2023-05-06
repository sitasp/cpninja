package org.example.component;

public class ButtonActions {
    public static void demoFunction() {
        CodePanel codePanel = CodePanel.getInstance();
        codePanel.getCodeArea().append("Hello from demoFunction()\n");
    }

    public static void compileCode() {
        CodePanel codePanel = CodePanel.getInstance();
        codePanel.getCodeArea().append("Hello from compileFunction()\n");
    }

    public static void runCode() {
        CodePanel codePanel = CodePanel.getInstance();
        codePanel.getCodeArea().append("Hello from runFunction()\n");
    }

    public static void compileAndRunCode() {
        CodePanel codePanel = CodePanel.getInstance();
        codePanel.getCodeArea().append("Hello from compileAndRunFunction()\n");
    }
}
