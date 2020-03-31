package com.example.demo.dao;

import com.example.demo.Entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationRepository extends JpaRepository<Operation,Long> {

    @Query("select o from Operation o where o.compte.codeCompte=:x ORDER BY o.date desc")
    public Page<Operation> listOperations (@Param("x") String codeCopte , Pageable pageable);

}
