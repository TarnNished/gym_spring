package com.muro_akhaladze.gym_task.service;

import com.muro_akhaladze.gym_task.entity.Trainee;
import com.muro_akhaladze.gym_task.entity.Trainer;
import com.muro_akhaladze.gym_task.repository.TrainerRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepo trainerRepo;

    public Trainer createTrainer(@Valid Trainer trainer) {

        log.info("Create trainer");
        return trainerRepo.save(trainer);
    }
    public Optional<Trainer> getTrainerByUsername(String username) {
        Optional<Trainer> trainer = trainerRepo.findByUsername(username);
        if (trainer.isPresent()) {
            log.info("select trainee with username");
        }else
            log.warning("no user found");
        return trainer;
    }
    public Trainer updateTrainer(@Valid Trainer trainer) {
        Trainer existingTrainer = trainerRepo.findByUsername(trainer.getUser().getUserName())
                .orElseThrow(() -> {
                    log.warning("No trainer found with username '" + trainer.getUser().getUserName() + "'");
                    return new IllegalArgumentException("Trainer not found");
                });

        log.info("Updating trainer with username");

        existingTrainer.setSpecialization(trainer.getSpecialization());
        existingTrainer.setUser(trainer.getUser());

        return trainerRepo.save(existingTrainer);
    }
    public List<Trainer> getNotAssigned(String username) {
        return trainerRepo.findTrainersNotAssignedToTrainee(username);
    }
}
