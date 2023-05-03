package com.example.TDD.state;

import com.example.TDD.service.GitService;
import com.example.TDD.util.Constants;
import com.example.TDD.models.Guess;
import com.example.TDD.models.Prediction;
import com.example.TDD.models.TestRun;
import com.example.TDD.models.TestRunOutcome;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.LinkedList;

@State(name = "TDDPredictState", storages = {@Storage(StoragePathMacros.WORKSPACE_FILE)})
public class TDDPredictStateComponent implements PersistentStateComponent<TDDPredictState> {
    private TDDPredictState state;
    private Project project;

    public TDDPredictStateComponent(Project project) {
        this.project = project;
        state = new TDDPredictState();
    }

    @Nullable
    @Override
    public TDDPredictState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull TDDPredictState state) {
        this.state = state;
    }

    public void recordTestOutcome(TestRunOutcome testRunOutcome) {
        TestRun lastTestRun = state.testRunHistory.getFirst();

        String currentCommit = project.getService(GitService.class).getCurrentCommit();

        if(lastTestRun.getTestRunOutcome() == null) {
            lastTestRun.setOutcome(testRunOutcome);
            lastTestRun.setCommit(currentCommit);


            incrementTestRunOutcomeCounter(testRunOutcome);
            incrementPredictionCounter(lastTestRun);
            addToHistoryPanel(lastTestRun);
        }
    }

    private void addToHistoryPanel(TestRun lastTestRun) {
        project.getUserData(Constants.HISTORY_PANEL).addTestRun(lastTestRun);
    }

    private void incrementPredictionCounter(TestRun lastTestRun) {
        if(lastTestRun.getPrediction() == Prediction.CORRECT) {
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

    public void addGuess(Guess guess) {
        TestRun testRun = new TestRun(null,guess,LocalDateTime.now().toString(),null, null);
        state.testRunHistory.addFirst(testRun);
    }

    public LinkedList<TestRun> getTestHistory() {
        return new LinkedList<>(state.testRunHistory);
    }

    public void clearTestHistory() {
        state.testRunHistory.clear();
    }

    public void updateTestRun(TestRun testRun) {
        throw new UnsupportedOperationException();
    }
}