package com.example.TDD.service;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;

@Service(Service.Level.PROJECT)
public final class GitService {
    private final Project myProject;

    private boolean continuousCommitEnabled;
    private String currentCommit;

    public GitService(Project project) {
        myProject = project;
    }

    public void setCurrentCommit(String currentCommit) {
        this.currentCommit = currentCommit;
    }

    public String getCurrentCommit() {
        return currentCommit;
    }

    public boolean isContinuousCommitEnabled() {
        return continuousCommitEnabled;
    }

    public void setContinuousCommitEnabled(boolean continuousCommitEnabled) {
        this.continuousCommitEnabled = continuousCommitEnabled;
    }
}
