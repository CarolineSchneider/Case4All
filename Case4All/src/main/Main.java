package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Category;
import model.Establishment;
import model.Product;

public class Main {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();

		try (FileReader reader = new FileReader("data.json")) {
			JSONObject data = (JSONObject) parser.parse(reader);

			JSONArray productList = (JSONArray) data.get("products");
			JSONArray categoriesList = (JSONArray) data.get("categories");
			JSONArray establishmentsList = (JSONArray) data.get("establishments");

////////////////////////////////////////////// inserting into an array of objects from "Product" type //////////////////////////////////////////////
			ArrayList<Product> pList = new ArrayList();
			productList.forEach(product -> pList.add(parseProductObj((JSONObject) product)));

			/*
			 * pList.forEach(product -> System.out.println(product.getId() + "     " +
			 * product.getName() + product.getPrice() + "  ----   " +
			 * product.getCategoriesId()));
			 */

/////////////////////////////////////////////// insert into an array of object from "Category" type//////////////////////////////////////////////

			ArrayList<Category> cList = new ArrayList();
			categoriesList.forEach(categorie -> cList.add(parseCategoryObj((JSONObject) categorie)));

			// cList.forEach(categorie -> System.out.println(categorie.getId() +
			// categorie.getName())); //

////////////////////////////////////////////// insert into an array of objects from "Establishment" type //////////////////////////////////////////////
			ArrayList<Establishment> eList = new ArrayList();
			establishmentsList.forEach(establishment -> eList.add(parseEstablishmentObj((JSONObject) establishment)));

			// eList.forEach(establishment -> System.out.println(establishment.getId() +
			// establishment.getName() + establishment.getProductsId()));

			JSONObject outputDataObject = new JSONObject();

			int i, i2;
			int i3 = 0;

			for (i = 0; i < eList.size(); i++) { // LOOKING INTO EVERY ESTABLISHMENT
				DataFormatEach establishment = new DataFormatEach();
				establishment.setNameEstablishment(eList.get(i).getName());
				int c = 0;
				Double s = (double) 0;
				JSONObject cat = new JSONObject();
				// Double avgPrice = (double) 0;
				for (i2 = 0; i2 < cList.size(); i2++) { // LOOKING INTO THE CATEGORIES

					if (eList.get(i).getProductsId().contains(cList.get(i2).getId())) {

						if (!establishment.getCategoriesListIds().contains(cList.get(i2).getId())) { // CHEKING IF THIS
																										// CATEGORY
																										// EXISTS IN THE
																										// ESTABLISHMENT
																										// ALREADY

							establishment.addToCategoriesList(cList.get(i2)); // IF NOT, THE WE ADD IT

							JSONObject prod = new JSONObject();

							for (i3 = 0; i3 < pList.size(); i3++) { // LOOKING INTO THE CATEGORIES
								JSONObject price = new JSONObject();
								price.put("price", pList.get(i3).getPrice());

								if (eList.get(i).getProductsId().contains(pList.get(i3).getId())
										&& pList.get(i3).getCategoriesId().contains(cList.get(i2).getId())) {
									// c++;
									// s += pList.get(i3).getPrice();
									prod.put(pList.get(i3).getName(), price);
								}
							}
							if (!prod.isEmpty()) {
								cat.put(cList.get(i2).getName(), prod); // ADDING THE LIST OF PRODUCTS INTO THIS
																		// CATEGORY IN THIS ESTABLISHMENT

							}

						}
					}
					/*
					 * avgPrice = s / c; System.out.println(avgPrice);
					 * outputDataObject.put(establishment.getNameEstablishment(), avgPrice); //
					 * REMAINS OF A FAILED TRY TO ADD THE ESTABLISHMENT AVG_PRICE CORRECTLY
					 */

				}
				outputDataObject.put((establishment.getNameEstablishment()), cat);
			}

			//System.out.println(outputDataObject.toJSONString());

			WriteJSONFile(outputDataObject);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Product parseProductObj(JSONObject product) {

		Product prod = new Product();

		String name = (String) product.get("name");

		JSONArray categoriesIdList = (JSONArray) product.get("categoriesId");
		ArrayList<Long> categoriesIdList2 = new ArrayList();
		categoriesIdList2.addAll(categoriesIdList);

		String price = (String) product.get("price");
		price.replaceAll(",", ".");
		double priceDouble = Double.parseDouble(price);
		long id = (long) product.get("id");

		prod.setName(name);
		prod.setCategoriesId(categoriesIdList2);
		prod.setPrice(priceDouble);
		prod.setId(id);

		return prod;
	}

	private static Category parseCategoryObj(JSONObject category) {
		Category categ = new Category();

		String name = (String) category.get("name");
		Long id = (Long) category.get("id");

		categ.setId(id);
		categ.setName(name);

		return categ;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Establishment parseEstablishmentObj(JSONObject establishment) {

		Establishment estab = new Establishment();

		String name = (String) establishment.get("name");
		long id = (long) establishment.get("id");

		JSONArray productsIdList = (JSONArray) establishment.get("productsId");
		ArrayList<Long> productsIdList2 = new ArrayList();
		productsIdList2.addAll(productsIdList);

		estab.setId(id);
		estab.setName(name);
		estab.setProductsId(productsIdList2);

		return estab;
	}

	private static void WriteJSONFile(JSONObject text) {
		System.out.println(text.toJSONString());
		try {
			FileWriter file = new FileWriter("caseConcluido.json");
			file.write(text.toJSONString());
			file.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
	}
}
