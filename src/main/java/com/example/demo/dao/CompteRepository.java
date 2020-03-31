package com.example.demo.dao;

import com.example.demo.Entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CompteRepository extends JpaRepository<Compte,String>{

}