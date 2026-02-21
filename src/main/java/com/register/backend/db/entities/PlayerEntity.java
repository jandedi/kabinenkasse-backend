package com.register.backend.db.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "Player")
public class PlayerEntity extends AbstractParentEntity {

    @ColumnDefault("''")
    @Column(name = "FirstName", nullable = false, length = 100)
    private String firstName;

    @ColumnDefault("''")
    @Column(name = "LastName", nullable = false, length = 100)
    private String lastName;

    @Column(name = "Birthday")
    private LocalDate birthday;

    @ColumnDefault("0")
    @Column(name = "DrinkCounter", nullable = false)
    private Integer drinkCounter;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getDrinkCounter() {
        return drinkCounter;
    }

    public void setDrinkCounter(Integer drinkCounter) {
        this.drinkCounter = drinkCounter;
    }

}