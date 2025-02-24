package com.muro_akhaladze.gym_task.facade;

import com.muro_akhaladze.gym_task.entity.Trainee;
import com.muro_akhaladze.gym_task.entity.Trainer;
import com.muro_akhaladze.gym_task.entity.Training;
import com.muro_akhaladze.gym_task.service.TraineeService;
import com.muro_akhaladze.gym_task.service.TrainerService;
import com.muro_akhaladze.gym_task.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Facade {
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    public Trainee createTrainee(Trainee trainee){
        return traineeService.createTrainee(trainee);
    }
    public Trainer createTrainer(Trainer trainer){
        return trainerService.createTrainer(trainer);
    }
    public Training createTraining(Training training){
        return trainingService.createTraining(training);
    }

    public Optional<Trainee> getTrainee(int id) {
        return traineeService.getTrainee(id);
    }
    public Optional<Trainer> getTrainer(int id) {
        return trainerService.getTrainer(id);
    }
    public Optional<Training> getTraining(int traineeId, int trainerId) {
        return trainingService.getTraining(traineeId, trainerId);
    }

    public Trainee updateTrainee(Trainee trainee) {
        return traineeService.updateTrainee(trainee);
    }
    public Trainer updateTrainer(Trainer trainer) {
        return trainerService.updateTrainer(trainer);
    }

    public boolean deleteTrainee(int id) {
        return traineeService.deleteTrainee(id);
    }



}
