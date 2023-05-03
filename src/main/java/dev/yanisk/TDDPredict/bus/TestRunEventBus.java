package dev.yanisk.TDDPredict.bus;

import com.intellij.util.messages.Topic;
import dev.yanisk.TDDPredict.models.TestRun;

public interface TestRunProcessedTopic {
    Topic<TestRunProcessedTopic> TEST_RUN_EVENT_BUS = new Topic<>("dev.yanisk.TDDPredict.bus.TestRunProcessedTopic", TestRunProcessedTopic.class);

    void testRunProcessed(TestRun testRun);
}
