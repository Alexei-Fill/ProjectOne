package com.SpEx7.service;

import com.SpEx7.DAO.UserDAO;
import com.SpEx7.entity.PortalUser;
import com.SpEx7.security.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDAO userDAOMock;
    @Mock
    private BCryptPasswordEncoder passwordEncoderMock;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void loadUserByUsername_whenUserExist_shouldReturnUserDetail() {
        PortalUser testUser = new PortalUser().setId(1).setLogin("testLogin").setPassword("testPassword").setToken("testToken");
        UserDetails testUserDetail = new UserPrincipal(testUser);
        when(userDAOMock.loadUserByUsername("testLogin")).thenReturn(testUser);

        UserDetails resultUserDetail = userService.loadUserByUsername("testLogin");

        assertEquals(testUserDetail, resultUserDetail);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_whenUserDosntExist_shouldReturnException() {
        UserDetails testUserDetail = new UserPrincipal(null);
        when(userDAOMock.loadUserByUsername("testLogin")).thenReturn(null);

        UserDetails resultUserDetail = userService.loadUserByUsername("testLogin");

        assertEquals(testUserDetail, resultUserDetail);
    }

    @Test
    public void getUserByUsername_whenUserExist_shouldReturnUser() {
        PortalUser testUser = new PortalUser().setId(1).setLogin("testLogin").setPassword("testPassword").setToken("testToken");
        when(userDAOMock.loadUserByUsername("testLogin")).thenReturn(testUser);

        PortalUser resultUser = userService.getUserByUsername("testLogin");

        assertEquals(testUser, resultUser);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void getUserByUsername_whenUserDosntExist_shouldReturnException() {
        when(userDAOMock.loadUserByUsername("testLogin")).thenReturn(null);

        PortalUser resultUser = userService.getUserByUsername("testLogin");

        assertEquals(null, resultUser);
    }

    @Test
    public void registration() {
        PortalUser testUser = new PortalUser().setId(1).setLogin("testLogin").setPassword("testPassword").setToken("testToken");
        when(userDAOMock.loadUserByUsername(anyString())).thenReturn(null);
        when(passwordEncoderMock.encode(testUser.getPassword())).thenReturn(testUser.getPassword());

        userService.registration(testUser);

        verify(userDAOMock).loadUserByUsername(anyString());
        verify(userDAOMock).addUser(testUser);
    }

    @Test
    public void updateToken() {
      String testUsername = "someUser";
      String testToken = "someToken";

      userService.updateToken(testUsername, testToken);

      verify(userDAOMock).updateToken(testUsername, testToken);
    }

    @Test
    public void getUserByToken() {
        String testToken = "testToken";
        PortalUser testUser = new PortalUser().setId(1).setLogin("testLogin").setPassword("testPassword").setToken(testToken);
        when(userDAOMock.loadUserByToken(testToken)).thenReturn(testUser);
        PortalUser resultUser = userDAOMock.loadUserByToken(testToken);

        assertEquals(testUser, resultUser);
    }
}
