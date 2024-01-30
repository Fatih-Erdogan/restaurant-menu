package items;

public class Soup extends AnyItem {

	
	public Soup(String name, double price, int initialStock) {
	
		this.name = name;
		this.price = price;
		this.stock = initialStock >= 0 ? initialStock : 0;
	}




	
	
	
	
	
}
