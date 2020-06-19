package com.hyper.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.hyper.backend.model.Media;
@Repository
public interface MediaRepo extends JpaRepository<Media, Integer>{
	@Query("select m from Media m " +
            " where m.title = ?1")
	Optional<Media> findMediaWithTitle(String name);
	
}