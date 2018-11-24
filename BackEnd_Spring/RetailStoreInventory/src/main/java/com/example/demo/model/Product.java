package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private int productId;
	private String productName;
	private int coorx;
	private int coory;
	private int p_coorx;
	private int p_coory;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCoorx() {
		return coorx;
	}

	public void setCoorx(int coorx) {
		this.coorx = coorx;
	}

	public int getCoory() {
		return coory;
	}

	public void setCoory(int coory) {
		this.coory = coory;
	}

	public int getP_coorx() {
		return p_coorx;
	}

	public void setP_coorx(int p_coorx) {
		this.p_coorx = p_coorx;
	}

	public int getP_coory() {
		return p_coory;
	}

	public void setP_coory(int p_coory) {
		this.p_coory = p_coory;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", coorx=" + coorx + ", coory="
				+ coory + ", p_coorx=" + p_coorx + ", p_coory=" + p_coory + "]";
	}

}