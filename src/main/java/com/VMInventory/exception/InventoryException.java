package com.VMInventory.exception;

public abstract class InventoryException extends RuntimeException{

	   protected InventoryException(String message){
	        super(message);
	    }
	}