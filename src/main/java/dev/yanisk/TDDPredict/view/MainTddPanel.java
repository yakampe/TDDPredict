package dev.yanisk.TDDPredict.view;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;

import javax.swing.*;
import java.awt.*;

public class MainTddPanel extends JBPanel {
    private final Project project;

    public MainTddPanel(Project project) {
        this.project = project;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(createAlignedPanel(new ActionPanel(project)));
        add(createAlignedPanel(new SummaryPanel(project)));
        add(createAlignedPanel(createHistoryScrollPane()));
        add(createAlignedPanel(createSettingsButton()));
    }

    private JPanel createAlignedPanel(JComponent component) {
        return new JPanel() {
            {
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                add(component);
                setAlignmentY(TOP_ALIGNMENT);
            }

            @Override
            public Dimension getMaximumSize() {
                Dimension max = super.getMaximumSize();
                max.height = getPreferredSize().height;
                return max;
            }
        };
    }




    private JButton createSettingsButton() {
        JButton settingsButton = new JButton("Settings", AllIcons.General.Settings);
        settingsButton.addActionListener(e -> {
            SettingsDialog settingsDialog = new SettingsDialog(project);
            settingsDialog.show();
        });

        settingsButton.setAlignmentX(CENTER_ALIGNMENT);

        return settingsButton;
    }


    private JBPanel createHistoryScrollPane() {



        // Wrap the historyScrollPane in a custom JPanel to control the width and height
        ScrollableHistoryList wrapper = new ScrollableHistoryList(project);

        // Set the maximum height of the wrapper panel



        return wrapper;
    }





}