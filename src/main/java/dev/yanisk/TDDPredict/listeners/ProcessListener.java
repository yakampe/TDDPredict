package dev.yanisk.TDDPredict.listeners;

import dev.yanisk.TDDPredict.models.ProcessOutcome;
import dev.yanisk.TDDPredict.models.TestRun;
import dev.yanisk.TDDPredict.service.PredictionProcessorService;
import dev.yanisk.TDDPredict.state.TDDPredictStateComponent;
import com.intellij.execution.ExecutionListener;
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

        if (tddPredictStateComponent.getTestHistory().size() > 0) {
            TestRun lastTestRun = tddPredictStateComponent.getLatestTest();
            if (lastTestRun.getPrediction() == null) {
                PredictionProcessorService predictionProcessorService = project.getService(PredictionProcessorService.class);
                predictionProcessorService.processPrediction(ProcessOutcome.TEST_FAILED);
            }
        }

    }

//    @Override
//    public void processTerminated(@NotNull RunProfile runProfile, @NotNull ProcessHandler handler) {
//        if (handler.getExitCode() != null && handler.getExitCode() > 0) {
//
//            TDDPredictStateComponent tddPredictStateComponent = project.getService(TDDPredictStateComponent.class);
//
//            if(tddPredictStateComponent.getTestHistory().size() > 0) {
//                PredictionProcessorService predictionProcessorService = project.getService(PredictionProcessorService.class);
//                predictionProcessorService.processPrediction(ProcessOutcome.TEST_TERMINATED);
//            }
//        }
//    }

    /*
    Having problems on determining when a test is terminated. So saying it just fails.

    On a failed test the below will be called and I have not found a way to identify if test has passed
    as the ProcessHandler or RunProfile has no context of test.

     */
    @Override
    public void processTerminated(@NotNull String executorId,
                                  @NotNull ExecutionEnvironment env,
                                  @NotNull ProcessHandler handler,
                                  int exitCode) {
        if (exitCode > 0) {

            TDDPredictStateComponent tddPredictStateComponent = project.getService(TDDPredictStateComponent.class);

            if (tddPredictStateComponent.getTestHistory().size() > 0) {
                PredictionProcessorService predictionProcessorService = project.getService(PredictionProcessorService.class);
                predictionProcessorService.processPrediction(ProcessOutcome.TEST_FAILED);
            }
        }
    }

}