package org.example.component;

import org.example.component.panel.MainPanel;
import org.example.entity.Problem;
import org.example.utils.CommonUtils;
import org.example.utils.MyCloseActionHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TabManager {
    private static JTabbedPane tabPane = new JTabbedPane();

    public static JTabbedPane getTabPane() {
        return tabPane;
    }

    public static void addANewTab(String title, Problem problem) {
        MainPanel tabBody = new MainPanel(problem, title);
        tabPane.addTab(title, tabBody);
        tabPane.setSelectedComponent(tabBody);

        int index = tabPane.indexOfTab(title);
        JPanel pnlTab = new JPanel(new GridBagLayout());
        pnlTab.setOpaque(false);
        JLabel lblTitle = new JLabel(title);
        JButton btnClose = new JButton("x");
        customizeTabCloseButton(btnClose);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;gbc.gridy=0;gbc.weightx=1;
        pnlTab.add(lblTitle, gbc);

        gbc.gridx++;gbc.weightx=0;
        pnlTab.add(btnClose, gbc);

        tabPane.setTabComponentAt(index, pnlTab);

        MyCloseActionHandler myCloseActionHandler = new MyCloseActionHandler(title, tabPane);
        btnClose.addActionListener(myCloseActionHandler);
    }

    private static void customizeTabCloseButton(JButton button){
        button.setOpaque(false);
        button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        button.setContentAreaFilled(false);
    }

    public static MainPanel currentActiveTabPanel() {
        int selectedTabIndex = tabPane.getSelectedIndex();
        if(selectedTabIndex == -1) {
            System.out.println("No tab selected");

            int lastTabIndex = totalNumberOfTabs();
            selectedTabIndex = lastTabIndex;
        }
        return (MainPanel) tabPane.getComponentAt(selectedTabIndex);
    }

    public static int totalNumberOfTabs() {
        return tabPane.getTabCount();
    }

    public static int currentActiveTab() {
        return tabPane.getSelectedIndex();
    }
}





