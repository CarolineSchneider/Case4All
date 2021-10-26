package model;

import java.util.ArrayList;

public class Category {
	private Long id;
	private String name;
	
	private ArrayList<Product> products;
	
	public Category() {
		super();
		this.products = new ArrayList<>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Categories [id=" + id + ", name=" + name + "]";
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void addProducts(Product product) {
		this.products.add(product);
	}
	public void addProductsList(ArrayList<Product> products) {
		this.products = products;
	}
	
	

}
