package dev.yanisk.TDDPredict.state;

import com.intellij.openapi.components.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@State(name = "SettingsState", storages = {@Storage(StoragePathMacros.WORKSPACE_FILE)})
public final class SettingsStateComponent implements PersistentStateComponent<SettingsState> {

    private SettingsState state;
    public SettingsStateComponent() {
        state = new SettingsState();
    }

    @Nullable
    @Override
    public SettingsState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull SettingsState state) {
        this.state = state;
    }

}
