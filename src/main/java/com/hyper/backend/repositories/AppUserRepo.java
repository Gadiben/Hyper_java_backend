package com.hyper.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.hyper.backend.model.Appuser;
@Repository
public interface AppUserRepo extends JpaRepository<Appuser, Integer>{
	@Query(" select u from Appuser u " +
            " where u.pseudo = ?1")
	Optional<Appuser> findUserWithName(String name);
}