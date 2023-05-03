package dev.yanisk.TDDPredict.view;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class Dialog extends DialogWrapper {


    private final String message;

    public Dialog(String title, String message) {
        super(true); // use current window as parent
        this.message = message;
        setTitle(title);
        init();
    }


    @Override
    protected @Nullable JBPanel createCenterPanel() {
        JBPanel dialogPanel = new JBPanel(new BorderLayout());

        JBLabel label = new JBLabel(message);
        dialogPanel.add(label, BorderLayout.CENTER);

        return dialogPanel;
    }
}
