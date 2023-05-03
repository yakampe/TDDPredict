package com.example.TDD.view;

import com.example.TDD.models.Prediction;
import com.example.TDD.state.TDDPredictStateComponent;
import com.example.TDD.util.Constants;
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
        JPanel incorrectPredictionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel incorrectPredictions = new JLabel(AllIcons.RunConfigurations.TestFailed);
        incorrectPredictions.setToolTipText("Incorrect Predictions");
        incorrectPredictionsPanel.add(incorrectPredictions);
        incorrectPredictionsPanel.add(incorrectGuessCounterLabel);
        add(incorrectPredictionsPanel);
    }

    private void createCorrectPredictionsPanel() {
        JPanel correctPredictionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel correctPredictions = new JLabel(AllIcons.RunConfigurations.TestPassed);
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
