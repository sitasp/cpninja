package org.example.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CloseableTabbedPane extends JTabbedPane {
    JButton closeButton = new JButton("x");

    public CloseableTabbedPane() {
        super();
    }

    public CloseableTabbedPane(int tabPlacement) {
        super(tabPlacement);
    }

    public CloseableTabbedPane(int tabPlacement, int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);
    }

    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);

        int index = indexOfComponent(component);
        JPanel tabPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        closeButton.setBorder(BorderFactory.createEmptyBorder(2, 5,2, 5));
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                removeTabAt(index);
            }
        });
        tabPanel.setOpaque(false);
        tabPanel.add(titleLabel, BorderLayout.WEST);
        tabPanel.add(closeButton, BorderLayout.EAST);
        setTabComponentAt(index, tabPanel);
    }
}
