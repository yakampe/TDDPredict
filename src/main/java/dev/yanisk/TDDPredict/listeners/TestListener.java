package dev.yanisk.TDDPredict.listeners;

import dev.yanisk.TDDPredict.models.ProcessOutcome;
import dev.yanisk.TDDPredict.service.PredictionProcessorService;
import dev.yanisk.TDDPredict.state.TDDPredictStateComponent;
import com.intellij.execution.testframework.sm.runner.SMTRunnerEventsListener;
import com.intellij.execution.testframework.sm.runner.SMTestProxy;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TestListener implements SMTRunnerEventsListener {
    private final Project project;

    public TestListener(Project project) {
        this.project = project;
    }

    @Override
    public void onTestingFinished(SMTestProxy.@NotNull SMRootTestProxy testsRoot) {
        TDDPredictStateComponent tddPredictStateComponent = project.getService(TDDPredictStateComponent.class);

        if(tddPredictStateComponent.getTestHistory().size() > 0) {
            ProcessOutcome processOutcome = testsRoot.isPassed() ? ProcessOutcome.TEST_PASS : ProcessOutcome.TEST_FAILED;

            PredictionProcessorService predictionProcessorService = project.getService(PredictionProcessorService.class);
            predictionProcessorService.processPrediction(processOutcome);
        }
    }

    @Override
    public void onTestingStarted(SMTestProxy.@NotNull SMRootTestProxy testsRoot) {

    }

    @Override
    public void onTestsCountInSuite(int count) {

    }

    @Override
    public void onTestStarted(@NotNull SMTestProxy test) {

    }

    @Override
    public void onTestFinished(@NotNull SMTestProxy test) {

    }

    @Override
    public void onTestFailed(@NotNull SMTestProxy test) {

    }

    @Override
    public void onTestIgnored(@NotNull SMTestProxy test) {

    }

    @Override
    public void onSuiteFinished(@NotNull SMTestProxy suite) {

    }

    @Override
    public void onSuiteStarted(@NotNull SMTestProxy suite) {

    }

    @Override
    public void onCustomProgressTestsCategory(@Nullable String categoryName, int testCount) {

    }

    @Override
    public void onCustomProgressTestStarted() {

    }

    @Override
    public void onCustomProgressTestFailed() {

    }

    @Override
    public void onCustomProgressTestFinished() {

    }

    @Override
    public void onSuiteTreeNodeAdded(SMTestProxy testProxy) {

    }

    @Override
    public void onSuiteTreeStarted(SMTestProxy suite) {

    }
}
