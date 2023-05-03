package com.example.TDD;

import com.example.TDD.util.Constants;
import com.example.TDD.view.CounterLabel;
import com.example.TDD.view.HistoryList;
import com.example.TDD.view.MainTddPanel;
import com.example.TDD.state.TDDPredictStateComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class TDDStatsToolWindow implements ToolWindowFactory {

    private Project project;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        JPanel panel = new JPanel();
        this.project = project;


        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(panel, "", false);

        toolWindow.getContentManager().addContent(content);

        generateTemplate(panel);
    }

    private void generateTemplate(JPanel panel) {
        project.putUserData(Constants.TEST_PASS_COUNTER, new CounterLabel());
        project.putUserData(Constants.TEST_FAIL_COUNTER, new CounterLabel());
        project.putUserData(Constants.PREDICT_CORRECT_COUNTER, new CounterLabel());
        project.putUserData(Constants.PREDICT_INCORRECT_COUNTER, new CounterLabel());
        project.putUserData(Constants.TEST_TERMINATION_COUNTER, new CounterLabel());
        project.putUserData(Constants.HISTORY_PANEL, new HistoryList());
        JPanel mainPanel = new MainTddPanel(project);
        panel.add(mainPanel, BorderLayout.NORTH);
    }
}
