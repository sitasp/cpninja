package org.example.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyCloseActionHandler implements ActionListener {
    private String tabName;

    private JTabbedPane tabPane;

    public MyCloseActionHandler(String tabName, JTabbedPane tabPane) {
        this.tabName = tabName;
        this.tabPane = tabPane;
    }

    public String getTabName() {
        return tabName;
    }

    public JTabbedPane getTabPane() {
        return tabPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = tabPane.indexOfTab(getTabName());

        if(index >= 0) {
            tabPane.removeTabAt(index);
        }
    }
}
