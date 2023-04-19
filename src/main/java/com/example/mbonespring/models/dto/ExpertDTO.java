package com.example.mbonespring.models.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
public class ExpertDTO {

    private String nom;
    private String prenom;
    private String urlPhoto;
    private int cout;
    private ArrayList<DomainesNiveaux> domaines;
}
