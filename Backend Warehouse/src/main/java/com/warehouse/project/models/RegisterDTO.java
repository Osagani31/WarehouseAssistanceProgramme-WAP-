package com.warehouse.project.models;

import com.warehouse.project.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank(message = "Full Name is required")
    private String fullName;

    @NotBlank(message = "Name is required")
    private String username;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;

    private UserRole role;


}
