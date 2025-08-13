package com.warehouse.project.models;

import com.warehouse.project.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="User Name is Required")
    private String fullName;

    @Column(unique=true)
    @NotBlank(message="User Email is Required")
    private String email;

    @NotBlank(message="User Phone Number is Required")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(unique=true)
    @NotBlank(message="User Name is Required")
    private String username;

    @NotBlank(message="User Password is Required")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Transactions> transactions;

    @Column(name = "created_at")
    private final LocalDateTime created_at = LocalDateTime.now();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", created_at=" + created_at +
                '}';
    }
}
