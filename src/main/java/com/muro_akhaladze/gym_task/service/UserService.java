package com.muro_akhaladze.gym_task.service;

import com.muro_akhaladze.gym_task.dao.TraineeDao;
import com.muro_akhaladze.gym_task.dao.TrainerDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TrainerDao trainerDao;
    private final TraineeDao traineeDao;

    private static int traineeId = 1;
    private static int trainerId = 1;

    public boolean alreadyUsed(String username){
        return this.traineeDao.existsByName(username) || this.trainerDao.existsByName(username);
    }
    public String generateUserName(String firstName, String lastName) {
        String temp = firstName + "." + lastName;
        String generated = temp;
        int i = 1;

        while (alreadyUsed(generated)) {
            generated = temp + i;
            i++;
        }
        return generated;
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
