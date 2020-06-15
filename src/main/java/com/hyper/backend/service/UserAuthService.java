package com.hyper.backend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyper.backend.model.Appuser;

@Service
public class UserAuthService implements IAppUserAuthentificationService {

	@Autowired
    private IAppUserService appUserService;
	
	Map<String, Appuser> loggedInUsers = new HashMap<>();
	
	@Override
	public Optional<String> login(final String username, final String password) {
		Appuser user = appUserService.loadUserByUsername(username);
		if (password.equals(user.getPassword())){
			final String uuid = UUID.randomUUID().toString();
			this.loggedInUsers.put(uuid,user);
			return Optional.of(uuid);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Appuser> findByToken(final String token) {
		return Optional.ofNullable(this.loggedInUsers.get(token));
	}

	@Override
	public void logout(Appuser user) {
		this.loggedInUsers.entrySet().removeIf(entry -> user.equals(entry.getValue()));
	}

}
