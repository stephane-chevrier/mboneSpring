package com.example.mbonespring;

import com.example.mbonespring.models.interfaces.UserRepository;
import com.example.mbonespring.models.entities.UserEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ClientDefault {
    @Autowired
    UserRepository userRepo;
    @PostConstruct
    public void populateUserDatabase()
    {
        Iterable<UserEntity> userIt = userRepo.findAll();

        if ( ! userIt.iterator().hasNext())
        {
            String[][] users = {
                    {"client", "jesuisleroi", "ROLE_CLIENT"},
                    {"Panzani", "torti", "ROLE_CLIENT"},
                    {"SNCF", "tgv", "ROLE_CLIENT"},
                    {"LaPoste", "timbre", "ROLE_CLIENT"}
            };

            for ( String[] userdat: users)
            {
                String pass = new BCryptPasswordEncoder().encode(userdat[1]);
                UserEntity userE = new UserEntity();
                userE.setUsername(userdat[0]);
                userE.setPassword(pass);
                userE.addGrantedAuthoity(userdat[2]);
                userE.setAccountNonLocked(true);
                userE.setEnabled(true);
                userE.setAccountNotExpired(true);
                userE.setCredentialsNonExpired(true);
                userRepo.save(userE);
            }
        }
    }
    @PostConstruct
    public void populateDatabase()
    {

    }
}
