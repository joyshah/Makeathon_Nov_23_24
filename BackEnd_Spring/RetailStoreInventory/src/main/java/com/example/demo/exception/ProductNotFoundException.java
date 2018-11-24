package com.example.demo.exception;

public class ProductNotFoundException extends Exception {

	public ProductNotFoundException() {
		super("Product Not Found");
	}
}
