package com.example.TDD.models;

import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Tag;

@Tag("testRun")
public class TestRun {
    @Attribute("testRunOutcome")
    private TestRunOutcome testRunOutcome;

    @Attribute("guess")
    private Guess guess;

    @Attribute("dateTime")
    private String dateTime;

    @Attribute("commit")
    private String commit;

    @Attribute("prediction")
    private Prediction prediction;

    public TestRun() {
    }

    public TestRun(TestRunOutcome testRunOutcome, Guess guess, String dateTime, String commit, Prediction prediction) {
        this.testRunOutcome = testRunOutcome;
        this.guess = guess;
        this.dateTime = dateTime;
        this.commit = commit;
        this.prediction = prediction;
    }

    public void setOutcome(TestRunOutcome testRunOutcome) {
        this.testRunOutcome = testRunOutcome;
        derivePrediction(testRunOutcome);
    }

    private void derivePrediction(TestRunOutcome testRunOutcome) {
        switch (testRunOutcome){
            case PASSED:
                if (guess == Guess.PASS) {
                    setPrediction(Prediction.CORRECT);
                }
                break;
            case FAILED:
                if (guess == Guess.FAIL) {
                    setPrediction(Prediction.CORRECT);
                }
                break;
            case NOT_EXECUTED:
                if (guess == Guess.NOT_EXECUTED) {
                    setPrediction(Prediction.CORRECT);
                }
                break;
            default:
                setPrediction(Prediction.INCORRECT);
        }
    }

    public TestRunOutcome getTestRunOutcome() {
        return testRunOutcome;
    }

    public void setTestRunOutcome(TestRunOutcome testRunOutcome) {
        this.testRunOutcome = testRunOutcome;
    }

    public Guess getGuess() {
        return guess;
    }

    public void setGuess(Guess guess) {
        this.guess = guess;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }

    @Override
    public String toString() {
        return "TestRun{" +
                "testRunOutcome=" + testRunOutcome +
                ", guess=" + guess +
                ", dateTime='" + dateTime + '\'' +
                ", prediction=" + prediction +
                '}';
    }


    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getCommit() {
        return commit;
    }
}
