package dev.yanisk.TDDPredict.service;

import com.intellij.openapi.project.Project;
import dev.yanisk.TDDPredict.bus.TestRunEventPublisherService;
import dev.yanisk.TDDPredict.models.*;
import dev.yanisk.TDDPredict.state.TDDPredictStateComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PredictionProcessorServiceTest {

    Project project;

    TDDPredictStateComponent tddPredictStateComponent;

    PredictionProcessorService predictionProcessorService;

    private TestRunEventPublisherService testRunEventPublisherService;

    @BeforeEach
    void setup() {
        project = mock(Project.class);
        predictionProcessorService = new PredictionProcessorService(project);


        tddPredictStateComponent = mock(TDDPredictStateComponent.class);
        testRunEventPublisherService = mock(TestRunEventPublisherService.class);

        when(project.getService(TDDPredictStateComponent.class)).thenReturn(tddPredictStateComponent);
        when(project.getService(TestRunEventPublisherService.class)).thenReturn(testRunEventPublisherService);
    }

    @Test
    void given_test_finished_user_guessed_pass_then_should_record_test_outcome_and_correct_prediction() {
        //arrange
        ArgumentCaptor<TestRun> testRunArgumentCaptor = ArgumentCaptor.forClass(TestRun.class);

        TestRun testRun = new TestRun(null, Guess.PASS,null,null,null);
        when(tddPredictStateComponent.getLatestTest()).thenReturn(testRun);

        //act
        predictionProcessorService.processPrediction(ProcessOutcome.TEST_PASS);

        //assert
        verify(testRunEventPublisherService).publishTestRunProcessed(testRunArgumentCaptor.capture());
        assertEquals(Prediction.CORRECT, testRunArgumentCaptor.getValue().getPrediction());
        assertEquals(TestRunOutcome.PASSED, testRunArgumentCaptor.getValue().getTestRunOutcome());
    }

    @Test
    void given_test_finished_user_guessed_fail_then_should_record_test_outcome_and_incorrect_prediction() {
        //arrange
        ArgumentCaptor<TestRun> testRunArgumentCaptor = ArgumentCaptor.forClass(TestRun.class);

        TestRun testRun = new TestRun(null, Guess.FAIL,null,null,null);
        when(tddPredictStateComponent.getLatestTest()).thenReturn(testRun);

        //act
        predictionProcessorService.processPrediction(ProcessOutcome.TEST_PASS);

        //assert
        verify(testRunEventPublisherService).publishTestRunProcessed(testRunArgumentCaptor.capture());
        assertEquals(Prediction.INCORRECT, testRunArgumentCaptor.getValue().getPrediction());
        assertEquals(TestRunOutcome.PASSED, testRunArgumentCaptor.getValue().getTestRunOutcome());
    }

    @Test
    void given_test_terminated_user_guessed_pass_then_should_record_test_outcome_and_incorrect_prediction() {
        //arrange
        ArgumentCaptor<TestRun> testRunArgumentCaptor = ArgumentCaptor.forClass(TestRun.class);

        TestRun testRun = new TestRun(null, Guess.PASS,null,null,null);
        when(tddPredictStateComponent.getLatestTest()).thenReturn(testRun);

        //act
        predictionProcessorService.processPrediction(ProcessOutcome.TEST_TERMINATED);

        //assert
        verify(testRunEventPublisherService).publishTestRunProcessed(testRunArgumentCaptor.capture());
        assertEquals(Prediction.INCORRECT, testRunArgumentCaptor.getValue().getPrediction());
        assertEquals(TestRunOutcome.NOT_EXECUTED, testRunArgumentCaptor.getValue().getTestRunOutcome());
    }

    @Test
    void given_test_terminated_user_guessed_fail_then_should_record_test_outcome_and_incorrect_prediction() {
        //arrange
        ArgumentCaptor<TestRun> testRunArgumentCaptor = ArgumentCaptor.forClass(TestRun.class);

        TestRun testRun = new TestRun(null, Guess.FAIL,null,null,null);
        when(tddPredictStateComponent.getLatestTest()).thenReturn(testRun);

        //act
        predictionProcessorService.processPrediction(ProcessOutcome.TEST_TERMINATED);

        //assert
        verify(testRunEventPublisherService).publishTestRunProcessed(testRunArgumentCaptor.capture());
        assertEquals(Prediction.INCORRECT, testRunArgumentCaptor.getValue().getPrediction());
        assertEquals(TestRunOutcome.NOT_EXECUTED, testRunArgumentCaptor.getValue().getTestRunOutcome());
    }

    @Test
    void given_test_failed_user_guessed_fail_then_should_record_test_outcome_and_correct_prediction() {
        //arrange
        ArgumentCaptor<TestRun> testRunArgumentCaptor = ArgumentCaptor.forClass(TestRun.class);

        TestRun testRun = new TestRun(null, Guess.FAIL,null,null,null);
        when(tddPredictStateComponent.getLatestTest()).thenReturn(testRun);

        //act
        predictionProcessorService.processPrediction(ProcessOutcome.TEST_FAILED);

        //assert
        verify(testRunEventPublisherService).publishTestRunProcessed(testRunArgumentCaptor.capture());
        assertEquals(Prediction.CORRECT, testRunArgumentCaptor.getValue().getPrediction());
        assertEquals(TestRunOutcome.FAILED, testRunArgumentCaptor.getValue().getTestRunOutcome());
    }

    @Test
    void given_test_failed_user_guessed_pass_then_should_record_test_outcome_and_incorrect_prediction() {
        //arrange
        ArgumentCaptor<TestRun> testRunArgumentCaptor = ArgumentCaptor.forClass(TestRun.class);

        TestRun testRun = new TestRun(null, Guess.PASS,null,null,null);
        when(tddPredictStateComponent.getLatestTest()).thenReturn(testRun);

        //act
        predictionProcessorService.processPrediction(ProcessOutcome.TEST_FAILED);

        //assert
        verify(testRunEventPublisherService).publishTestRunProcessed(testRunArgumentCaptor.capture());
        assertEquals(Prediction.INCORRECT, testRunArgumentCaptor.getValue().getPrediction());
        assertEquals(TestRunOutcome.FAILED, testRunArgumentCaptor.getValue().getTestRunOutcome());
    }

}