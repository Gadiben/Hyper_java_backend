package com.hyper.backend.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import com.hyper.backend.endpoints.AuthenticationEndpoint;
import com.hyper.backend.service.authentication.IAppUserAuthentificationService;



@WebMvcTest(AuthenticationEndpoint.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")


public class AuthenticationEndpointTest {
	@Autowired
    private MockMvc mvc;
 
    @MockBean
	private IAppUserAuthentificationService authentication;


    @Test
    public void givenBadCredentials_PostLogin_ReturnErro() 
    		throws Exception {
    	
    	Mockito.when(authentication.login("geok"," ")).thenReturn(Optional.empty());
		mvc.perform(post("/login")
				.accept(MediaType.APPLICATION_JSON)
				.param("username", "geok")
				.param("password", " "))
				.andExpect(status().is4xxClientError());
    }
    
    @Test
    public void givenGoodCredentials_PostLogin_ReturnOK() 
    		throws Exception {
    	
    	Mockito.when(authentication.login("geok","abc")).thenReturn(Optional.of("Pass"));
		mvc.perform(post("/login")
				.accept(MediaType.APPLICATION_JSON)
				.param("username", "geok")
				.param("password", "abc"))
				.andExpect(status().isOk());
    }
    
    @Test
    public void PostLogout_ReturnOk() throws Exception{
		mvc.perform(post("/logout")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
    }
    
}
