package dev.yanisk.TDDPredict.util;

import dev.yanisk.TDDPredict.view.CounterLabel;
import dev.yanisk.TDDPredict.view.HistoryList;
import com.intellij.openapi.util.Key;

public class Constants {
    public static final Key<CounterLabel> TEST_PASS_COUNTER = Key.create("TestPassCounter");
    public static final Key<CounterLabel> TEST_FAIL_COUNTER = Key.create("TestFailCounter");
    public static final Key<CounterLabel> PREDICT_CORRECT_COUNTER = Key.create("PredictCorrectCounter");
    public static final Key<CounterLabel> PREDICT_INCORRECT_COUNTER = Key.create("PredictIncorrectCounter");
    public static final Key<HistoryList> HISTORY_PANEL = Key.create("HistoryPanel");
    public static final Key<CounterLabel> TEST_TERMINATION_COUNTER = Key.create("HistoryPanel");
}
