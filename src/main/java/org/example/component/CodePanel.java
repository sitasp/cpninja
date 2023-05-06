package org.example.component;

import org.example.constants.NinjaConstants;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CodePanel extends JPanel implements ButtonAdditons {

    private RSyntaxTextArea codeArea;
    private RTextScrollPane scrollPane;
    private static CodePanel instance;
    public static CodePanel getInstance() {
        if (instance == null) {
            instance = new CodePanel();
        }
        return instance;
    }

    public CodePanel() {
        codeArea = new RSyntaxTextArea();
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        codeArea.setCodeFoldingEnabled(true);

        scrollPane = new RTextScrollPane(codeArea);
        add(scrollPane);
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

    }

    @Override
    public List<JButton> createButtons() {

        List<JButton> buttonList    = new ArrayList<>();
        JButton compileBtn          = new JButton(NinjaConstants.MainCodeConstants.COMPILE);
        JButton runBtn              = new JButton(NinjaConstants.MainCodeConstants.RUN);
        JButton compileRunBtn       = new JButton(NinjaConstants.MainCodeConstants.COMPILE_AND_RUN);

        compileBtn.addActionListener( e-> ButtonActions.compileCode());
        runBtn.addActionListener(e-> ButtonActions.runCode());
        compileRunBtn.addActionListener(e->ButtonActions.compileAndRunCode());

        buttonList.add(compileBtn);
        buttonList.add(runBtn);
        buttonList.add(compileRunBtn);

        return buttonList;
    }

    public void postInit()  {
        List<JButton> buttonListForCodePanel = createButtons();
        addButtons(buttonListForCodePanel);
    }
}
