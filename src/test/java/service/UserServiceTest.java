package service;

import com.muro_akhaladze.gym_task.entity.Trainee;
import com.muro_akhaladze.gym_task.entity.Trainer;
import com.muro_akhaladze.gym_task.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Mock
    private Map<Integer, Trainer> trainerStorage;

    @Mock
    private Map<Integer, Trainee> traineeStorage;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        trainerStorage = new HashMap<>();
        traineeStorage = new HashMap<>();
        userService = new UserService(trainerStorage, traineeStorage);
    }

    @Test
    void testGenerateUserName_Unique() {
        String username = userService.generateUserName("John", "Doe");
        assertEquals("John.Doe", username);
    }

    @Test
    void testGenerateUserName_Duplicate() {
        Trainer trainer = new Trainer(
                "John",
                "Doe",
                "John.Doe",
                1,
                "password123",
                "Yoga");
        trainerStorage.put(1, trainer);

        String username = userService.generateUserName("John", "Doe");
        assertEquals("John.Doe1", username);
    }

    @Test
    void testAlreadyUsed_Exists() {
        Trainee trainee = new Trainee(
                "Liam",
                "Anderson",
                "securePass456",
                "Liam.Anderson",
                1,
                "1998-07-15",
                "456 Elm Street");
        traineeStorage.put(1, trainee);

        assertTrue(userService.alreadyUsed("Liam.Anderson"));
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
