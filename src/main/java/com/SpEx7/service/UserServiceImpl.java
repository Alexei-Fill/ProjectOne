package com.SpEx7.service;

import com.SpEx7.DAO.UserDAO;
import com.SpEx7.entity.PortalUser;
import com.SpEx7.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
//
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

       @Override
        public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
           PortalUser portalUser = userDAO.loadUserByUsername(login);
           if (portalUser != null) {
               return new UserPrincipal(portalUser);
           } else {
               throw new UsernameNotFoundException("User not found");
           }
    }

    public PortalUser getUserByUsername(String login) throws UsernameNotFoundException {
        PortalUser portalUser = userDAO.loadUserByUsername(login);
        if (portalUser != null) {
            return portalUser;
        } else {
            throw new UsernameNotFoundException("User not found");
        }

    }

     public void registration(PortalUser portalUser) {
        PortalUser checkUser = userDAO.loadUserByUsername(portalUser.getLogin());
        if (checkUser == null){
            portalUser.setPassword(passwordEncoder.encode(portalUser.getPassword()));
            userDAO.addUser(portalUser);
        } else System.out.println("User exist");
    }

    public void updateToken(String username, String token) {
        userDAO.updateToken(username, token);
    }

    public PortalUser getUserByToken(String accessToken) {
        return userDAO.loadUserByToken(accessToken);
    }
}
