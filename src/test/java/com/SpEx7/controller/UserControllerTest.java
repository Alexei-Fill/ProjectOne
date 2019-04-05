package com.SpEx7.controller;

import com.SpEx7.entity.PortalUser;
import com.SpEx7.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController = new UserController();
    private UserServiceImpl userService = mock(UserServiceImpl.class);
    private MockMvc mockMvc;


    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        this.mockMvc = standaloneSetup(userController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void ShowLogin_returnViewLogin() throws Exception {
        mockMvc.perform(get("/showLogin"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void ShowReg_returnViewRegistrationAndNewEmptyUser() throws Exception {
        mockMvc.perform(get("/showReg"))
                .andExpect(status().isOk())
                .andExpect(view().name("reg"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void SignOut_loginAndPasswordNotEmpty_registrationUserAndRedirectToLoginPage() throws Exception {
        PortalUser testUser = new PortalUser(0, "tetLogin", "testPass", null);

        mockMvc.perform(post("/reg")
                .param("login", "tetLogin")
                .param("password", "testPass"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(" /showLogin"))
                .andExpect(model().attribute("user", testUser));
        verify(userService).registration(testUser);
    }

    @Test
    public void Forbidden_returnForbiddenPage() throws Exception {
        mockMvc.perform(get("/forbidden"))
                .andExpect(status().isOk())
                .andExpect(view().name("forbidden"));
    }
}
