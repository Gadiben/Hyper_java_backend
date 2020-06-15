package com.hyper.backend.exception;

public class NotFoundException extends RuntimeException{
	public NotFoundException(Integer id){
		super("Could not find item with id " + id);
	}
}
