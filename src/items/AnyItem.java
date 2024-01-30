package items;



/**
 * super class of all item classes, Soup Drink etc. 
 * created for enabling polymorphic behavior
 */
public abstract class AnyItem {
	
	protected String name;
	protected double price;
	protected int stock;
	
	
	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	public int getStock() {
		return stock;
	}
	
	public boolean isAvailable() {
		return (stock > 0);
	}
	
	//used when an item is ordered
	public void minusStock() {
		stock = stock-1;
	}
	//used when an item is canceled
	public void plusStock() {
		stock = stock+1;
	}
	
	

}
