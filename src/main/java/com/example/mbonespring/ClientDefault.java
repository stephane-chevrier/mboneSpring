package com.example.mbonespring;

import org.springframework.beans.factory.annotation.Autowired;

public class ClientDefault {
    @Autowired
    UserRepository userRepo;
    private void populateUserDatabase()
    {
        Iterable<UserEntity> userIt = userDAO.findAll();

        if ( ! userIt.iterator().hasNext())
        {
            String[][] users =  {{"client","jesuisleroi","ROLE_CLIENT"}};
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

}
