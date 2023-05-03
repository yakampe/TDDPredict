package com.example.TDD.listeners;

import com.example.TDD.models.TestRun;
import com.example.TDD.models.TestRunOutcome;
import com.example.TDD.state.TDDPredictStateComponent;
import com.intellij.execution.ExecutionListener;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ProcessListener implements ExecutionListener {
    private final Project project;

    public ProcessListener(Project project) {
        this.project = project;
    }

    @Override
    public void processNotStarted(@NotNull String executorId, @NotNull ExecutionEnvironment env, Throwable cause) {
        TDDPredictStateComponent tddPredictStateComponent = project.getService(TDDPredictStateComponent.class);

        if(tddPredictStateComponent.getTestHistory().size() > 0) {
            TestRun lastTestRun = tddPredictStateComponent.getTestHistory().getFirst();
            if (lastTestRun.getPrediction() == null) {
                tddPredictStateComponent.recordTestOutcome(TestRunOutcome.NOT_EXECUTED);
            }
        }

    }

    @Override
    public void processTerminated(@NotNull RunProfile runProfile, @NotNull ProcessHandler handler) {
        if (handler.getExitCode() != null && handler.getExitCode() > 0) {
            TDDPredictStateComponent tddPredictStateComponent = project.getService(TDDPredictStateComponent.class);
            if(tddPredictStateComponent.getTestHistory().size() > 0) {
                TestRun lastTestRun = tddPredictStateComponent.getTestHistory().getFirst();
                if (lastTestRun.getPrediction() == null) {
                    tddPredictStateComponent.recordTestOutcome(TestRunOutcome.NOT_EXECUTED);
                }
            }
        }
    }

}