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
    private final TrainingDao trainingDao;

    public Training createTraining(Training training) {
        log.info("created Training");
        return trainingDao.createTraining(training);
    }
    public Optional<Training> getTraining(int traineeId, int trainerId) {
        log.info("get Training");
        return trainingDao.getTraining(traineeId, trainerId);
    }
}
