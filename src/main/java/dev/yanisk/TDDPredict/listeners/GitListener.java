package dev.yanisk.TDDPredict.listeners;

import dev.yanisk.TDDPredict.service.GitService;
import com.intellij.openapi.project.Project;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryChangeListener;
import org.jetbrains.annotations.NotNull;
public class GitListener implements GitRepositoryChangeListener {

    private final Project project;

    public GitListener(Project project) {
        this.project = project;
    }

    @Override
    public void repositoryChanged(@NotNull GitRepository repository) {
        project.getService(GitService.class).setCurrentCommit(repository.getCurrentRevision());
        project.getService(GitService.class).setGitRepository(repository);
//        Collection<GitRemote> remotes = repository.getInfo().getRemotes();
//
//        remotes.forEach(remote -> {
//            String remoteUrl = remote.getFirstUrl().replaceFirst("\\.git","/");
//            project.getService(GitService.class).setCurrentCommit(remoteUrl + repository.getCurrentRevision());
//        });

    }
}
