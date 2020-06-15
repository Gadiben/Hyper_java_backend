package com.hyper.backend.service;

import java.util.Optional;

import com.hyper.backend.model.Appuser;

public interface IAppUserAuthentificationService {

	  /**
	   * Logs in with the given {@code username} and {@code password}.
	   *
	   * @param username
	   * @param password
	   * @return an {@link Optional} of a user when login succeeds
	   */
	  Optional<String> login(String username, String password);

	  /**
	   * Finds a user by its dao-key.
	   *
	   * @param token user dao key
	   * @return
	   */
	  Optional<Appuser> findByToken(String token);

	  /**
	   * Logs out the given input {@code user}.
	   *
	   * @param user the user to logout
	   */
	  void logout(Appuser user);

	}