package org.example.module.render;

import org.apache.commons.lang3.StringUtils;
import org.example.component.TabManager;
import org.example.component.menuComponent.menubar.NinjaMenuBar;
import org.example.component.panel.CodePanel;
import org.example.component.panel.MainPanel;
import org.example.component.panel.TerminalPanel;
import org.example.constants.NinjaConstants;
import org.example.entity.Problem;
import org.example.entity.Source;
import org.example.module.execution.common.Language;
import org.example.module.execution.java.Java;
import org.example.utils.PlatformParser;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Objects;

public class UIRender extends JFrame{
    private static UIRender instance;
    public static UIRender getInstance() {
        if(Objects.isNull(instance)) {
            instance = new UIRender();
        }
        return instance;
    }

    private UIRender() {
        super("Server");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();

        // Calculate the maximum width and height (80% of the screen dimensions)
        int maxWidth = (int) (screenWidth * 1.0);
        int maxHeight = (int) (screenHeight * 1.0);

        // Set the maximum width and height
        window.setMaximumSize(new Dimension(maxWidth, maxHeight));

        // Set the preferred size (optional)
        window.setPreferredSize(new Dimension(maxWidth, maxHeight));

        window.setLocationRelativeTo(null);

        NinjaMenuBar menuBar = new NinjaMenuBar();
        window.setJMenuBar(menuBar);
        menuBar.setVisible(true);

        JTabbedPane tabPane = TabManager.getTabPane();
        window.add(tabPane, BorderLayout.CENTER);

        UIRender.initialRendering();
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setLocationRelativeTo(null);
        window.pack();
        window.setVisible(true);
    }

    private static void initialRendering() {
        String uniqueTitle = "New Tab 1";
        TabManager.addANewTab(uniqueTitle, null);
    }


    public static void startRendering(Problem problem) throws Exception {
        String uniqueTitle = parseProblem(problem);
        TabManager.addANewTab(uniqueTitle, problem);
    }

    private static String parseProblem(Problem problem) throws Exception {
        String url = problem.getUrl();
        Source source = Source.parseSourceFromURL(url);
        if(Objects.isNull(source)) {
            throw new Exception("Source not found");
        }
        StringBuilder sb = new StringBuilder();
        parseOtherUrlParams(sb, source, url);
        return sb.toString();
    }

    private static void parseOtherUrlParams(StringBuilder sb, Source source, String url) {
        switch(source) {
            case CODEFORCES -> PlatformParser.CodeforcesParser.createCodeforcesTitle(sb, source, url);
            case ATCODER -> PlatformParser.AtcoderParser.createAtcoderTitle(sb, source, url);
        }
    }

    public static void renderMessage(String message) {
        TerminalPanel terminalPanel;
        terminalPanel = (TerminalPanel) TabManager.currentActiveTabPanel().getTerminalPanel();
        terminalPanel.displayMessage(message);
    }
}
