package com.example.mbonespring.models.interfaces;

import com.example.mbonespring.models.entities.DomainesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DomainesRepository extends JpaRepository<DomainesEntity, Integer>, JpaSpecificationExecutor<DomainesEntity> {

}