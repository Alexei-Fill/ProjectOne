package com.SpEx7.service;

import com.SpEx7.DAO.UserDAO;
import com.SpEx7.entity.User;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean authorization(User user) {
        return userDAO.authorization(user);
    }
}
