package dev.yanisk.TDDPredict.bus;

import com.intellij.util.messages.Topic;
import dev.yanisk.TDDPredict.models.TestRun;

public interface TestRunEventBus {
    Topic<TestRunEventBus> TEST_RUN_EVENT_BUS = new Topic<>("dev.yanisk.TDDPredict.bus.TestRunProcessedTopic", TestRunEventBus.class);

    void testRunProcessed(TestRun testRun);
}
