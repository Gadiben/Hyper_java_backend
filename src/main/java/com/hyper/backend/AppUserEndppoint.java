package com.hyper.backend;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hyper.backend.model.Appuser;
import com.hyper.backend.service.IAppUserService;

import java.util.List;

@RestController
public class AppUserEndppoint {

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
}
