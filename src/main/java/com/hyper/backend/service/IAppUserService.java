package com.hyper.backend.service;

import java.util.List;

import com.hyper.backend.model.Appuser;

public interface IAppUserService {
	
	List<Appuser> findAll();
	Appuser findById(Integer id);
}
