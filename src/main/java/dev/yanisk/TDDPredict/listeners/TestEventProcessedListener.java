package dev.yanisk.TDDPredict.listeners;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import dev.yanisk.TDDPredict.bus.TestRunEventBus;
import dev.yanisk.TDDPredict.models.Prediction;
import dev.yanisk.TDDPredict.models.TestRun;
import dev.yanisk.TDDPredict.models.TestRunOutcome;
import dev.yanisk.TDDPredict.util.Constants;

@Service
public final class TestEventProcessedListener implements TestRunEventBus {

    private final Project project;

    public TestEventProcessedListener(Project project) {
        this.project = project;
        project.getMessageBus().connect().subscribe(TestRunEventBus.TEST_RUN_EVENT_BUS, this);
    }

    @Override
    public void testRunProcessed(TestRun testRun) {
        incrementPredictionCounter(testRun.getPrediction());
        incrementTestRunOutcomeCounter(testRun.getTestRunOutcome());
    }


    private void incrementPredictionCounter(Prediction prediction) {
        if(prediction == Prediction.CORRECT) {
            project.getUserData(Constants.PREDICT_CORRECT_COUNTER).incrementCount();
        } else {
            project.getUserData(Constants.PREDICT_INCORRECT_COUNTER).incrementCount();
        }
    }

    private void incrementTestRunOutcomeCounter(TestRunOutcome testRunOutcome) {
        if(testRunOutcome == TestRunOutcome.PASSED) {
            project.getUserData(Constants.TEST_PASS_COUNTER).incrementCount();
        } else if(testRunOutcome == TestRunOutcome.FAILED) {
            project.getUserData(Constants.TEST_FAIL_COUNTER).incrementCount();
        } else {
            project.getUserData(Constants.TEST_TERMINATION_COUNTER).incrementCount();
        }
    }
}