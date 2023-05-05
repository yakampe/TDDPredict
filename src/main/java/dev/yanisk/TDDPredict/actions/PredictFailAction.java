package dev.yanisk.TDDPredict.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import static dev.yanisk.TDDPredict.util.PredictionHelper.greenPredictCalled;
import static dev.yanisk.TDDPredict.util.PredictionHelper.redPredictCalled;

public class PredictFailAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        redPredictCalled(e.getProject());
    }
}
