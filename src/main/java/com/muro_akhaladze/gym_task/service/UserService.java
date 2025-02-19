package com.muro_akhaladze.gym_task.service;

import com.muro_akhaladze.gym_task.entity.Trainee;
import com.muro_akhaladze.gym_task.entity.Trainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log
public class UserService {
    private final Map<Integer, Trainer> trainerStorage;
    private final Map<Integer, Trainee> traineeStorage;

    private static int traineeId = 1;
    private static int trainerId = 1;

    public boolean alreadyUsed(String username){
        return this.traineeStorage.values().stream()
                .anyMatch(t -> t.getUserName().equals(username)) ||
                this.trainerStorage.values().stream()
                        .anyMatch(t -> t.getUserName().equals(username));

    }
    public String generateUserName(String firstName, String lastName) {
        String temp = firstName + "." + lastName;
        int i = 1;

        while (alreadyUsed(temp)) {
            temp = firstName + "." + lastName + i;
            i++;
        }

        return temp;
    }

    public synchronized Integer getTrainerId() {
        return trainerId++;
    }
    public synchronized Integer getTraineeId() {
        return traineeId++;
    }

    public String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        int password_length = 10;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(password_length);
        for (int i = 0; i < password_length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

}
