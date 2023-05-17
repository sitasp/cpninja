package org.example.module.render;

import org.apache.commons.lang3.StringUtils;
import org.example.component.TabManager;
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
        window.setSize(1200, 1080);
        window.setLocationRelativeTo(null);

//        TabManager tabManager = new TabManager();
        JTabbedPane tabPane = TabManager.getTabPane();
        window.add(tabPane, BorderLayout.CENTER);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.pack();
        window.setVisible(true);
        UIRender.initialRendering();
    }

    private static void initialRendering() {
        String uniqueTitle = "New Tab 1";
        TabManager.addANewTab(uniqueTitle);
    }


    public static void startRendering(Problem problem) throws Exception {
        String uniqueTitle = parseProblem(problem);
        TabManager.addANewTab(uniqueTitle);
        propagateTheDetails(TabManager.currentActiveTabPanel(), problem);
    }

    private static void propagateTheDetails(MainPanel currentActiveTabPanel, Problem problem) {
        fillExtraDetailsInCodePanel(currentActiveTabPanel.getCodePanel(), problem);
    }

    private static void fillExtraDetailsInCodePanel(JPanel codePanel, Problem problem) {
        addCommentToCodePanel("Problem: " + problem.getName());
        addCommentToCodePanel("Contest: " + problem.getGroup());
        addCommentToCodePanel("URL: " + problem.getUrl());
        addCommentToCodePanel("Memory Limit: " + problem.getMemoryLimit());
        addCommentToCodePanel("Time Limit: " + problem.getTimeLimit());
        addCommentToCodePanel("CP Ninja");
    }

    private static void addCommentToCodePanel(String commentToBeAdded) {
        Language language = Language.findEnumByValue(NinjaConstants.DEFAULT_LANGUAGE);
        String cmnt = "";
        if (Objects.requireNonNull(language) == Language.JAVA) {
            cmnt = Java.addComments(commentToBeAdded);
        }
        CodePanel codePanel;
        codePanel = (CodePanel) TabManager.currentActiveTabPanel().getCodePanel();
        codePanel.getCodeArea().append(cmnt);
    }

    private static String parseProblem(Problem problem) throws Exception {
        String url = problem.getUrl();
        Source source = Source.parseSourceFromURL(url);
        if(Objects.isNull(source)) {
            throw new Exception("Source not found");
        }
        StringBuilder sb = new StringBuilder(source.getTiny());
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
