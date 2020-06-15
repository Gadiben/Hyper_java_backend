package com.hyper.backend.service;

import java.util.Optional;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hyper.backend.exception.NotFoundException;
import com.hyper.backend.model.Appuser;
import com.hyper.backend.repositories.AppUserRepo;

@Service
public class AppUserService implements IAppUserService, UserDetailsService {
	
	@Autowired
	private AppUserRepo appuserRepository;
	
	@Override
	public List<Appuser> findAll(){
		List<Appuser> appusers = (List<Appuser>) appuserRepository.findAll();
		
		return appusers;
	}

	@Override
	public Appuser findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Appuser> appuser = appuserRepository.findById(id);
		if (appuser.isPresent()){
			return appuser.get();
		}else {
			throw new NotFoundException(id, "app user");
		}
	}

	@Override
	public Appuser loadUserByUsername(String username) throws UsernameNotFoundException {
		Objects.requireNonNull(username);
        Appuser user = appuserRepository.findUserWithName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
	}

}
