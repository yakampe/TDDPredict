package com.example.TDD.view;

import com.example.TDD.models.TestRun;
import com.example.TDD.state.TDDPredictStateComponent;
import com.example.TDD.util.Constants;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;

import java.awt.*;
import java.util.LinkedList;

public class ScrollableHistoryList extends JBPanel {

    public ScrollableHistoryList(Project project) {
//        super(new BorderLayout());



        HistoryList historyList = project.getUserData(Constants.HISTORY_PANEL);
        LinkedList<TestRun> testHistory = project.getService(TDDPredictStateComponent.class).getTestHistory();
        historyList.addHistory(testHistory);

        // Create a JBScrollPane, set its preferred size, and add the historyList to it
        JBScrollPane scrollPanel = new JBScrollPane(historyList);
        scrollPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPanel.setPreferredSize(new Dimension(120, 160));
        setToolTipText(null);

        // Add the scrollPanel to the current panel
        add(scrollPanel);
    }
}
