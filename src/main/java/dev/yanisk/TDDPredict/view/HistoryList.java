package com.example.TDD.view;

import com.example.TDD.models.Prediction;
import com.example.TDD.models.TestRun;
import com.intellij.icons.AllIcons;
import com.intellij.ui.components.JBLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;

import static com.example.TDD.util.ButtonColors.*;

public class HistoryList extends JPanel {

    LinkedList<TestRun> testHistory = new LinkedList<>();

    public HistoryList() {
        this.setLayout(new BorderLayout());
    }

    public void addHistory(LinkedList<TestRun> testHistory) {
        this.testHistory.addAll(testHistory);
        generateHistoryList();
        setupRefreshTimer(60000); // one minute
    }

    private void setupRefreshTimer(int delay) {
        // Set up timer to regenerate history list every minute
        Timer timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (testHistory.size() != 0) {
                    removeAll();
                    generateHistoryList();
                }
            }
        });
        timer.start();
    }

    public void addTestRun(TestRun lastTestRun) {
        testHistory.addFirst(lastTestRun);
        this.removeAll();
        generateHistoryList();
    }

    public void removeHistory() {
        testHistory = new LinkedList<>();
        this.removeAll();
        generateHistoryList();
    }

    private void generateHistoryList() {
        extracted();
    }

    private void extracted() {
        if (testHistory.size() == 0) {
            add(new JBLabel("No Predictions", SwingConstants.CENTER), BorderLayout.NORTH);
        } else {
            removeAll();
            JPanel historyContent = new JPanel(new GridBagLayout());
            GridBagConstraints historyGbc = new GridBagConstraints();
            historyGbc.insets = new Insets(5, 5, 5, 5);

            for (int i = 0; i < testHistory.size(); i++) {
                TestRun testRun = testHistory.get(i);
                // time
                historyGbc.gridy = i;
                historyGbc.gridx = 0;

                String dateTimeString = testRun.getDateTime();
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);

                long secondsSinceLastExecution = Duration.between(dateTime, LocalDateTime.now()).getSeconds();
                JBLabel timeLabel = new JBLabel(getTimeString(secondsSinceLastExecution));

                //If nothing has happened for 15 minutes make the time red
                if (i == 0 && secondsSinceLastExecution >= 900) {
                    Font boldFont = timeLabel.getFont().deriveFont(Font.BOLD);
                    timeLabel.setForeground(Color.RED);
                    timeLabel.setFont(boldFont);
                }

                historyContent.add(timeLabel, historyGbc);

                // circle
                historyGbc.gridy = i;
                historyGbc.gridx = 1;

                Color circleColor = Color.BLACK;

                switch (testRun.getTestRunOutcome()) {
                    case PASSED:
                        circleColor = TEST_PASS_COLOR;
                        break;
                    case FAILED:
                        circleColor = TEST_FAIL_COLOR;
                        break;
                    case NOT_EXECUTED:
                        circleColor = TEST_DID_NOT_RUN_COLOR;
                        break;
                }

                JPanel exampleGreenCirclePanel = new Circle(circleColor, 14, testRun.getCommit());
                historyContent.add(exampleGreenCirclePanel, historyGbc);

                //icon
                historyGbc.gridx = 2;

                Icon icon = testRun.getPrediction() == Prediction.CORRECT ? AllIcons.RunConfigurations.TestPassed : AllIcons.RunConfigurations.TestFailed;
                historyContent.add(new JLabel(icon), historyGbc);
            }

            add(historyContent, BorderLayout.NORTH);
        }
    }

    private String getTimeString(long secondsSinceExecution) {

        if (secondsSinceExecution < 60) {
            return "< 1 min";
        } else if (secondsSinceExecution < 3600) {
            return (secondsSinceExecution / 60) + " min";
        } else if (secondsSinceExecution < 86400) {
            return (secondsSinceExecution / 3600) + " h";
        } else {
            return "1d+";
        }
    }

}
