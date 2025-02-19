package com.muro_akhaladze.gym_task.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    private int traineeId;
    private int trainerId;
    private String trainingName;
    private TrainingType trainingType;
    private String TrainingDate;
    private Duration TrainingDuration;
}
