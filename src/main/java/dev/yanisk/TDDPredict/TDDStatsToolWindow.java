package dev.yanisk.TDDPredict;

import com.intellij.ui.components.JBPanel;
import dev.yanisk.TDDPredict.listeners.TestEventProcessedListener;
import dev.yanisk.TDDPredict.util.Constants;
import dev.yanisk.TDDPredict.view.CounterLabel;
import dev.yanisk.TDDPredict.view.HistoryList;
import dev.yanisk.TDDPredict.view.MainTddPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class TDDStatsToolWindow implements ToolWindowFactory {

    private Project project;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        JBPanel panel = new JBPanel();
        this.project = project;


        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(panel, "", false);

        toolWindow.getContentManager().addContent(content);

        generateTemplate(panel);

        project.getService(TestEventProcessedListener.class);
    }

    private void generateTemplate(JBPanel panel) {
        project.putUserData(Constants.TEST_PASS_COUNTER, new CounterLabel());
        project.putUserData(Constants.TEST_FAIL_COUNTER, new CounterLabel());
        project.putUserData(Constants.PREDICT_CORRECT_COUNTER, new CounterLabel());
        project.putUserData(Constants.PREDICT_INCORRECT_COUNTER, new CounterLabel());
        project.putUserData(Constants.TEST_TERMINATION_COUNTER, new CounterLabel());
        project.putUserData(Constants.HISTORY_PANEL, new HistoryList(project));
        JBPanel mainPanel = new MainTddPanel(project);
        panel.add(mainPanel, BorderLayout.NORTH);
    }
}
