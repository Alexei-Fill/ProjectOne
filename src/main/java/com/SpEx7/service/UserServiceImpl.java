package com.SpEx7.service;

import com.SpEx7.DAO.UserDAO;
import com.SpEx7.entity.PortalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

       @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
           PortalUser portalUser = userDAO.loadUserByUsername(login);
           User.UserBuilder userBuilder = null;
           if (portalUser != null) {
               userBuilder = User.withUsername(login);
               userBuilder.password(portalUser.getPassword());
           } else {
               throw new UsernameNotFoundException("User not found");
           }
        return userBuilder.build();
    }
}
