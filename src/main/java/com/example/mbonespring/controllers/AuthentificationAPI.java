package com.example.mbonespring.controllers;

import com.example.mbonespring.ClientDefault;
import com.example.mbonespring.models.dto.DomainesNiveaux;
import com.example.mbonespring.models.dto.ExpertDTO;
import com.example.mbonespring.models.dto.UserDTO;
import com.example.mbonespring.models.entities.*;
import com.example.mbonespring.models.interfaces.*;
import com.example.mbonespring.services.AuthentificationRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;

import javax.validation.Valid;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthentificationAPI {

    private final ClientDefault clientDefault;
   // @Bean

    private final AuthenticationManager authenticationManager;
    private static final String SECRET = "mypersonnalveryveryverysecretsecuritytokensecuritysauceforourangularproject";

    private static final Long TOKEN_VALIDITY = 36000 * 1000L;

    @PostConstruct
    private void initClient(){
        clientDefault.populateUserDatabase();
    }

    @GetMapping("/test")
    public String test(){
        return "test reussi";
    }

    @GetMapping("/test2")
    public String test2(){
        return "test avec tokken reussi";
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid AuthentificationRequest request){

        try {
            final Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword())
            );
            final UserEntity user = (UserEntity) authenticate.getPrincipal();
            final String token = Jwts.builder().setSubject(authenticate.getName())
                    .claim("authorities", authenticate.getAuthorities()
                            .stream().map(GrantedAuthority::getAuthority)
                            .toList())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                    .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
            //ResponseEntity<Integer> response = soldeClients();
            int credit = getSolde( user.getId().intValue());
            UserDTO userDTO = new UserDTO(user.getUsername(), token, credit);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION,"Bearer " + token)
                    .body(userDTO);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Methode de recuperation du solde d'un client
     * @return ResponseEntity<Integer>
     */
    @Autowired
    ClientsRepository clientsRepository;
    @GetMapping("/clients/solde")
    public ResponseEntity<Integer> soldeClients() {
        final var userDetails = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            return ResponseEntity.ok().body(clientsRepository.findByUserId(userDetails.getId().intValue()).getSolde());
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    private  int getSolde( int userid )
    {
        try {
            ClientsEntity userDTO = clientsRepository.findByUserId(userid);
            int solde = (userDTO==null)?-1:userDTO.getSolde();
            return solde;

        } catch (BadCredentialsException ex) {
            return -1;
        }

    }
    /**
     * Methode de recuperation du solde d'un client
     * @return ResponseEntity<Integer>
     */
    @Autowired
    ExpertsRepository expertsRepository;
    @Autowired
    DomainesRepository domainesRepository;
    @Autowired
    NiveauxRepository niveauxRepository;
    @Autowired
    ExpertisesRepository expertisesRepository;
    @GetMapping("/liste/experts")
    public ResponseEntity<List<ExpertDTO>> listeExperts() {
        // initialisation de la liste qui sera retournee
        List<ExpertDTO> expertDTOList = new ArrayList<>();
        try {
            // telechargement des tables experts et expertises
            List<ExpertsEntity> experts = expertsRepository.findAll();
            List<ExpertisesEntity> expertises = expertisesRepository.findAll();
            // boucle sur la table des experts
            for (ExpertsEntity e : experts) {
                ExpertDTO expertDTO = new ExpertDTO();
                expertDTO.setNom(e.getNom());
                expertDTO.setPrenom(e.getPrenom());
                expertDTO.setUrlPhoto(e.getUrlPhoto());
                expertDTO.setCout(e.getCout());
                // initialisation de la liste des domaines/niveaux de l'expert
                ArrayList<DomainesNiveaux> listDomainesNiveaux = new ArrayList();
                // boucle sur la table expertise
                for (ExpertisesEntity f : expertises) {
                    // si l'expert est present dans la ligne d'expertise
                    if (e.getId()==f.getExpertId()) {
                        DomainesNiveaux domainesNiveaux = new DomainesNiveaux();
                        domainesNiveaux.setDomaine(domainesRepository.findById(f.getDomaineId()).get().getNom());
                        domainesNiveaux.setNiveau(niveauxRepository.findById(f.getNiveauId()).get().getNom());
                        // Ajout de la liste domaine/niveaux
                        listDomainesNiveaux.add(domainesNiveaux);
                    }
                }
                // Maj de l'expert avec la liste des domaines/niveaux
                expertDTO.setDomaines(listDomainesNiveaux);
                // rajout de l'expert à la liste
                expertDTOList.add(expertDTO);
            }
            // retour de la liste construite
            return ResponseEntity.ok().body(expertDTOList);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}