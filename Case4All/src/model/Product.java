package model;
import java.util.ArrayList;

public class Product {
	private long id;
	private ArrayList<Long> categoriesId;
	private String name;
	private double price;
	private int CEM = 100;
	
	public Product() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(Long id2) {
		this.id = id2;
	}
	public ArrayList<Long> getCategoriesId() {
		return categoriesId;
	}
	public void setCategoriesId(ArrayList<Long> categoriesIdList2) {
		this.categoriesId = categoriesIdList2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price / CEM;
	}
	
}
