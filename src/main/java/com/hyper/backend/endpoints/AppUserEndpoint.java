package com.hyper.backend.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyper.backend.exception.IllegalOperationException;
import com.hyper.backend.model.Appuser;
import com.hyper.backend.service.appuser.IAppUserService;

import java.security.Principal;
import java.util.List;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AppUserEndpoint {

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
    
}
