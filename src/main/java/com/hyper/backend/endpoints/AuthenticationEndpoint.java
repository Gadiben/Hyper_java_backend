package com.hyper.backend.endpoints;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyper.backend.exception.IllegalOperationException;
import com.hyper.backend.model.Appuser;
import com.hyper.backend.service.authentication.IAppUserAuthentificationService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationEndpoint {

	@Autowired
	private IAppUserAuthentificationService authentication;


    @PostMapping("/login")
    public Map<String, String> login(
    		@RequestParam("username") final String username,
    	    @RequestParam("password") final String password) {
    	

        String token =  authentication
          .login(username, password)
          .orElseThrow(() -> new IllegalOperationException("invalid login and/or password"))
          .toString();
        Map<String, String> jo = new HashMap<String,String>();
        jo.put("token", token);
        return jo;
    }
    
    @PostMapping("/logout")
    boolean logout(@AuthenticationPrincipal final Appuser user) {
      authentication.logout(user);
      return true;
    }
}
