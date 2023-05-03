package com.example.TDD.view;

import com.example.TDD.state.TDDPredictStateComponent;
import com.example.TDD.util.Constants;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBCheckBox;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SettingsDialog extends DialogWrapper {
    private final Project project;

    public SettingsDialog(Project project) {
        super(true);
        this.setTitle("TDD Predict Settings");
        this.project = project;
        init();
    }


    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());

        JBCheckBox gitCheckbox = new JBCheckBox();
        gitCheckbox.setText("Git Commit");
        gitCheckbox.addActionListener(e -> {
            System.out.println(e.getActionCommand());
        });

        dialogPanel.add(gitCheckbox, BorderLayout.NORTH);
        dialogPanel.add(createDeleteDataButton(), BorderLayout.CENTER);



        return dialogPanel;
    }

    private JPanel createDeleteDataButton() {
        JPanel buttonsBottomPanel = new JPanel(new GridLayout(1, 1));

        JButton deleteDataButton = createButton("Delete Data", e -> {
            Dialog dialog = new Dialog("Are you sure?","This will delete all data for this project.");

            if(dialog.showAndGet()) {
                project.getService(TDDPredictStateComponent.class).clearTestHistory();
                project.getUserData(Constants.HISTORY_PANEL).removeHistory();
                project.getUserData(Constants.TEST_PASS_COUNTER).setCount(0);
                project.getUserData(Constants.TEST_FAIL_COUNTER).setCount(0);
                project.getUserData(Constants.TEST_TERMINATION_COUNTER).setCount(0);
                project.getUserData(Constants.PREDICT_CORRECT_COUNTER).setCount(0);
                project.getUserData(Constants.PREDICT_INCORRECT_COUNTER).setCount(0);
            }
        });

        buttonsBottomPanel.add(deleteDataButton);

        return buttonsBottomPanel;
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }
}
