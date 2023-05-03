package com.example.TDD.state;

import com.example.TDD.models.TestRun;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.XCollection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TDDPredictState {

    @Tag("testRunHistory")
    @XCollection
    public LinkedList<TestRun> testRunHistory = new LinkedList<>();
}

