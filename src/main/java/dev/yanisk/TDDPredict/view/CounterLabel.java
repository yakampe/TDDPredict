package com.example.TDD.view;

import javax.swing.*;

public class CounterLabel extends JLabel {
    private long count;

    public CounterLabel() {

    }

    public CounterLabel(long calculateGuesses) {
        this.count = calculateGuesses;
    }

    public void incrementCount() {
        count++;
        this.setText(count);
    }

    public void setCount(long count) {
        this.count = count;
        setText(count);
    }

    private void setText(long count) {
        this.setText(String.valueOf(count));
    }
}
