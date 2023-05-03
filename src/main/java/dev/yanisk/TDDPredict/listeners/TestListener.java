package com.example.TDD.listeners;

import com.example.TDD.models.TestRunOutcome;
import com.example.TDD.state.TDDPredictStateComponent;
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
    public void onTestingStarted(SMTestProxy.@NotNull SMRootTestProxy testsRoot) {

    }


    @Override
    public void onTestingFinished(SMTestProxy.@NotNull SMRootTestProxy testsRoot) {
        TDDPredictStateComponent tddPredictStateComponent = project.getService(TDDPredictStateComponent.class);

        if(tddPredictStateComponent.getTestHistory().size() > 0) {
            TestRunOutcome testRunOutcome = testsRoot.isPassed() ? TestRunOutcome.PASSED : TestRunOutcome.FAILED;
            tddPredictStateComponent.recordTestOutcome(testRunOutcome);
        }
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
