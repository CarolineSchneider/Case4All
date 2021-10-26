package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Category;

public class DataFormatEach {
	
	private String nameEstablishment;
	private List<Category> categoriesList;
	private Double avg;
	
	
	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public Double getAvg() {
		return avg;
	}
	public double Avg(ArrayList<Double> products) {
		double  n = 0;
		for(int i = 0; i < products.size(); i++) {
			n = products.get(i);
		}
		double avg = n / products.size();
		
		return avg;
	}
	
	public DataFormatEach() {
		super();
		categoriesList = new ArrayList<Category>();
	}
	public String getNameEstablishment() {
		return nameEstablishment;
	}
	public void setNameEstablishment(String nameEstablishment) {
		this.nameEstablishment = nameEstablishment;
	}
	public List<Category> getCategoriesList() {
		return categoriesList;
	}
	public List<String> getCategoriesListNames() {
		ArrayList <String> categoriesListNames = new ArrayList<>();
		for(int i = 0; i < categoriesList.size(); i++) {
			categoriesListNames.add(categoriesList.get(i).getName());
		}
		return categoriesListNames;
	}
	public List<Long> getCategoriesListIds() {
		ArrayList<Long> x = new ArrayList<>();
		for(int i = 0; i < categoriesList.size(); i++) {
			x.add(categoriesList.get(i).getId());
		}
		return x;
	}
	public void addToCategoriesList(Category category) {
		this.categoriesList.add(category);
	}
	
	@Override
	public String toString() {
		return " " + nameEstablishment + ", categoriesList=" + categoriesList + "]";
	}
	
	

}
