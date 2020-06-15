package com.hyper.backend.service;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyper.backend.exception.NotFoundException;
import com.hyper.backend.model.Appuser;
import com.hyper.backend.repositories.AppUserRepo;

@Service
public class AppUserService implements IAppUserService{
	
	@Autowired
	private AppUserRepo repository;
	
	@Override
	public List<Appuser> findAll(){
		List<Appuser> appusers = (List<Appuser>) repository.findAll();
		
		return appusers;
	}

	@Override
	public Appuser findById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Appuser> appuser = repository.findById(id);
		if (appuser.isPresent()){
			return appuser.get();
		}else {
			throw new NotFoundException(id);
		}
	}

}
