package model;
import java.util.ArrayList;

public class Establishment {
	private String name;
	private long id;
	private ArrayList<Long> productsId;

	public ArrayList<Double> getProductsPrice(ArrayList<Product> products) {
		ArrayList<Double> productsPrice = new ArrayList<>();
		for(int i = 0; i < this.productsId.size(); i++) {
			if(products.get(i).getId() == this.productsId.get(i)) {
					productsPrice.add(products.get(i).getPrice());
			}
		}
		return productsPrice;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ArrayList<Long> getProductsId() {
		return productsId;
	}
	public void setProductsId(ArrayList<Long> productsIdList) {
		this.productsId = productsIdList;
	}
	
	
		

}
