package com.SpEx7.DAO;

import com.SpEx7.entity.PortalUser;

public interface UserDAO {
    PortalUser loadUserByUsername (String login);

    void addUser(PortalUser portalUser);
}
