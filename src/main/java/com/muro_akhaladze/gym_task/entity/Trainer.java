package com.muro_akhaladze.gym_task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trainer extends User{
    @Column(nullable = false)
    private String specialization;

    public Trainer(String firstName, String lastName, String username,Integer userId, String password, String specialization) {
        super(firstName,lastName,username,password,userId,true);


        this.specialization = specialization;
    }
}
