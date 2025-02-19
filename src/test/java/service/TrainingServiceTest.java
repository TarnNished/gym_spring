package service;

import com.muro_akhaladze.gym_task.dao.TrainingDao;
import com.muro_akhaladze.gym_task.entity.Training;
import com.muro_akhaladze.gym_task.entity.TrainingType;
import com.muro_akhaladze.gym_task.service.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainingServiceTest {

    @Mock
    private TrainingDao trainingDao;

    @InjectMocks
    private TrainingService trainingService;

    private Training training;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        training = new Training(
                1,
                2,
                "Strength Training",
                new TrainingType("Strength"),
                "2024-02-20",
                Duration.ofMinutes(60)
        );
    }

    @Test
    void testCreateTraining_Success() {
        when(trainingDao.createTraining(any())).thenReturn(training);

        Training createdTraining = trainingService.createTraining(
                training.getTraineeId(),
                training.getTrainerId(),
                training.getTrainingName(),
                training.getTrainingType(),
                training.getTrainingDate(),
                training.getTrainingDuration()
        );

        assertNotNull(createdTraining);
        assertEquals(training.getTrainingName(), createdTraining.getTrainingName());
        assertEquals(training.getTrainingType(), createdTraining.getTrainingType());

        verify(trainingDao, times(1)).createTraining(any());
    }

    @Test
    void testGetTraining_Found() {
        when(trainingDao.getTraining(training.getTraineeId(), training.getTrainerId())).thenReturn(Optional.of(training));

        Optional<Training> retrievedTraining = trainingService.getTraining(training.getTraineeId(), training.getTrainerId());

        assertTrue(retrievedTraining.isPresent());
        assertEquals(training.getTrainingName(), retrievedTraining.get().getTrainingName());

        verify(trainingDao, times(1)).getTraining(training.getTraineeId(), training.getTrainerId());
    }

    @Test
    void testGetTraining_NotFound_ReturnsEmpty() {
        when(trainingDao.getTraining(999, 888)).thenReturn(Optional.empty());

        Optional<Training> retrievedTraining = trainingService.getTraining(999, 888);

        assertTrue(retrievedTraining.isEmpty());
        verify(trainingDao, times(1)).getTraining(999, 888);
    }
}
