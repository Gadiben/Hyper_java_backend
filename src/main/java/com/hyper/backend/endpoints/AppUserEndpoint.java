package com.hyper.backend.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyper.backend.exception.IllegalOperationException;
import com.hyper.backend.model.Appuser;
import com.hyper.backend.service.IAppUserAuthentificationService;
import com.hyper.backend.service.IAppUserService;

import java.security.Principal;
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
    
    
    @PatchMapping("/users/{id}")
    public Appuser patchAppuser(@PathVariable Integer id, @RequestBody Appuser modifiedAppuser, Principal principal) {

    	Appuser authenticatedUser = (Appuser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    	if( !(id==authenticatedUser.getId()) || !(id == modifiedAppuser.getId())) {
    		throw new IllegalOperationException	();
    	}
        return appUserService.update(modifiedAppuser);
    }
    
    @PostMapping("/users")
    public Appuser postAppUser(@RequestBody Appuser modifiedAppuser) {
    	return appUserService.save(modifiedAppuser);
    }
    
    @PostMapping("/login")
    public String login(
    		@RequestParam("username") final String username,
    	    @RequestParam("password") final String password) {
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
