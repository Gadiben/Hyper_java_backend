package com.hyper.backend.service.appuser;

import java.util.Optional;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hyper.backend.exception.IllegalOperationException;
import com.hyper.backend.exception.NotFoundException;
import com.hyper.backend.model.Appuser;
import com.hyper.backend.repositories.AppUserRepo;
import com.hyper.backend.utils.NotNullBeanUtils;

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
	
	@Override
	public Appuser update(Appuser appuser) {
		Integer id = appuser.getId();
		Appuser originalUser = this.findById(appuser.getId());
		try {
			NotNullBeanUtils.copyProperties(appuser, originalUser);
			appuserRepository.save(originalUser);
			return originalUser;
		} catch (Exception e) {
			System.out.println(e);
			throw new NotFoundException(id, "app user");
		}
		
	}

	@Override
	public Appuser save(Appuser appuser) throws IllegalOperationException {
		List<Appuser> allUsers = this.findAll();
		int newId = allUsers.stream().map(user -> user.getId()).max(Integer::compare).orElse(-1)+1;
		appuser.setId(newId);
		try {
			appuserRepository.save(appuser);
		}catch (Exception e) {
			System.out.println(e);
			System.out.println(appuser.getEmail());
			throw new IllegalOperationException();
		}
		return appuser;
	}

}
