package com.example.testecomfront.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testecomfront.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
