package items;

public class MainDish extends AnyItem {

	public MainDish(String name, double price, int initialStock) {
		
		this.name = name;
		this.price = price;
		this.stock = initialStock >= 0 ? initialStock : 0;
	}



	
	
	
}
