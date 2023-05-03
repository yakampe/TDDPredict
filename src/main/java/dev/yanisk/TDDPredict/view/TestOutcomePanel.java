package dev.yanisk.TDDPredict.view;

import dev.yanisk.TDDPredict.models.TestRunOutcome;
import dev.yanisk.TDDPredict.state.TDDPredictStateComponent;
import dev.yanisk.TDDPredict.util.Constants;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;
import dev.yanisk.TDDPredict.util.ButtonColors;

import java.awt.*;

public class TestOutcomePanel extends JBPanel {

    private final Project project;
    private CounterLabel testsPassedCounterLabel;
    private CounterLabel testsFailedCounterLabel;
    private CounterLabel testNotExecutedCounterLabel;

    public TestOutcomePanel(Project project) {
        this.project = project;

        loadCounterLabels();
        loadCounterData();

        setLayout(new GridLayout(1, 3, 5, 5));
        add(createCircleWithCounter(ButtonColors.TEST_PASS_COLOR, testsPassedCounterLabel, "Tests Passed"));
        add(createCircleWithCounter(ButtonColors.TEST_FAIL_COLOR, testsFailedCounterLabel, "Tests Failed"));
//        add(createCircleWithCounter(ButtonColors.TEST_DID_NOT_RUN_COLOR, testNotExecutedCounterLabel, "Tests did not run after prediction"));

    }

    private void loadCounterLabels() {
        testsPassedCounterLabel = project.getUserData(Constants.TEST_PASS_COUNTER);
        testsFailedCounterLabel = project.getUserData(Constants.TEST_FAIL_COUNTER);
        testNotExecutedCounterLabel = project.getUserData(Constants.TEST_TERMINATION_COUNTER);
    }


    private JBPanel createCircleWithCounter(Color color, CounterLabel counterLabel, String tooltip) {
        JBPanel circleWithCounterPanel = new JBPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

        int circleSize = 14;
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
