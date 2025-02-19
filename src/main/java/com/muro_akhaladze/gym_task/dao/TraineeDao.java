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


    //creates trainee
    public Trainee createTrainee(Trainee trainee) {
        // and save it in database(hashmap)
        traineeStorage.put(trainee.getUserId(), trainee);
        return trainee;
    }

    // deletes trainee by id
    public boolean deleteTrainee(int userId) {
        return traineeStorage.remove(userId) != null;
    }

    // select function
    public Optional<Trainee> getTrainee(int userId) {
        return Optional.ofNullable(traineeStorage.get(userId))
                .or(() -> {
                    log.warning("Trainer with ID " + userId + " not found");
                    return Optional.empty();
                });
    }



    // updates trainee and check if such trainer already exist in database(hashmap)
    public Trainee updateTrainer(Trainee trainee) {
        if (!traineeStorage.containsKey(trainee.getUserId())) {
            log.warning("no trainer found");
        }
        traineeStorage.put(trainee.getUserId(), trainee);
        return trainee;
    }

}
