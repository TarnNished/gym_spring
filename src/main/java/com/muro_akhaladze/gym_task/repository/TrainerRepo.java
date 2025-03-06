package com.muro_akhaladze.gym_task.repository;

import com.muro_akhaladze.gym_task.entity.Trainee;
import com.muro_akhaladze.gym_task.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrainerRepo extends JpaRepository<Trainer, Integer> {

    @Query("SELECT t FROM Trainer t WHERE t.user.userName = :username")
    Optional<Trainer> findByUsername(@Param("username") String username);


    @Query("SELECT tr FROM Trainer tr " +
            "LEFT JOIN Training t ON tr = t.trainer AND t.trainee.user.userName = :traineeUsername " +
            "WHERE t.id IS NULL")
    List<Trainer> findTrainersNotAssignedToTrainee(@Param("traineeUsername") String traineeUsername);

}
