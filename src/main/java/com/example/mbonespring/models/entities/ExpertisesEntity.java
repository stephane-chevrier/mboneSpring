package com.example.mbonespring.models.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "expertises")
public class ExpertisesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "expert_id")
    private Integer expertId;

    @Column(name = "domaine_id")
    private Integer domaineId;

    @Column(name = "niveau_id")
    private Integer niveauId;

}
