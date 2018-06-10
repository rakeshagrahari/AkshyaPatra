package com.aws.codestar.projecttemplates.controller;

/**
 * This class is used for Exception Handling
 */
public class ApplicationException extends Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ApplicationException(){
		super();	
	}
	
	public ApplicationException(String message){
		  super(message);
	}
	
	

}

