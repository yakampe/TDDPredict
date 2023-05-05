package dev.yanisk.TDDPredict.view;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBPanel;
import dev.yanisk.TDDPredict.state.SettingsStateComponent;
import dev.yanisk.TDDPredict.state.TDDPredictStateComponent;
import dev.yanisk.TDDPredict.util.Constants;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
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
        JBPanel dialogPanel = new JBPanel(new BorderLayout());

        SettingsStateComponent settingsStateComponent = project.getService(SettingsStateComponent.class);

        JBCheckBox gitCheckbox = new JBCheckBox();
        gitCheckbox.setText("Disable Prediction Popup");
        gitCheckbox.setSelected(settingsStateComponent.getState().predictionPopupDisabled);

        gitCheckbox.addActionListener(e -> {
            if(gitCheckbox.isSelected()) {
                settingsStateComponent.getState().predictionPopupDisabled = true;
            } else {
                settingsStateComponent.getState().predictionPopupDisabled = false;
            }
        });

        dialogPanel.add(gitCheckbox, BorderLayout.NORTH);
        dialogPanel.add(createDeleteDataButton(), BorderLayout.CENTER);



        return dialogPanel;
    }

    private JBPanel createDeleteDataButton() {
        JBPanel buttonsBottomPanel = new JBPanel(new GridLayout(1, 1));

        JButton deleteDataButton = createButton("Delete Data", e -> {
            dev.yanisk.TDDPredict.view.Dialog dialog = new Dialog("Are you sure?","This will delete all data for this project.");

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
