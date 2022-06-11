package net.javaguides.qlbanhang.model;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Product product;
	private Integer quantity;
	
	
	public Cart() {
	}
	
	public Cart(Product product, Integer quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
