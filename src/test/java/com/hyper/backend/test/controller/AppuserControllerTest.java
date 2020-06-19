package com.hyper.backend.test.controller;

import static org.mockito.Mockito.*;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hyper.backend.TestUtil;
import com.hyper.backend.endpoints.AppUserEndpoint;
import com.hyper.backend.model.Appuser;
import com.hyper.backend.service.appuser.IAppUserService;
import com.hyper.backend.service.authentication.IAppUserAuthentificationService;


@WebMvcTest(AppUserEndpoint.class)
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
public class AppuserControllerTest {
	
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private IAppUserService appUserService;
    
    @MockBean
	private IAppUserAuthentificationService authentication;
    
    private Appuser appuser;
    private Appuser appuser2;
    @Before
    public void setUp() {
    	Date now = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	Appuser appuser = new Appuser((Integer) 0, now,"goek@email.com","male",
    			new BigDecimal(0.0),new BigDecimal(0.0),"goek","123456");
    	Appuser appuser2 = new Appuser((Integer) 1, now,"goek@email.com","male",
    			new BigDecimal(0.0),new BigDecimal(0.0),"goek2","123456");
    	this.appuser = appuser;
    	this.appuser2 = appuser2;
    	List<Appuser> allUsers= Arrays.asList(appuser);


        Mockito.when(appUserService.findAll())
        	.thenReturn(allUsers);
        Mockito.when(appUserService.findById(this.appuser.getId()))
        	.thenReturn(this.appuser);
        Mockito.when(appUserService.save(any(Appuser.class)))
    		.thenReturn(this.appuser);
        Mockito.when(appUserService.update(any(Appuser.class)))
			.thenReturn(this.appuser);

    }

    @Test
    public void givenAppusers_whenGetAppusers_ReturnJsonArray() 
    		throws Exception {
    	
        
		mvc.perform(get("/users")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].pseudo").value(this.appuser.getPseudo()))
				.andExpect(jsonPath("$[0].id").value(this.appuser.getId()))
		    	.andExpect(status().isOk());
    }
    
    @Test
    public void givenAppuser_whenGetAppuser_ReturnAppuser() 
    		throws Exception {
    	
        
		mvc.perform(get("/users/0")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("pseudo").value(this.appuser.getPseudo()))
				.andExpect(jsonPath("id").value(this.appuser.getId()))
		    	.andExpect(status().isOk());
    }
    
    @Test
    public void givenAppuser_whenPostAppuser_ReturnAppuser() 
    		throws Exception {
    	
        
		mvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
		    	.content(TestUtil.convertObjectToJsonBytes(this.appuser)))
				.andExpect(jsonPath("pseudo").value(this.appuser.getPseudo()))
				.andExpect(jsonPath("id").value(this.appuser.getId()))
		    	.andExpect(status().isOk());
    }
    
    @Test
    public void givenAppuser_whenPatchAppuser_ReturnAppuser() 
    		throws Exception {
    	UsernamePasswordAuthenticationToken mockPrincipal = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.when(mockPrincipal.getPrincipal()).thenReturn(this.appuser);
    	
        
		mvc.perform(patch("/users/0")
				.contentType(MediaType.APPLICATION_JSON)
				.principal((Principal)mockPrincipal)
				.content(TestUtil.convertObjectToJsonBytes(this.appuser)))
				.andExpect(jsonPath("pseudo").value(this.appuser.getPseudo()))
				.andExpect(jsonPath("id").value(this.appuser.getId()))
		    	.andExpect(status().isOk());
    }
    @Test
    public void givenAppuser_whenPatchWrongAppuser_ReturnIllegalOperation() 
    		throws Exception {
    	UsernamePasswordAuthenticationToken mockPrincipal = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.when(mockPrincipal.getPrincipal()).thenReturn(this.appuser);
    	
        
		mvc.perform(patch("/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.principal((Principal)mockPrincipal)
				.content(TestUtil.convertObjectToJsonBytes(this.appuser)))
		    	.andExpect(status().is4xxClientError());
    }
    @Test
    public void givenAppuser_whenPatchWrongLoggedInAppuser_ReturnIllegalOperation() 
    		throws Exception {

		UsernamePasswordAuthenticationToken mockPrincipal = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.when(mockPrincipal.getPrincipal()).thenReturn(this.appuser2);
        
        mvc.perform(patch("/users/0")
				.contentType(MediaType.APPLICATION_JSON)
				.principal((Principal)mockPrincipal)
				.content(TestUtil.convertObjectToJsonBytes(this.appuser)))
		    	.andExpect(status().is4xxClientError());
    }
    
    @Test
    public void givenAppuser_whenPatchWrongLoggedInForWrongAppuser_ReturnIllegalOperation() 
    		throws Exception {

		UsernamePasswordAuthenticationToken mockPrincipal = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.when(mockPrincipal.getPrincipal()).thenReturn(this.appuser2);
        
        mvc.perform(patch("/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.principal((Principal)mockPrincipal)
				.content(TestUtil.convertObjectToJsonBytes(this.appuser)))
		    	.andExpect(status().is4xxClientError());
    }
}
