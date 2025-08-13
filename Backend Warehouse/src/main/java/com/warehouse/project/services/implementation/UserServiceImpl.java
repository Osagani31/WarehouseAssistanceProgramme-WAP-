package com.warehouse.project.services.implementation;

import com.warehouse.project.dto.LoginDTO;
import com.warehouse.project.dto.RegisterDTO;
import com.warehouse.project.dto.Response;
import com.warehouse.project.dto.UserDTO;
import com.warehouse.project.enums.UserRole;
import com.warehouse.project.exceptions.InvalidCredentialException;
import com.warehouse.project.exceptions.NotFoundException;
import com.warehouse.project.models.User;
import com.warehouse.project.repositories.UserRepository;
import com.warehouse.project.security.JwtUtils;
import com.warehouse.project.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;


    @Override
    public Response registerUser(RegisterDTO registerDTO) {

        UserRole role = UserRole.Super_Admin;

        if (registerDTO.getRole() != null) {
            role = registerDTO.getRole();
        }

        User userToSave =User.builder()
                .fullName(registerDTO.getFullName())
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .phoneNumber(registerDTO.getPhoneNumber())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(role)
                .build();

        userRepository.save(userToSave);

        return Response.builder()
                .status(200)
                .message("User registered successfully")
                .build();

    }

    @Override
    public Response loginUser(LoginDTO loginDTO) {
        User user=userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow( ()->new NotFoundException("Email Not found"));

        if ((!passwordEncoder.matches(loginDTO.getPassword(),user.getPassword()))){
            throw new InvalidCredentialException("Password Incorrect");
        }

        String token=jwtUtils.generateToken(user.getEmail());

        return Response.builder()
                .status(200)
                .message("User Logged Successfully")
                .role(user.getRole())
                .token(token)
                .expirationTime("6 Months")
                .build();
    }

    @Override
    public Response getAllUsers() {
       List<User> users=userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

       users.forEach(user->user.setTransactions(null));

       List<UserDTO> userDTOs= modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());

       return Response.builder()
               .status(200)
               .message("Successfully all users received")
               .users(userDTOs)
               .build();
    }

    @Override
    public User getCurrentLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =authentication.getName();
        User user =userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("Email Not found"));
        user.setTransactions(null);
        return user;
    }

    @Override
    public Response getUserById(Long id) {
        User user=userRepository.findById(id).orElseThrow( ()->new NotFoundException("User not found") );

        UserDTO userDTO=modelMapper.map(user,UserDTO.class);

        userDTO.setTransactions(null);

        return Response.builder()
                .status(200)
                .message("Successfully  user received")
                .user(userDTO)
                .build();
    }

    @Override
    public Response updateUser(Long id, UserDTO userDTO) {

        User existuser=userRepository.findById(id).orElseThrow( ()->new NotFoundException("User not found") );

        if (userDTO.getEmail() != null) {
            existuser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPhoneNumber() != null) {
            existuser.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (userDTO.getFullName() != null) {
            existuser.setFullName(userDTO.getFullName());
        }
        if (userDTO.getRole() != null) {
            existuser.setRole(userDTO.getRole());
        }

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existuser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepository.save(existuser);

        return Response.builder()
                .status(200)
                .message("Successfully user updated")
                .build();
    }

    @Override
    public Response deleteUser(Long id) {
        User user=userRepository.findById(id).orElseThrow( ()->new NotFoundException("User not found") );
        userRepository.delete(user);

        return Response.builder()
                .status(200)
                .message("Successfully user deleted")
                .build();
    }

    @Override
    public Response getUserTransactions(Long id) {

        User user=userRepository.findById(id).orElseThrow( ()->new NotFoundException("User not found") );

        UserDTO userDTO=modelMapper.map(user,UserDTO.class);
        userDTO.getTransactions().forEach(transactionDTO->{
            transactionDTO.setUser(null);
            transactionDTO.setSupplier(null);
        });

        return Response.builder()
                .status(200)
                .message("Successfully retrieved")
                .user(userDTO)
                .build();
    }


}
