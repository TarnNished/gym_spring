package com.muro_akhaladze.gym_task.dao;


import com.muro_akhaladze.gym_task.entity.Training;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Log
public class TrainingDao {

    private final Map<Pair<Integer,Integer>, Training> trainingStorage;


    public Training createTraining(Training training)
    {

        // since training need unique primary key we concatenate two ids in composite key with pair algorithm
        Pair<Integer,Integer> compositeKey = Pair.of(training.getTraineeId(), training.getTrainerId());
        trainingStorage.put(compositeKey, training);
        return training;
    }
    // select function for training
    public Optional<Training> getTraining(int trainerId,int traineeId) {
        return Optional.ofNullable(trainingStorage.get(Pair.of(traineeId, trainerId)))
                .or(() -> {
                    log.warning("no training found");
                    return Optional.empty();
                });
    }


}
