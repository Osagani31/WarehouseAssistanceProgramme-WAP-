package com.warehouse.project.services;

import com.warehouse.project.dto.LoginDTO;
import com.warehouse.project.dto.RegisterDTO;
import com.warehouse.project.dto.Response;
import com.warehouse.project.dto.UserDTO;
import com.warehouse.project.models.User;

public interface UserService {

    Response registerUser(RegisterDTO registerDTO);

    Response loginUser(LoginDTO loginDTO);

    Response getAllUsers();

    User getCurrentLoggedInUser();

    Response getUserById(Long id);

    Response updateUser(Long id, UserDTO userDTO);

    Response deleteUser(Long id);

    Response getUserTransactions(Long id);

}
