package dev.yanisk.TDDPredict.view;

import dev.yanisk.TDDPredict.models.TestRun;
import dev.yanisk.TDDPredict.state.TDDPredictStateComponent;
import dev.yanisk.TDDPredict.util.Constants;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;

import java.awt.*;
import java.util.LinkedList;

public class ScrollableHistoryList extends JBPanel {

    public ScrollableHistoryList(Project project) {

        HistoryList historyList = project.getUserData(Constants.HISTORY_PANEL);
        LinkedList<TestRun> testHistory = project.getService(TDDPredictStateComponent.class).getTestHistory();
        historyList.addHistory(testHistory);

        JBScrollPane scrollPanel = new JBScrollPane(historyList);
        scrollPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPanel.setPreferredSize(new Dimension(150, 120));
        scrollPanel.setMinimumSize(new Dimension(150, 120));
        setToolTipText(null);

        add(scrollPanel);
    }
}
