package com.SpEx7.DAO;

import com.SpEx7.entity.PortalUser;

public interface UserDAO {
    PortalUser loadUserByUsername(String login);

    void addUser(PortalUser portalUser);

    void updateToken(String username, String token);

    PortalUser loadUserByToken(String accessToken);
}
