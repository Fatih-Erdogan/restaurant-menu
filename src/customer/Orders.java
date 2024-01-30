package customer;

import java.util.ArrayList;
import items.AnyItem;

public class Orders {
	
	private static ArrayList<AnyItem> allOrders = new ArrayList<>();
	private ArrayList<AnyItem> orderList = new ArrayList<>();
	private double totalPrice = 0;
	
	public Orders() {
		
	}
	
	/**
	 * returns the total price of every item contained in this order object
	 * @return Total price of objects contained in the order object
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	
	
	/**
	 * Adds a new Item (such as Mercimek Çorbasý) and reduces the stock number
	 * List to keep track of every order to display for example total sales
	 * Updates total price field
	 * @param item (EX: Mercimek Çorbasý from Soup class)
	 * @return if adding is successful returns true
	 */
	public boolean addNewItem(AnyItem item) {
		if (item.isAvailable()) {
			item.minusStock();
			orderList.add(item);
			allOrders.add(item);
			updateTotalPrice();
			return true;
		}
		else return false;
	}
	
	
	/**
	 * does exactly the opposite of addNewItem method
	 * @param item (EX: Mercimek Çorbasý from Soup class)
	 * @return Returns true if removal is successful
	 */
	public boolean removeItem(AnyItem item) {
		if (orderList.contains(item)) {
			item.plusStock();
			orderList.remove(item);
			allOrders.remove(item);
			updateTotalPrice();
			return true;
		}
		else return false;
	}
	
	/**
	 * @return the order list containing items
	 */
	public ArrayList<AnyItem> getOrderList(){
		return orderList;
	}
	
	/**
	 * updates total price field, used in the class when adding or removing items
	 */
	private void updateTotalPrice() {
		double total = 0;
		for (AnyItem item : orderList) {
			total += item.getPrice();
		}
		totalPrice = total;
	}
	
	/**
	 * @return list of all items belonging to all Order objects
	 */
	public static ArrayList<AnyItem> getAllOrders(){
		return new ArrayList<AnyItem>(allOrders);
	}
	
	
	
	
}
