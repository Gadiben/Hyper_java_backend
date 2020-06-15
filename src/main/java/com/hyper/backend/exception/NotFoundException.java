package com.hyper.backend.exception;

public class NotFoundException extends RuntimeException{
	public NotFoundException(Integer id, String object_class){
		super("Could not find "+object_class+ " with id " + id);
	}
}
