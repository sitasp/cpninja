package org.example.component;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.example.component.panel.CaseComponentEnums;

import javax.swing.*;
import java.awt.*;

@Data
@ToString
public class CaseComponent extends JPanel {

    private     JButton         button;
    private     JLabel          label;
    private     JTextArea       text;
    private     JScrollPane     scrollPane;
    private     String          title;
    private     JPanel          topSection;
    private     String          btnText;
    private     Double          componentHeight;
    private     Double          componentWidth;
    private     ButtonActions   btnActions;
    private     Boolean         btnFlag;
    private     CaseComponentEnums type;
    private     String  textToBePrinted;

    public CaseComponent(String title, String btnText, Double width, Double height, ButtonActions btnActions, Boolean btnFlag, CaseComponentEnums type, String textToBePrinted) {
        this.title      = title;
        this.btnText    = btnText;
        this.componentWidth     = width;
        this.componentHeight     = height;
        this.btnActions     = btnActions;
        this.btnFlag        = btnFlag;
        this.type           = type;
        this.textToBePrinted = textToBePrinted;
        button          = new JButton(btnText);
        button.setEnabled(btnFlag);
        switch(type) {
            case INPUT -> {
                button.addActionListener( e-> btnActions.runIndividualTask(title));
            }
            case OUTPUT -> {
                button.addActionListener(e -> btnActions.doNothing());
            }
            case EXPECTED -> {
                button.addActionListener( e-> btnActions.deleteTestCase(title));
            }
        }
        label           = new JLabel(title);
        text            = new JTextArea();
        if (StringUtils.isNotEmpty(StringUtils.trimToEmpty(textToBePrinted))) {
            text.setText(textToBePrinted);
        }
        scrollPane      = new JScrollPane(text);

        this.setLayout(new BorderLayout());
        topSection  = new JPanel(new BorderLayout());
        topSection.add(label, BorderLayout.WEST);
        topSection.add(button, BorderLayout.EAST);

        add(topSection, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension((int)Math.floor(componentWidth), (int)Math.floor(componentHeight)));
    }
}
