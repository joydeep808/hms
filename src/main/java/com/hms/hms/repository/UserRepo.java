package com.hms.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.hms.entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  @Query(value = "SELECT  email from user where email = :email" ,nativeQuery = true)
  Optional<User> findByUserEmail(@Param("email") String email); // Find user by username



  Optional<User> findByEmail(String email);
  
}