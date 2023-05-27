package org.example.component.panel;

import org.example.component.ButtonActions;
import org.example.component.ButtonAdditons;
import org.example.component.objects.Message;
import org.example.constants.NinjaConstants;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TerminalPanel extends JPanel implements ButtonAdditons {

    private Style successStyle = null;
    private Style errorStyle = null;
    private Style defaultStyle = null;

    private JTextPane messageArea;
    private JScrollPane scrollPane;
    private TerminalPanel instance;
    private JPanel instructionTop;
    private ButtonActions btnActions;

    public TerminalPanel(ButtonActions btnActions) {
        messageArea = new JTextPane();
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
        customizeStyles();
    }

    private void customizeStyles() {
        successStyle = messageArea.addStyle("SuccessStyle", null);
        StyleConstants.setForeground(successStyle, new Color(19, 105, 42));
        StyleConstants.setFontFamily(successStyle, "Arial");
        StyleConstants.setFontSize(successStyle, 12);
//        StyleConstants.setBold(successStyle, true);

        errorStyle = messageArea.addStyle("ErrorStyle", null);
        StyleConstants.setForeground(errorStyle, new Color(204, 33, 41));
        StyleConstants.setFontFamily(errorStyle, "Arial");
        StyleConstants.setFontSize(errorStyle, 12);
        StyleConstants.setBold(errorStyle, true);

        defaultStyle = messageArea.addStyle("DefaultStyle", null);
        StyleConstants.setForeground(defaultStyle, Color.BLACK);
        StyleConstants.setFontFamily(defaultStyle, "Arial");
        StyleConstants.setFontSize(defaultStyle, 12);
//        StyleConstants.setBold(defaultStyle, true);
    }

    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            System.out.println(message);
            StyledDocument document = messageArea.getStyledDocument();
            try {
                document.insertString(document.getLength(), message + "\n", defaultStyle);
            } catch (BadLocationException e) {
                System.out.println("exception occurred during terminal output" + e.getMessage());
                displayMessage(message);
            }
        });
    }

    public void displayMessage(Message message) {
        SwingUtilities.invokeLater(() -> {
            System.out.println(message);
            StyledDocument document = messageArea.getStyledDocument();
            try {
                String outputMsg = generateMsg(message);
                document.insertString(document.getLength(), outputMsg, defaultStyle);
            } catch (BadLocationException e) {
                System.out.println("exception occurred during terminal output" + e.getMessage());
                displayMessage(message);
            }
        });
    }

    public void displayErrorMessage(Message message) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Error: " + message);
            StyledDocument document = messageArea.getStyledDocument();
            try {
                String outputMsg = generateMsg(message);
                document.insertString(document.getLength(), outputMsg, errorStyle);
            } catch (BadLocationException e) {
                System.out.println("exception occurred during terminal output" + e.getMessage());
                displayMessage(message);
            }
        });
    }

    public void displaySuccessMessage(Message message) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Success: " + message);
            StyledDocument document = messageArea.getStyledDocument();
            try {
                String outputMsg =  generateMsg(message);
                document.insertString(document.getLength(), outputMsg, successStyle);
            } catch (BadLocationException e) {
                System.out.println("exception occurred " + e.getMessage());
                displayMessage(message);
            }
        });
    }

    private String generateMsg(Message message) {
        if(message.isSuccess())
            return String.format("[%s]   [%s]  | [%s] \n", message.getTime(), message.getSource(), message.getMessage());
        else
            return String.format("[%s]   [%s]  | [\n%s] \n", message.getTime(), message.getSource(), message.getMessage());
    }

    public void clearMessage() {
        SwingUtilities.invokeLater(() -> {
            messageArea.setText("");
        });
    }
}
