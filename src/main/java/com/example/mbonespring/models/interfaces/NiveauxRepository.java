package com.example.mbonespring.models.interfaces;

import com.example.mbonespring.models.entities.NiveauxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NiveauxRepository extends JpaRepository<NiveauxEntity, Integer>, JpaSpecificationExecutor<NiveauxEntity> {

}