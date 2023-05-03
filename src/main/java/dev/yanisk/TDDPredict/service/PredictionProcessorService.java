package dev.yanisk.TDDPredict.service;

import com.intellij.openapi.components.Service;
import dev.yanisk.TDDPredict.bus.TestRunEventPublisherService;
import dev.yanisk.TDDPredict.models.*;
import dev.yanisk.TDDPredict.state.TDDPredictStateComponent;
import com.intellij.openapi.project.Project;

@Service(Service.Level.PROJECT)
public final class PredictionProcessorService {

    private final Project project;

    public PredictionProcessorService(Project project) {
        this.project = project;
    }

    public void processPrediction(ProcessOutcome processOutcome) {
        TDDPredictStateComponent tddPredictStateComponent = project.getService(TDDPredictStateComponent.class);
        TestRun latestTest = tddPredictStateComponent.getLatestTest();

        if(latestTest.getTestRunOutcome() != null) {
            return;
        }

        latestTest.setPrediction(Prediction.INCORRECT);

        switch (processOutcome) {
            case TEST_PASS:
                latestTest.setTestRunOutcome(TestRunOutcome.PASSED);
                if(latestTest.getGuess() == Guess.PASS) {
                    latestTest.setPrediction(Prediction.CORRECT);
                }
                break;

            case TEST_FAILED:
                latestTest.setTestRunOutcome(TestRunOutcome.FAILED);
                if(latestTest.getGuess() == Guess.FAIL) {
                    latestTest.setPrediction(Prediction.CORRECT);
                }
                break;

            case TEST_TERMINATED:
                latestTest.setTestRunOutcome(TestRunOutcome.NOT_EXECUTED);
                break;

        }

        project.getService(GitService.class).addCommitNumberToTestRun(latestTest);
        project.getService(TestRunEventPublisherService.class).publishTestRunProcessed(latestTest);
    }
}
