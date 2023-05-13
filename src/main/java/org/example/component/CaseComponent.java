package org.example.component;

import lombok.Data;
import lombok.ToString;

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

    public CaseComponent(String title, String btnText, Double width, Double height) {
        this.title      = title;
        this.btnText    = btnText;
        this.componentWidth     = width;
        this.componentHeight     = height;
        button          = new JButton(btnText);
        label           = new JLabel(title);
        text            = new JTextArea();
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
