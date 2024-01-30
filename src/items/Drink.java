package items;

public class Drink extends AnyItem {

	public Drink(String name, double price, int initialStock) {
		
		this.name = name;
		this.price = price;
		this.stock = initialStock >= 0 ? initialStock : 0;
	}


	
	
	
}
