package com.muro_akhaladze.gym_task.service;

import com.muro_akhaladze.gym_task.dao.TrainingDao;
import com.muro_akhaladze.gym_task.entity.Training;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@Log
@RequiredArgsConstructor
public class TrainingService {
    private TrainingDao trainingDao;

    public Training createTraining(int traineeId, int trainerId, String trainingName,
                                   String trainingDate, Duration trainingDuration,String trainingTypeName) {
        Training training = new Training(traineeId, trainerId,
                trainingName, trainingDate, trainingDuration, trainingTypeName);
        log.info("created Training");
        return trainingDao.createTraining(training);
    }
    public Optional<Training> getTraining(int traineeId, int trainerId) {
        log.info("get Training");
        return trainingDao.getTraining(traineeId, trainerId);
    }
}
