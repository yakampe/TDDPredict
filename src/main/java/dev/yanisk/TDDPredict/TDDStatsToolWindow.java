package dev.yanisk.TDDPredict;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import dev.yanisk.TDDPredict.listeners.TestEventProcessedListener;
import dev.yanisk.TDDPredict.util.Constants;
import dev.yanisk.TDDPredict.view.CounterLabel;
import dev.yanisk.TDDPredict.view.HistoryList;
import dev.yanisk.TDDPredict.view.MainTddPanel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

public class TDDStatsToolWindow implements ToolWindowFactory {

    private Project project;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        this.project = project;
        generateUserData();

        TDDPredictToolWindowContent tddPredictToolWindowContent = new TDDPredictToolWindowContent();
        Content content = ContentFactory.getInstance().createContent(tddPredictToolWindowContent.getContentPanel(project), "", false);

        toolWindow.getContentManager().addContent(content);
        project.getService(TestEventProcessedListener.class);
    }

    private static class TDDPredictToolWindowContent {
        public JBPanel getContentPanel(Project project) {
            return new MainTddPanel(project);

        }
    }


    private void generateUserData() {
        project.putUserData(Constants.TEST_PASS_COUNTER, new CounterLabel());
        project.putUserData(Constants.TEST_FAIL_COUNTER, new CounterLabel());
        project.putUserData(Constants.PREDICT_CORRECT_COUNTER, new CounterLabel());
        project.putUserData(Constants.PREDICT_INCORRECT_COUNTER, new CounterLabel());
        project.putUserData(Constants.TEST_TERMINATION_COUNTER, new CounterLabel());
        project.putUserData(Constants.HISTORY_PANEL, new HistoryList(project));
    }
}
