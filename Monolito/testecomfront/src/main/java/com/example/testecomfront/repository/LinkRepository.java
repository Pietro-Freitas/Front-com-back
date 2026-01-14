package com.example.testecomfront.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testecomfront.entities.Link;

public interface LinkRepository extends JpaRepository<Link, Long>{
 
    public List<Link> findByUserId(Long id);
}
