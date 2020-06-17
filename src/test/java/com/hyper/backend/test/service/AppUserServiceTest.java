package com.hyper.backend.test.service;


import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hyper.backend.model.Appuser;
import com.hyper.backend.repositories.AppUserRepo;
import com.hyper.backend.service.AppUserService;

@RunWith(SpringRunner.class)
public class AppUserServiceTest {
	@TestConfiguration
    static class AppUserServiceTestContextConfiguration {
  
        @Bean
        public AppUserService employeeService() {
            return new AppUserService();
        }
    }
	
	@Autowired
    private AppUserService appuserService;
 
    @MockBean
    private AppUserRepo appuserRepository;
    
    @Before
    public void setUp() {
    	Date now = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    	Appuser appuser = new Appuser((Integer) 0, now,"goek@email.com","male",
    			new BigDecimal(0.0),new BigDecimal(0.0),"goek","123456");
     
        Mockito.when(appuserRepository.findUserWithName(appuser.getPseudo()))
          .thenReturn(Optional.of(appuser));
    }
    
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "goek";
        Appuser found = appuserService.loadUserByUsername(name);
      
         assertEquals(found.getPseudo(),name);
     }
}
