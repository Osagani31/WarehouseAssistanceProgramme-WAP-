package com.warehouse.project.controllers;

import com.warehouse.project.dto.LoginDTO;
import com.warehouse.project.dto.RegisterDTO;
import com.warehouse.project.dto.Response;
import com.warehouse.project.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid RegisterDTO registerDTO) {
        return ResponseEntity.ok(userService.registerUser(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.loginUser(loginDTO));
    }


}
