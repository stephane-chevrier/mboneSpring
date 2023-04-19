package com.example.mbonespring.models.interfaces;

import com.example.mbonespring.models.entities.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClientsRepository extends JpaRepository<ClientsEntity, Integer>, JpaSpecificationExecutor<ClientsEntity>  {
    ClientsEntity findByUserId(int id);
}