package CafeTycoon;

import java.util.List;

public class Coffee implements MenuItem {

	private String name;
	private int price;
	private List<String> recipe;

	public Coffee(String name, int price, List<String> recipe) {
		this.name = name;
		this.price = price;
		this.recipe = recipe;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public List<String> getRecipe() {
		return recipe;
	}
}
