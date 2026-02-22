package com.register.backend.db.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "User")
public class UserEntity extends AbstractParentEntity {

    @Size(max = 50)
    @NotNull
    @ColumnDefault("'0'")
    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @NotNull
    @ColumnDefault("'PENALTY_TREASURER'")
    @Column(name = "Role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Size(max = 2000)
    @NotNull
    @ColumnDefault("'0'")
    @Column(name = "Password", nullable = false, length = 2000)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}