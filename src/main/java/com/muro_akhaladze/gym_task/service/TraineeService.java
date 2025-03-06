package com.muro_akhaladze.gym_task.service;


import com.muro_akhaladze.gym_task.entity.Trainee;
import com.muro_akhaladze.gym_task.repository.TraineeRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Log
@RequiredArgsConstructor
public class TraineeService {
    private final TraineeRepo traineeRepo;

    public Trainee createTrainee(@Valid Trainee trainee) {
        log.info("Creating new trainee");
        return traineeRepo.save(trainee);
    }
    public void deleteTrainee(String username) {
        traineeRepo.deleteByUsername(username);
        log.info("deleting trainee with ID");
    }
    public Optional<Trainee> getTraineeByUsername(String username) {
            Optional<Trainee> trainee = traineeRepo.findByUsername(username);
            if (trainee.isPresent()) {
                log.info("select trainee with username");
            }else
                log.warning("no user found");
        return trainee;
    }
    public Trainee updateTrainee(@Valid Trainee trainee) {
        Trainee existingTrainee = traineeRepo.findByUsername(trainee.getUser().getUserName())
                .orElseThrow(() -> {
                    log.warning("No trainee found with username '" + trainee.getUser().getUserName() + "'");
                    return new IllegalArgumentException("Trainee not found");
                });

        log.info("Updating trainee with username");

        existingTrainee.setAddress(trainee.getAddress());
        existingTrainee.setDateOfBirth(trainee.getDateOfBirth());
        existingTrainee.setUser(trainee.getUser());

        return traineeRepo.save(existingTrainee);
    }
}
