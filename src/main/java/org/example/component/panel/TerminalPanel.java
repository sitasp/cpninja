package org.example.component.panel;

import org.example.component.ButtonActions;
import org.example.component.ButtonAdditons;
import org.example.constants.NinjaConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TerminalPanel extends JPanel implements ButtonAdditons {

    private static JTextArea messageArea;
    private static JScrollPane scrollPane;
    private static TerminalPanel instance;
    private static JPanel instructionTop;

    public static TerminalPanel getInstance() {
        if(Objects.isNull(instance)) {
            instance = new TerminalPanel();
        }
        return instance;
    }

    private TerminalPanel() {
        messageArea = new JTextArea();
        scrollPane = new JScrollPane();
        scrollPane.add(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Messages");

        instructionTop = new JPanel(new BorderLayout());
        instructionTop.add(label, BorderLayout.WEST);
        add(instructionTop, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
        postInit();
    }

    @Override
    public void addButtons(List<JButton> buttons) {
        JPanel buttonListsPanelForTerminalPanel = new JPanel();
        buttonListsPanelForTerminalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        for ( JButton button: buttons ) {
            buttonListsPanelForTerminalPanel.add(button);
        }
        instructionTop.add(buttonListsPanelForTerminalPanel, BorderLayout.EAST);
    }

    @Override
    public List<JButton> createButtons() {
        List<JButton> buttonList    = new ArrayList<>();
        JButton clearBtn            = new JButton(NinjaConstants.Terminal.CLEAR);

        clearBtn.addActionListener( e-> ButtonActions.clearTerminal());
        buttonList.add(clearBtn);

        return buttonList;
    }

    private void postInit(){
        List<JButton> buttonListForTerminalPanel = createButtons();
        addButtons(buttonListForTerminalPanel);
    }
    public static void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            messageArea.append(message + "\n");
        });
    }
}
