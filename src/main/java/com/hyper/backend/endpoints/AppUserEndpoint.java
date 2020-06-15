package com.hyper.backend.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyper.backend.model.Appuser;
import com.hyper.backend.service.IAppUserAuthentificationService;
import com.hyper.backend.service.IAppUserService;

import java.util.List;



@RestController
public class AppUserEndpoint {

	@Autowired
	IAppUserAuthentificationService authentication;

    @Autowired
    private IAppUserService appUserService;

    @GetMapping("/users")
    public List<Appuser> listUsers() {

    	List<Appuser> appusers = (List<Appuser>) appUserService.findAll();
        return appusers;
    }
    
    @GetMapping("/users/{id}")
    public Appuser retrieveAppuser(@PathVariable Integer id) {

    	Appuser appuser = (Appuser) appUserService.findById(id);
        return appuser;
    }
    
    @PostMapping("/public/login")
    public String login(
    		@RequestParam("username") final String username,
    	    @RequestParam("password") final String password) {
    	System.out.println("LOGGGING IN -------------");
        return authentication
          .login(username, password)
          .orElseThrow(() -> new RuntimeException("invalid login and/or password"))
          .toString();
    }
    
    @GetMapping("/logout")
    boolean logout(@AuthenticationPrincipal final Appuser user) {
      authentication.logout(user);
      return true;
    }
}
