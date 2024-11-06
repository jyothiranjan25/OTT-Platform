package com.example.springboot.UserManagment.AppUser;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "app_user",indexes = {
        @Index(name = "idx_username", columnList = "username"),
        @Index(name = "idx_email", columnList = "email")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uk_username", columnNames = "username"),
        @UniqueConstraint(name = "uk_email", columnNames = "email")
})
@EntityListeners(AppUserListener.class)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private AppUserEnum userType;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
