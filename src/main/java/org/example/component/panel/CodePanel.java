package org.example.component.panel;

import org.example.component.ButtonActions;
import org.example.component.ButtonAdditons;
import org.example.component.TabManager;
import org.example.constants.NinjaConstants;
import org.example.entity.Problem;
import org.example.module.execution.common.Language;
import org.example.module.execution.common.Program;
import org.example.module.execution.java.Java;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CodePanel extends JPanel implements ButtonAdditons {
    private RSyntaxTextArea codeArea;
    private RTextScrollPane scrollPane;
    private static CodePanel instance;
    private GridBagConstraints gbc;
    private ButtonActions buttonActions;
    private Program       program;
    private Problem       problem;

    public CodePanel(ButtonActions btnActions, Problem problem, Program program) {
        codeArea = new RSyntaxTextArea();
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        codeArea.setCodeFoldingEnabled(true);
        this.program = program;
        this.buttonActions = btnActions;
        this.problem = problem;
        postInit();
    }

    public RSyntaxTextArea getCodeArea() {
        return codeArea;
    }

    public void setCodeArea(RSyntaxTextArea codeArea) {
        this.codeArea = codeArea;
    }

    public RTextScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(RTextScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodePanel codePanel = (CodePanel) o;
        return Objects.equals(codeArea, codePanel.codeArea) && Objects.equals(scrollPane, codePanel.scrollPane);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeArea, scrollPane);
    }

    @Override
    public String toString() {
        return "CodePanel{" +
                "codeArea=" + codeArea +
                ", scrollPane=" + scrollPane +
                '}';
    }

    @Override
    public void addButtons(List<JButton> buttons) {
        JPanel buttonListsPanelForCodePanel = new JPanel();
        buttonListsPanelForCodePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        for ( JButton button: buttons ) {
            buttonListsPanelForCodePanel.add(button);
        }

        this.add(buttonListsPanelForCodePanel, BorderLayout.SOUTH);
    }

    @Override
    public List<JButton> createButtons() {

        List<JButton> buttonList    = new ArrayList<>();
        JButton compileBtn          = new JButton(NinjaConstants.MainCodeConstants.COMPILE);
        JButton runBtn              = new JButton(NinjaConstants.MainCodeConstants.RUN);
        JButton compileRunBtn       = new JButton(NinjaConstants.MainCodeConstants.COMPILE_AND_RUN);

        compileBtn.addActionListener( e-> buttonActions.compileCode(this.program));
        runBtn.addActionListener(e-> buttonActions.runCode(this.program));
        compileRunBtn.addActionListener(e-> buttonActions.compileAndRunCode(this.program));

        buttonList.add(compileBtn);
        buttonList.add(runBtn);
        buttonList.add(compileRunBtn);

        return buttonList;
    }

    private void postInit()  {
        scrollPane = new RTextScrollPane(codeArea);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        List<JButton> buttonListForCodePanel = createButtons();
        addButtons(buttonListForCodePanel);
        fillExtraDetailsInCodePanel();
    }
    private void fillExtraDetailsInCodePanel() {
        if(Objects.isNull(problem))
            return;
        addCommentToCodePanel("Problem: " + problem.getName());
        addCommentToCodePanel("Contest: " + problem.getGroup());
        addCommentToCodePanel("URL: " + problem.getUrl());
        addCommentToCodePanel("Memory Limit: " + problem.getMemoryLimit());
        addCommentToCodePanel("Time Limit: " + problem.getTimeLimit());
        addCommentToCodePanel("CP Ninja");
    }

    private void addCommentToCodePanel(String commentToBeAdded) {
        Language language = Language.findEnumByValue(NinjaConstants.DEFAULT_LANGUAGE);
        String cmnt = "";
        if (Objects.requireNonNull(language) == Language.JAVA) {
            cmnt = Java.addComments(commentToBeAdded);
        }
        codeArea.append(cmnt);
    }
}
