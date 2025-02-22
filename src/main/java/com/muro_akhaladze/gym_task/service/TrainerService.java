package com.muro_akhaladze.gym_task.service;

import com.muro_akhaladze.gym_task.dao.TrainerDao;
import com.muro_akhaladze.gym_task.entity.Trainer;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
@RequiredArgsConstructor
public class TrainerService {
    private final UserService userService;
    private final TrainerDao trainerDao;


    public Trainer createTrainer(String firstName, String lastName,String specialization) {
        Trainer trainer = new Trainer();
        trainer.setFirstName(firstName);
        trainer.setLastName(lastName);
        trainer.setSpecialization(specialization);


        trainer.setUserId(userService.getTrainerId());
        trainer.setUserName(userService.generateUserName(firstName, lastName));
        trainer.setPassword(userService.generatePassword());

        log.info("Create trainer");
        return trainerDao.createTrainer(trainer);
    }
    public Optional<Trainer> getTrainer(int trainerId) {
        log.info("Get trainer");
        return trainerDao.getTrainer(trainerId);
    }
    public Trainer updateTrainer(Trainer trainer) {
        log.info("Update trainer");
        return trainerDao.updateTrainer(trainer);
    }
}
