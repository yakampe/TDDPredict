package dev.yanisk.TDDPredict.listeners;

import com.intellij.openapi.components.Service;
import dev.yanisk.TDDPredict.service.GitService;
import com.intellij.openapi.project.Project;
import git4idea.repo.GitRemote;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryChangeListener;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@Service(Service.Level.PROJECT)
public class GitListener implements GitRepositoryChangeListener {

    private final Project project;

    public GitListener(Project project) {
        this.project = project;
    }

    @Override
    public void repositoryChanged(@NotNull GitRepository repository) {
        Collection<GitRemote> remotes = repository.getInfo().getRemotes();
            
        remotes.forEach(remote -> {
            String remoteUrl = remote.getFirstUrl().replaceFirst("\\.git","/");
            project.getService(GitService.class).setCurrentCommit(remoteUrl + repository.getCurrentRevision());
        });

    }
}
