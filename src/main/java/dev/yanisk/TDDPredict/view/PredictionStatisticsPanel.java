package dev.yanisk.TDDPredict.view;

import com.intellij.ui.components.JBLabel;
import dev.yanisk.TDDPredict.models.Prediction;
import dev.yanisk.TDDPredict.state.TDDPredictStateComponent;
import dev.yanisk.TDDPredict.util.Constants;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;

import javax.swing.*;
import java.awt.*;

public class PredictionStatisticsPanel extends JBPanel {

    private final Project project;
    private CounterLabel incorrectGuessCounterLabel;
    private CounterLabel correctGuessCounterLabel;

    public PredictionStatisticsPanel(Project project) {
        this.project = project;
        loadCounterLabels();
        loadCounterData();

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        createCorrectPredictionsPanel();
        createIncorrectPredictionsPanel();
    }

    private void createIncorrectPredictionsPanel() {
        JBPanel incorrectPredictionsPanel = new JBPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JBLabel incorrectPredictions = new JBLabel(AllIcons.RunConfigurations.TestFailed);
        incorrectPredictions.setToolTipText("Incorrect Predictions");
        incorrectPredictionsPanel.add(incorrectPredictions);
        incorrectPredictionsPanel.add(incorrectGuessCounterLabel);
        add(incorrectPredictionsPanel);
    }

    private void createCorrectPredictionsPanel() {
        JBPanel correctPredictionsPanel = new JBPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JBLabel correctPredictions = new JBLabel(AllIcons.RunConfigurations.TestPassed);
        correctPredictions.setToolTipText("Correct Predictions");
        correctPredictionsPanel.add(correctPredictions);
        correctPredictionsPanel.add(correctGuessCounterLabel);
        add(correctPredictionsPanel);
    }

    private void loadCounterLabels() {
        correctGuessCounterLabel = project.getUserData(Constants.PREDICT_CORRECT_COUNTER);
        incorrectGuessCounterLabel = project.getUserData(Constants.PREDICT_INCORRECT_COUNTER);
    }

    private void loadCounterData() {
        incorrectGuessCounterLabel.setCount(calculateGuesses(Prediction.INCORRECT));
        correctGuessCounterLabel.setCount(calculateGuesses(Prediction.CORRECT));
    }

    private long calculateGuesses(Prediction correct) {
        return project.getService(TDDPredictStateComponent.class).getTestHistory()
                .stream()
                .filter(testRun -> testRun.getPrediction() == correct)
                .count();
    }
}
