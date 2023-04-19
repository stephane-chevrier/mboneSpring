package com.example.mbonespring.models.interfaces;

import com.example.mbonespring.models.entities.ExpertisesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExpertisesRepository extends JpaRepository<ExpertisesEntity, Integer>, JpaSpecificationExecutor<ExpertisesEntity> {

}