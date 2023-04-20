package com.example.mbonespring.models.interfaces;

import com.example.mbonespring.models.entities.ExpertsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertsRepository extends JpaRepository<ExpertsEntity, Integer>{

}