package dev.yanisk.TDDPredict.bus;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import dev.yanisk.TDDPredict.models.TestRun;

@Service(Service.Level.PROJECT)
public final class TestRunEventPublisherService {
    private final Project project;

    public TestRunEventPublisherService(Project project) {
        this.project = project;
    }

    public void publishTestRunProcessed(TestRun processedTestRun) {
        project.getMessageBus().syncPublisher(TestRunEventBus.TEST_RUN_EVENT_BUS).testRunProcessed(processedTestRun);
    }
}
