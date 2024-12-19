package com.hms.hms.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.hms.entity.User;
import com.hms.hms.repository.UserRepo;
import com.hms.hms.util.Response;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepository;

    private final BCryptPasswordEncoder passwordEncoder; // For encoding passwords

    /**
     * Register a new user
     * @param user the user object to register
     * @return ResponseEntity with a success or error response
     */
    @Transactional
    public ResponseEntity<Response<User>> registerUser(User user) {
        // Check if the user already exists by username or email

        if (userRepository.findByUserEmail(user.getEmail()).isPresent()) {
            return Response.sendErrorResponse("Email is already exist", HttpStatus.BAD_REQUEST.value());
        }
        // Encode the password before saving to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user
        User savedUser = userRepository.save(user);

        return Response.sendSuccessResponse("User registered successfully", savedUser);
    }

    /**
     * Fetch user details by username
     * @param username the username of the user
     * @return ResponseEntity with user details or error if not found
     */
    public ResponseEntity<Response<User>> getUserByEmail(String email) {
        Optional<User> userOpt = userRepository.findByUserEmail(email);

        if (userOpt.isPresent()) {
            return Response.sendSuccessResponse("User details retrieved successfully", userOpt.get());
        } else {
            return Response.sendErrorResponse("User not found", HttpStatus.NOT_FOUND.value());
        }
    }

    /**
     * Update user details
     * @param username the username of the user to update
     * @param updatedUser the new details for the user
     * @return ResponseEntity with success or error response
     */
    @Transactional
    public ResponseEntity<Response<User>> updateUser(String email, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findByEmail(email);

        if (!existingUserOpt.isPresent()) {
            return Response.sendErrorResponse("User not found", HttpStatus.NOT_FOUND.value());
        }

        User existingUser = existingUserOpt.get();
        // Update user details as needed
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        // existingUser.setRole(updatedUser.getRole());

        // Save the updated user
        User updated = userRepository.save(existingUser);

        return Response.sendSuccessResponse("User updated successfully", updated);
    }

    /**
     * Authenticate user by username and password
     * @param username the username
     * @param password the password
     * @return ResponseEntity with authentication result
     */
    public ResponseEntity<Response<String>> authenticateUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (!userOpt.isPresent()) {
            return Response.sendErrorResponse("Invalid username or password", HttpStatus.UNAUTHORIZED.value());
        }

        User user = userOpt.get();

        // Verify password using BCrypt
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return Response.sendErrorResponse("Invalid username or password", HttpStatus.UNAUTHORIZED.value());
        }

        // Authentication successful
        return Response.sendSuccessResponse("Authentication successful", "Token or Session can be generated here.");
    }
}