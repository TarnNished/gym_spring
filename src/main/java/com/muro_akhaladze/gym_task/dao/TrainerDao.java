package com.muro_akhaladze.gym_task.dao;

import com.muro_akhaladze.gym_task.entity.Trainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log
public class TrainerDao {
    private final Map<Integer, Trainer> trainerStorage;


    // creates trainer
    public Trainer createTrainer(Trainer trainer) {
        trainerStorage.put(trainer.getUserId(), trainer);
        return trainer;
    }
    // select function
    public Optional<Trainer> getTrainer(int userId) {
        return Optional.ofNullable(trainerStorage.get(userId))
                .or(() -> {
                   log.warning("Trainer with ID " + userId + " not found");
                    return Optional.empty();
                });
    }

    // updates trainer and check if such trainer already exist in database(hashmap)
    public Trainer updateTrainer(Trainer trainer) {
        if (!trainerStorage.containsKey(trainer.getUserId())) {
            log.warning("no trainer found");
        }
        trainerStorage.put(trainer.getUserId(), trainer);
        return trainer;
    }

}
