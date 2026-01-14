package com.example.testecomfront.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testecomfront.entities.Link;
import com.example.testecomfront.entities.User;

public interface LinkRepository extends JpaRepository<Link, Long>{
 
    public List<Link> findByUserId(Long id);
    public List<Link> findByUser(User user);
}
