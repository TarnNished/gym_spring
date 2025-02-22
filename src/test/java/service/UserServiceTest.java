package service;

import com.muro_akhaladze.gym_task.dao.TraineeDao;
import com.muro_akhaladze.gym_task.dao.TrainerDao;
import com.muro_akhaladze.gym_task.entity.Trainee;
import com.muro_akhaladze.gym_task.entity.Trainer;
import com.muro_akhaladze.gym_task.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import org.mockito.*;


import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private TraineeDao traineeDao;

    @Mock
    private TrainerDao trainerDao;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(trainerDao, traineeDao);
    }

    @Test
    void testGenerateUserName_Unique() {
        String username = userService.generateUserName("Unique", "Username");
        assertEquals("Unique.Username", username);
    }

    @Test
    void testAlreadyUsed_NotExists() {
        assertFalse(userService.alreadyUsed("Non.Existing"));
    }

    @Test
    void testGeneratePassword_Length() {
        String password = userService.generatePassword();
        assertEquals(10, password.length());
    }

    @Test
    void testGetTrainerId_Increments() {
        int firstId = userService.getTrainerId();
        int secondId = userService.getTrainerId();

        assertEquals(firstId + 1, secondId);
    }

    @Test
    void testGetTraineeId_ShouldReturnIncrementingValues() {
        int firstId = userService.getTraineeId();
        int secondId = userService.getTraineeId();

        assertEquals(firstId + 1, secondId);
    }
}
