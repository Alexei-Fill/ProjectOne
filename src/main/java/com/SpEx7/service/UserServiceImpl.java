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
           System.out.println(portalUser.getPassword());
//           User.UserBuilder userBuilder = null;
           if (portalUser != null) {
//               userBuilder = User.withUsername(login);
//               userBuilder.password(portalUser.getPassword());
               System.out.println("after if       " + portalUser.getPassword());
               System.out.println("before ret");
               return new UserPrincipal(portalUser);
           } else {
               throw new UsernameNotFoundException("User not found");
           }

    }

     public void registration(PortalUser portalUser) {
         System.out.println(portalUser.toString() + " reg0");
        PortalUser checkUser = userDAO.loadUserByUsername(portalUser.getLogin());
         System.out.println(portalUser.toString() + " reg1");
        if (checkUser == null){
            portalUser.setPassword(passwordEncoder.encode(portalUser.getPassword()));
            System.out.println(portalUser.toString() + " reg2");
            userDAO.addUser(portalUser);
            System.out.println(portalUser.toString() + " reg3");
        } else System.out.println("User exist");
    }
}
