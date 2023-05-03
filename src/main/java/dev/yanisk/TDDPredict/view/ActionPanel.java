package dev.yanisk.TDDPredict.view;

import com.intellij.ui.components.JBPanel;
import dev.yanisk.TDDPredict.util.PredictionHelper;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ActionPanel extends JBPanel {
    private final Project project;

    public ActionPanel(Project project) {
        this.project = project;
        JBPanel northPanel = new JBPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(createPredictLabel());
        northPanel.add(createButtonsPanel());
        this.add(northPanel);
    }

    private JLabel createPredictLabel() {
        JLabel predictLabel = new JLabel("Predict");
        predictLabel.setHorizontalAlignment(SwingConstants.CENTER);
        predictLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return predictLabel;
    }

    private JBPanel createButtonsPanel() {
        JBPanel buttonsPanel = new JBPanel(new GridLayout(1, 2));
        String passButtonText = "Pass  "; //Added padding because doesn't look centered
        JButton predictPassButton = createButton(passButtonText, AllIcons.RunConfigurations.TestPassed,e -> {
            PredictionHelper.greenPredictCalled(project);
        });

        String failButtonText = "Fail  "; //Added padding because doesn't look centered
        JButton predictFailButton = createButton(failButtonText,AllIcons.RunConfigurations.TestError, e -> {
            PredictionHelper.redPredictCalled(project);
        });
//        JButton predictExecutionFailButton = createButton("BREAK", e -> {
//            PredictionHelper.terminatePredictCalled(project);
//        });

        buttonsPanel.add(predictPassButton);
        buttonsPanel.add(predictFailButton);
//        buttonsPanel.add(predictExecutionFailButton);

        return buttonsPanel;
    }


    private JButton createButton(String text, Icon icon, ActionListener listener) {
        JButton button = new JButton(text, icon);

        button.addActionListener(listener);
        return button;
    }
}
