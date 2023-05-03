package com.example.TDD.util;

import com.example.TDD.models.Guess;
import com.example.TDD.state.TDDPredictStateComponent;
import com.example.TDD.view.Dialog;
import com.intellij.execution.ProgramRunnerUtil;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.openapi.project.Project;

public class PredictionHelper {

    public static void greenPredictCalled(Project project) {
        Dialog dialog = new Dialog("Predicting PASS","Predicting tests will PASS!");
        if(dialog.showAndGet()) {
            persistGuessToStateStorage(project, Guess.PASS);
            executeSelectedConfiguration(project);
        }
    }

    public static void redPredictCalled(Project project) {
        Dialog dialog = new Dialog("Predicting FAIL","Predicting tests will FAIL!");
        if(dialog.showAndGet()) {
            persistGuessToStateStorage(project, Guess.FAIL);
            executeSelectedConfiguration(project);
        }
    }

    public static void terminatePredictCalled(Project project) {
        Dialog dialog = new Dialog("Predicting BREAK","Predicting app BREAKS! and no tests are run!");
        if(dialog.showAndGet()) {
            persistGuessToStateStorage(project, Guess.NOT_EXECUTED);
            executeSelectedConfiguration(project);
        }
    }

    private static void persistGuessToStateStorage(Project project, Guess guess) {
        TDDPredictStateComponent tddPredictStateComponent = project.getService(TDDPredictStateComponent.class);
        tddPredictStateComponent.addGuess(guess);
    }

    private static void executeSelectedConfiguration(Project project) {
        ProgramRunnerUtil.executeConfiguration(project, getConfiguration(project), DefaultRunExecutor.getRunExecutorInstance());
    }




    private static RunnerAndConfigurationSettings getConfiguration(Project project) {
        RunManager runManager = RunManager.getInstance(project);

        RunnerAndConfigurationSettings selectedConfiguration = runManager.getSelectedConfiguration();

        return selectedConfiguration;
    }


}
