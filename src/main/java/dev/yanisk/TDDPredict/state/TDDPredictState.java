package dev.yanisk.TDDPredict.state;

import dev.yanisk.TDDPredict.models.TestRun;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.XCollection;

import java.util.LinkedList;

public class TDDPredictState {

    @Tag("testRunHistory")
    @XCollection
    public LinkedList<TestRun> testRunHistory = new LinkedList<>();
}

