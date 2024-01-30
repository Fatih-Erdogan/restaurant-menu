package items;

public class Starter extends AnyItem {

	public Starter(String name, double price, int initialStock) {
		
		this.name = name;
		this.price = price;
		this.stock = initialStock >= 0 ? initialStock : 0;
	}

	

}
