package dev.yanisk.TDDPredict.service;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import dev.yanisk.TDDPredict.models.TestRun;
import git4idea.repo.GitRepository;

@Service(Service.Level.PROJECT)
public final class GitService {

    private boolean continuousCommitEnabled;
    private String currentCommit;

    private GitRepository gitRepository;

    public GitService(Project project) {
    }

    public void setCurrentCommit(String currentCommit) {
        this.currentCommit = currentCommit;
    }

    public String getCurrentCommit() {
        return currentCommit;
    }

    public void addCommitNumberToTestRun(TestRun testRun) {
        testRun.setCommit(getCurrentCommit());
    }

    public GitRepository getGitRepository() {
        return gitRepository;
    }

    public void setGitRepository(GitRepository gitRepository) {
        this.gitRepository = gitRepository;
    }
}
