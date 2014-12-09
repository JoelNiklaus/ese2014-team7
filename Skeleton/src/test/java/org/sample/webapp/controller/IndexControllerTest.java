package org.sample.webapp.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.sample.controller.IndexController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.UpdateService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class IndexControllerTest {

	@Mock
	LoginService loginService;
	@Mock
	UpdateService updateService;
	
	@InjectMocks
	IndexController indexController;

    private MockMvc mockMvc;
	
    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setSuffix(".jsp");
 
        this.mockMvc = MockMvcBuilders.standaloneSetup(new IndexController())
                                 .setViewResolvers(viewResolver)
                                 .build();

    }
    
    @Test
    public void testError404() throws Exception {

        this.mockMvc.perform(get("404"));

    }
    
    @Test
    public void testSecurityError() throws Exception {

        this.mockMvc.perform(get("/security-error"));

    }

}
