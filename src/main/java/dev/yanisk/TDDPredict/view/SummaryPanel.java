package com.example.TDD.view;

import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {

    private Project project;

    public SummaryPanel(Project project) {
        this.project = project;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addComponentsToPanel();
    }


    private void addComponentsToPanel() {
        // Test Outcomes
        add(createTitleLabel("Test Outcomes"));
        add(new TestOutcomePanel(project));
        add(new JSeparator(SwingConstants.HORIZONTAL));

        // Prediction Statistics
        add(createTitleLabel("Prediction Statistics"));
        add(new PredictionStatisticsPanel(project));
    }

    private JLabel createTitleLabel(String title) {
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }

}