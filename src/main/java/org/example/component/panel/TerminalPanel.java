package org.example.component.panel;

import org.example.component.ButtonActions;
import org.example.component.ButtonAdditons;
import org.example.constants.NinjaConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TerminalPanel extends JPanel implements ButtonAdditons {

    private JTextArea messageArea;
    private JScrollPane scrollPane;
    private TerminalPanel instance;
    private JPanel instructionTop;
    private ButtonActions btnActions;

//    public static TerminalPanel getInstance() {
//        if(Objects.isNull(instance)) {
//            instance = new TerminalPanel();
//        }
//        return instance;
//    }

    public TerminalPanel(ButtonActions btnActions) {
        messageArea = new JTextArea();
        scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension((int)Math.floor(NinjaConstants.Terminal.TERMINAL_WIDTH),
                (int)Math.floor(NinjaConstants.Terminal.TERMINAL_HEIGHT)));
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Messages");
        this.btnActions = btnActions;
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

        clearBtn.addActionListener( e-> btnActions.clearTerminal());
        buttonList.add(clearBtn);

        return buttonList;
    }

    private void postInit(){
        List<JButton> buttonListForTerminalPanel = createButtons();
        addButtons(buttonListForTerminalPanel);
    }
    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            System.out.println(message);
            messageArea.append(message + "\n");
        });
    }

    public void clearMessage() {
        SwingUtilities.invokeLater(() -> {
            messageArea.setText("");
        });
    }
}
