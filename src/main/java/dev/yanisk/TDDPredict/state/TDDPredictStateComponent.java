package dev.yanisk.TDDPredict.state;

import dev.yanisk.TDDPredict.models.Guess;
import dev.yanisk.TDDPredict.models.TestRun;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.LinkedList;

@State(name = "TDDPredictState", storages = {@Storage(StoragePathMacros.WORKSPACE_FILE)})
public class TDDPredictStateComponent implements PersistentStateComponent<TDDPredictState> {
    private TDDPredictState state;
    public TDDPredictStateComponent(Project project) {
        state = new TDDPredictState();
    }

    @Nullable
    @Override
    public TDDPredictState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull TDDPredictState state) {
        this.state = state;
    }

    public void addGuess(Guess guess) {
        TestRun testRun = new TestRun(null,guess,LocalDateTime.now().toString(),null, null);
        state.testRunHistory.addFirst(testRun);
    }

    public LinkedList<TestRun> getTestHistory() {
        return new LinkedList<>(state.testRunHistory);
    }

    public void clearTestHistory() {
        state.testRunHistory.clear();
    }



    public TestRun getLatestTest() {
        return state.testRunHistory.getFirst();
    }
}