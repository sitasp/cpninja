package org.example.component.panel;

import org.example.component.ButtonActions;
import org.example.component.ButtonAdditons;
import org.example.constants.NinjaConstants;
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
    public static CodePanel getInstance() {
        if (instance == null) {
            instance = new CodePanel();
        }
        return instance;
    }

    private CodePanel() {
        codeArea = new RSyntaxTextArea();
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        codeArea.setCodeFoldingEnabled(true);

        scrollPane = new RTextScrollPane(codeArea);

//        GridBagLayout layout = new GridBagLayout();
//        setLayout(layout);
//        gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.CENTER;
//        gbc.gridx = 0;
//        gbc.gridy = 0;

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

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
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        this.add(buttonListsPanelForCodePanel, gbc);
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

    private void postInit()  {
        List<JButton> buttonListForCodePanel = createButtons();
        addButtons(buttonListForCodePanel);
    }
}
