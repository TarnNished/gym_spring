package com.muro_akhaladze.gym_task.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Trainee extends User{
    @Column(nullable = false)
    private String dateOfBirth;
    @Column(nullable = false, length = 50)
    private String address;

    public Trainee(String firstName,String lastName,  String password,String userName, int id,String dateOfBirth, String address) {
        super(firstName,lastName,userName,password,id,true);

        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}
