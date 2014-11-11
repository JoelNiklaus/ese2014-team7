 package org.sample.service;


import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.LoginServiceImpl;
import org.sample.model.User;
import org.sample.model.dao.UserDao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    private UserDao userDao;
    private LoginService loginServiceImpl;

    @Before
    public void doSetup() {
        userDao = mock(UserDao.class);
        loginServiceImpl = new LoginServiceImpl();
    }

    @Test
    public void testSaveForm() {

        SignupForm signupForm = new SignupForm();
        signupForm.setLastName("formLast");
        signupForm.setFirstName("formFirst");
        signupForm.setEmail("form@test.com");
        signupForm.setPassword("password");
        signupForm.setPasswordConfirm("password");
        
        when(userDao.save(any(User.class)))
                .thenAnswer(new Answer<User>() {
                    public User answer(InvocationOnMock invocation) throws Throwable {
                        User user = (User) invocation.getArguments()[0];
                        user.setId(1L);
                        return user;
                    }
                });

        assertNull(signupForm.getId());
        assertNotNull(signupForm.getId());
        assertTrue(signupForm.getId() > 0);
    }

    @Test(expected = InvalidUserException.class)
    public void testInvalidUserException() {
        SignupForm signupForm = new SignupForm();
        signupForm.setLastName("formLast");
        signupForm.setFirstName("ESE");
        signupForm.setEmail("form@test.com");

        loginServiceImpl.saveFrom(signupForm);
    }


}