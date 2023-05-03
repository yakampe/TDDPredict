package com.example.TDD.view;

import com.example.TDD.models.Prediction;
import com.example.TDD.models.TestRunOutcome;
import com.example.TDD.state.TDDPredictStateComponent;
import com.example.TDD.util.Constants;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;

import javax.swing.*;
import java.awt.*;

import static com.example.TDD.util.ButtonColors.*;

public class TestOutcomePanel extends JBPanel {

    private final int circleSize = 14;
    private final Project project;
    private CounterLabel testsPassedCounterLabel;
    private CounterLabel testsFailedCounterLabel;
    private CounterLabel testNotExecutedCounterLabel;

    public TestOutcomePanel(Project project) {
        this.project = project;

        loadCounterLabels();
        loadCounterData();

        setLayout(new GridLayout(1, 3, 5, 5));
        add(createCircleWithCounter(TEST_PASS_COLOR, testsPassedCounterLabel, "Tests Passed"));
        add(createCircleWithCounter(TEST_FAIL_COLOR, testsFailedCounterLabel, "Tests Failed"));
        add(createCircleWithCounter(TEST_DID_NOT_RUN_COLOR, testNotExecutedCounterLabel, "Tests did not run after prediction"));

    }

    private void loadCounterLabels() {
        testsPassedCounterLabel = project.getUserData(Constants.TEST_PASS_COUNTER);
        testsFailedCounterLabel = project.getUserData(Constants.TEST_FAIL_COUNTER);
        testNotExecutedCounterLabel = project.getUserData(Constants.TEST_TERMINATION_COUNTER);
    }


    private JPanel createCircleWithCounter(Color color, CounterLabel counterLabel, String tooltip) {
        JPanel circleWithCounterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        Circle circle = new Circle(color, circleSize, tooltip);
        circleWithCounterPanel.add(circle);
        circleWithCounterPanel.add(counterLabel);

        return circleWithCounterPanel;
    }

    private void loadCounterData() {
        testsPassedCounterLabel.setCount(calculateOutcomes(TestRunOutcome.PASSED));
        testsFailedCounterLabel.setCount(calculateOutcomes(TestRunOutcome.FAILED));
        testNotExecutedCounterLabel.setCount(calculateOutcomes(TestRunOutcome.NOT_EXECUTED));
    }

    private long calculateOutcomes(TestRunOutcome outcome) {
        return project.getService(TDDPredictStateComponent.class).getTestHistory()
                .stream()
                .filter(testRun -> testRun.getTestRunOutcome() == outcome)
                .count();
    }
}
