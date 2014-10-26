package org.sample.controller;

import org.sample.controller.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProfileController {

    @Autowired
    LoginService loginService;
    
}
