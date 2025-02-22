package facade;

import com.muro_akhaladze.gym_task.entity.Trainee;
import com.muro_akhaladze.gym_task.entity.Trainer;
import com.muro_akhaladze.gym_task.entity.Training;
import com.muro_akhaladze.gym_task.facade.Facade;
import com.muro_akhaladze.gym_task.service.TraineeService;
import com.muro_akhaladze.gym_task.service.TrainerService;
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

public class FacadeTest {

    @Mock
    private TraineeService traineeService;

    @Mock
    private TrainerService trainerService;

    @Mock
    private TrainingService trainingService;

    @InjectMocks
    private Facade facade;

    private Trainee trainee;
    private Trainer trainer;
    private Training training;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        trainee = new Trainee(
                "Liam",
                "Anderson",
                "securePass456",
                "Liam.Anderson",
                2,
                "1998-07-15",
                "456 Elm Street"
        );
        trainer = new Trainer(
                "Oliver",
                "Martinez",
                "Oliver.Martinez",
                1,
                "securePass452",
                "Yoga & Flexibility"
        );
        training = new Training(1,
                2,
                "Strength Training",
                "2024-02-20",
                Duration.ofMinutes(60),
                "Strength"
        );
    }

    @Test
    void testCreateTrainee_Success() {
        when(traineeService.createTrainee(anyString(), anyString(), anyString(), anyString())).thenReturn(trainee);

        Trainee createdTrainee = facade.createTrainee("Liam", "Anderson", "123 Oak St", "1995-08-10");

        assertNotNull(createdTrainee);
        verify(traineeService, times(1)).createTrainee(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void testCreateTrainer_Success() {
        when(trainerService.createTrainer(anyString(), anyString(), anyString())).thenReturn(trainer);

        Trainer createdTrainer = facade.createTrainer("Sophia", "Clark", "Pilates");

        assertNotNull(createdTrainer);
        verify(trainerService, times(1)).createTrainer(anyString(), anyString(), anyString());
    }

    @Test
    void testCreateTraining_Success() {
        when(facade.createTraining(anyInt(), anyInt(), anyString(), anyString(), any(), anyString()))
                .thenReturn(training);

        Training result = facade.createTraining(1, 2, "Cardio", "2025-02-25", null, "Gym A");

        assertNotNull(result);
        assertEquals(training, result);

        verify(trainingService, times(1)).createTraining(anyInt(), anyInt(), anyString(), anyString(), any(), anyString());
    }

    @Test
    void testGetTrainee_Found() {
        when(traineeService.getTrainee(1)).thenReturn(Optional.of(trainee));

        Optional<Trainee> retrievedTrainee = facade.getTrainee(1);

        assertTrue(retrievedTrainee.isPresent());
        verify(traineeService, times(1)).getTrainee(1);
    }

    @Test
    void testGetTrainer_Found() {
        when(trainerService.getTrainer(1)).thenReturn(Optional.of(trainer));

        Optional<Trainer> retrievedTrainer = facade.getTrainer(1);

        assertTrue(retrievedTrainer.isPresent());
        verify(trainerService, times(1)).getTrainer(1);
    }

    @Test
    void testGetTraining_Found() {
        when(trainingService.getTraining(1, 2)).thenReturn(Optional.of(training));

        Optional<Training> retrievedTraining = facade.getTraining(1, 2);

        assertTrue(retrievedTraining.isPresent());
        verify(trainingService, times(1)).getTraining(1, 2);
    }

    @Test
    void testUpdateTrainee_Success() {
        when(traineeService.updateTrainee(any())).thenReturn(trainee);

        Trainee updatedTrainee = facade.updateTrainee(trainee);

        assertNotNull(updatedTrainee);
        verify(traineeService, times(1)).updateTrainee(any());
    }

    @Test
    void testUpdateTrainer_Success() {
        when(trainerService.updateTrainer(any())).thenReturn(trainer);

        Trainer updatedTrainer = facade.updateTrainer(trainer);

        assertNotNull(updatedTrainer);
        verify(trainerService, times(1)).updateTrainer(any());
    }

    @Test
    void testDeleteTrainee_Success() {
        when(traineeService.deleteTrainee(1)).thenReturn(true);

        boolean isDeleted = facade.deleteTrainee(1);

        assertTrue(isDeleted);
        verify(traineeService, times(1)).deleteTrainee(1);
    }
}
