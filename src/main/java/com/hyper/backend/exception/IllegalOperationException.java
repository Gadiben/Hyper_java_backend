package com.hyper.backend.exception;

public class IllegalOperationException extends RuntimeException{
	public IllegalOperationException(){
		super("Illegal Operation");
	}

	public IllegalOperationException(String string) {
		super(string);
	}
}
