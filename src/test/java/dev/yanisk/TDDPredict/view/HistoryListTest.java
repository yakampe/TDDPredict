package dev.yanisk.TDDPredict.view;

import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import dev.yanisk.TDDPredict.models.TestRun;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HistoryListTest {

    @Test
    void when_there_is_no_test_run_outcome_should_not_throw_exception() {
        Project project = mock(Project.class);
        MessageBus messageBus = mock(MessageBus.class);
        MessageBusConnection messageBusConnection = mock(MessageBusConnection.class);

        when(project.getMessageBus()).thenReturn(messageBus);
        when(messageBus.connect()).thenReturn(messageBusConnection);

        HistoryList historyList = new HistoryList(project);

        LinkedList<TestRun> testHistory = new LinkedList<>();
        TestRun testRun = new TestRun();
        testRun.setDateTime("2015-04-14T11:07:00");
        testHistory.add(testRun);


        assertDoesNotThrow(() -> {
            historyList.addHistory(testHistory);
        });

    }

}