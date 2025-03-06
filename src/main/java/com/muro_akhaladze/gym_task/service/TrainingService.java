package com.muro_akhaladze.gym_task.service;

import com.muro_akhaladze.gym_task.entity.Training;
import com.muro_akhaladze.gym_task.repository.TrainingRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Log
@RequiredArgsConstructor
public class TrainingService {
    private final TrainingRepo trainingRepo;

    public Training createTraining(@Valid Training training) {
        log.info("created Training");
        return trainingRepo.save(training);
    }
    public Optional<Training> getTraining(int trainingId) {
        Optional<Training> training = trainingRepo.findById(trainingId);
        if (training.isPresent()) {
            log.info("select trainee with username");
        }else
            log.warning("no user found");
        return training;
    }
    public List<Training> getTraineeTrainings(String traineeUsername, LocalDate fromDate, LocalDate toDate, String trainerName, String trainingType) {
        return trainingRepo.findTraineeTrainings(traineeUsername, fromDate, toDate, trainerName, trainingType);
    }

    public List<Training> getTrainerTrainings(String trainerUsername, LocalDate fromDate, LocalDate toDate, String traineeName) {
        return trainingRepo.findTrainerTrainings(trainerUsername, fromDate, toDate, traineeName);
    }
}
