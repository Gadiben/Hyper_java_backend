package com.hyper.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.hyper.backend.model.Appuser;
@Repository
public interface AppUserRepo extends CrudRepository<Appuser, Integer>{

}