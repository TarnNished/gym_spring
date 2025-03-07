package com.muro_akhaladze.gym_task.service;


import com.muro_akhaladze.gym_task.dao.TraineeDao;
import com.muro_akhaladze.gym_task.entity.Trainee;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Log
@RequiredArgsConstructor
public class TraineeService {
    private final UserService userService;
    private final TraineeDao traineeDao;

        public Trainee createTrainee(Trainee trainee) {

        trainee.setUserId(userService.getTraineeId());
        trainee.setUserName(userService.generateUserName(trainee.getFirstName(), trainee.getLastName()));
        trainee.setPassword(userService.generatePassword());

        log.info("Creating new trainee: {}");

        return traineeDao.createTrainee(trainee);
    }

    public boolean deleteTrainee(int userId) {
        log.info("deleting trainee with ID: {}");
        return traineeDao.deleteTrainee(userId);
    }
    public Optional<Trainee> getTrainee(int userId) {
        log.info("select trainee with id: {}");
        return traineeDao.getTrainee(userId);
    }
    public Trainee updateTrainee(Trainee trainee) {
        log.info("Updating trainee with ID: {}");
        return traineeDao.updateTrainer(trainee);
    }
}
