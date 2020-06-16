package com.hyper.backend.service;

import java.util.List;
import java.util.Optional;

import com.hyper.backend.model.Appuser;

public interface IAppUserService {
	
	List<Appuser> findAll();
	Appuser findById(Integer id);
	Appuser loadUserByUsername(String name);
	Appuser update(Appuser appuser);
}
