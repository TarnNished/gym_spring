package com.muro_akhaladze.gym_task.facade;

import com.muro_akhaladze.gym_task.entity.Trainee;
import com.muro_akhaladze.gym_task.entity.Trainer;
import com.muro_akhaladze.gym_task.entity.Training;
import com.muro_akhaladze.gym_task.entity.TrainingType;
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

    // CREATE SECTION
        // create Trainee
    public Trainee createTrainee(String firstName, String lastName, String address, String dob){
        return traineeService.createTrainee(firstName, lastName, address, dob);
    }
    // create Trainer
    public Trainer createTrainer(String firstName, String lastName,String specialization){
        return trainerService.createTrainer(firstName, lastName, specialization);
    }
    // create Training
    public Training createTraining(int traineeId, int trainerId, String trainingName, TrainingType trainingTypeName,
                                   String trainingDate, Duration trainingDuration){
        return trainingService.createTraining(traineeId, trainerId,
                trainingName, trainingTypeName, trainingDate, trainingDuration);
    }

    // SELECT SECTION
    // select Trainee
    public Optional<Trainee> getTrainee(int id) {
        return traineeService.getTrainee(id);
    }
    // select Trainer
    public Optional<Trainer> getTrainer(int id) {
        return trainerService.getTrainer(id);
    }
    // select Training
    public Optional<Training> getTraining(int traineeId, int trainerId) {
        return trainingService.getTraining(traineeId, trainerId);
    }

    // UPDATE SECTION
    // update trainee
    public Trainee updateTrainee(Trainee trainee) {
        return traineeService.updateTrainee(trainee);
    }
    public Trainer updateTrainer(Trainer trainer) {
        return trainerService.updateTrainer(trainer);
    }

    // DELETE SECTION
    // delete trainee from DB
    public boolean deleteTrainee(int id) {
        return traineeService.deleteTrainee(id);
    }



}
