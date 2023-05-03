package dev.yanisk.TDDPredict.view;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;

import javax.swing.*;

public class MainTddPanel extends JBPanel {
    private final Project project;

    public MainTddPanel(Project project) {
        this.project = project;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(new ActionPanel(project));
        add(new SummaryPanel(project));
        add(createHistoryScrollPane());
        add(createSettingsButton());
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