package org.example.component;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.example.component.objects.Message;
import org.example.component.panel.CodePanel;
import org.example.component.panel.TerminalPanel;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
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

    public void runCode() {
        ((TerminalPanel)terminalPanel).displayMessage("Hello from runFunction()\n");
    }

    public void compileAndRunCode() {
        ((TerminalPanel)terminalPanel).displayMessage("Hello from compileAndRunFunction()\n");
    }

    public void compileCode() {
        String writtenCode = ((CodePanel)codePanel).getCodeArea().getText();
        File tempFile = null;
        Message message = new Message();
        message.setSource("Compiler");


        try {
            tempFile = new File("Main.java");
            try (FileWriter fileWriter = new FileWriter(tempFile)) {
                fileWriter.write(writtenCode);
            }
            ProcessBuilder processBuilder = new ProcessBuilder("javac", tempFile.getAbsolutePath());
            Process compileProcess = processBuilder.start();
            int exitCode = compileProcess.waitFor();

            if(!Objects.equals(exitCode, 0)) {
                String errorOutput = new String(compileProcess.getErrorStream().readAllBytes());
                message.setSuccess(false);

                if(StringUtils.isNotEmpty(errorOutput)) {
                    message.setMessage(errorOutput);
                }
                else {
                    message.setMessage("Code can't be compiled");
                }
                ((TerminalPanel)terminalPanel).displayErrorMessage(message);
                return;
            }
            message.setMessage("Compilation successful");
            ((TerminalPanel)terminalPanel).displaySuccessMessage(message);
        }
        catch (Exception ex) {
            if(ex instanceof IOException) {
                message.setMessage("File creation error during compilation: " + ex.getMessage());
                ((TerminalPanel)terminalPanel).displayErrorMessage(message);
            }
            else {
                message.setMessage("Unhandled error during compilation: " + ex.getMessage());
                ((TerminalPanel)terminalPanel).displayErrorMessage(message);
            }
        }
        finally {
            if(Objects.nonNull(tempFile))tempFile.deleteOnExit(); // Delete the file when the JVM exits
        }
//        ((TerminalPanel)terminalPanel).displayMessage("Hello from compileFunction()\n" + writtenCode + "\n");
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
