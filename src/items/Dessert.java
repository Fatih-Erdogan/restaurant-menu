package items;

public class Dessert extends AnyItem {
	
	public Dessert(String name, double price, int initialStock) {
		
		this.name = name;
		this.price = price;
		this.stock = initialStock >= 0 ? initialStock : 0;
	}

	
	
	
}
