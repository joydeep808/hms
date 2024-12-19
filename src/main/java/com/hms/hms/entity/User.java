package com.hms.hms.entity;



import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Email(message = "Please enter valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Full name is required")
    private String fullName;

    private UserRole role;

    public enum UserRole{
        USER ,
        ADMIN,
        DOCTOR,
        ASSISTANT
    }
}
