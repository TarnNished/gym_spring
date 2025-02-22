package com.muro_akhaladze.gym_task.data;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.muro_akhaladze.gym_task.entity.Trainee;
import com.muro_akhaladze.gym_task.entity.Trainer;
import com.muro_akhaladze.gym_task.entity.Training;
import com.muro_akhaladze.gym_task.service.TraineeService;
import com.muro_akhaladze.gym_task.service.TrainerService;
import com.muro_akhaladze.gym_task.service.TrainingService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PreLoader {

    private final TrainingService trainingService;
    private final TrainerService trainerService;
    private final TraineeService traineeService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${data.storage}")
    private String dataFilePath;

    @PostConstruct
    public void loadData() {
        log.info("Loading data from: {} " , dataFilePath);

        try {
            File file = new File(dataFilePath);
            DataWrapper dataWrapper = objectMapper.readValue(file,DataWrapper.class);
            for (Trainee trainee : dataWrapper.getTrainees()) {
                Trainee savedTrainee = traineeService.createTrainee(
                        trainee.getFirstName(),
                        trainee.getLastName(),
                        trainee.getAddress(),
                        trainee.getDateOfBirth()
                );
                log.info("Created Trainee: {}", savedTrainee.getUserName());
            }
            for (Trainer trainer : dataWrapper.getTrainers()) {
                Trainer savedTrainer = trainerService.createTrainer(
                        trainer.getFirstName(),
                        trainer.getLastName(),
                        trainer.getSpecialization()
                );
                log.info("Created Trainer: {}", savedTrainer.getUserName());
            }
            for (TrainingData trainingData : dataWrapper.getTrainings()) {
                Training savedTraining = trainingService.createTraining(
                        trainingData.getTraineeId(),
                        trainingData.getTrainerId(),
                        trainingData.getTrainingName(),
                        trainingData.getTrainingDate(),
                        Duration.ofMinutes(trainingData.getTrainingDurationMinutes()),
                        trainingData.getTrainingType()
                );
                log.info("Created Training: {}", savedTraining.getTrainingName());

            }


        } catch (IOException e) {
            log.error("Error reading data file: {}", e.getMessage());
        }
    }
    // this class is used to map the structure of json file
    // and ensures it is properly structured before being used
    @Getter
    static class DataWrapper {
        private List<Trainee> trainees;
        private List<Trainer> trainers;
        private List<TrainingData> trainings;
    }

    @Getter
    static class TrainingData {
        private int traineeId;
        private int trainerId;
        private String trainingName;
        private String trainingType;
        private String trainingDate;
        private int trainingDurationMinutes;
    }

}
