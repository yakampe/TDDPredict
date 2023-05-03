package com.example.TDD.service;

import com.example.TDD.models.ProcessOutcome;
import com.example.TDD.state.TDDPredictStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;

@Service(Service.Level.PROJECT)
public class PredictionProcessorService {
    private final TDDPredictStateComponent tddPredictStateComponent;
    public PredictionProcessorService(Project project) {
         tddPredictStateComponent = project.getService(TDDPredictStateComponent.class);
    }

    public void processPrediction(ProcessOutcome testFinished) {

    }

}
