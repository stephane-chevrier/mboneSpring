package com.example.mbonespring.models.interfaces;

import com.example.mbonespring.models.entities.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, Integer>{
    ClientsEntity findByUserId(int id);
}