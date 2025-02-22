package com.muro_akhaladze.gym_task.dao;

import com.muro_akhaladze.gym_task.entity.Trainee;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log
public class TraineeDao {
    private final Map<Integer, Trainee> traineeStorage;


    public Trainee createTrainee(Trainee trainee) {
       this.traineeStorage.put(trainee.getUserId(), trainee);
        return trainee;
    }

    public boolean deleteTrainee(int userId) {
        return traineeStorage.remove(userId) != null;
    }

    public Optional<Trainee> getTrainee(int userId) {
        return Optional.ofNullable(traineeStorage.get(userId))
                .or(() -> {
                    log.warning("Trainer with ID " + userId + " not found");
                    return Optional.empty();
                });
    }


    public Trainee updateTrainer(Trainee trainee) {
        if (!traineeStorage.containsKey(trainee.getUserId())) {
            log.warning("no trainer found");
        }
        traineeStorage.put(trainee.getUserId(), trainee);
        return trainee;
    }

    public boolean existsByName(String username) {
        return traineeStorage.values().stream()
                .anyMatch(trainee -> trainee.getUserName().equalsIgnoreCase(username));
    }
}
