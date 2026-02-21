package com.register.backend.rest.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link com.register.backend.db.entities.PlayerEntity}
 */
public class PlayerDto implements Serializable {
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthday;
    private final Integer drinkCounter;

    public PlayerDto(Integer id, String firstName, String lastName, LocalDate birthday, Integer drinkCounter) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.drinkCounter = drinkCounter;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Integer getDrinkCounter() {
        return drinkCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDto entity = (PlayerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.birthday, entity.birthday) &&
                Objects.equals(this.drinkCounter, entity.drinkCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, drinkCounter);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "birthday = " + birthday + ", " +
                "drinkCounter = " + drinkCounter + ")";
    }
}