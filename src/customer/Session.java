package customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import items.AnyItem;


/**
 * Keeps all related items such as Table Order and Customer objects together
 */
public class Session implements Comparable<Session> {

	
	/**
	 * session map keeps only the current sessions and allows access them by using table or session number which are the same
	 */
	private static final HashMap<Integer,Session> SESSIONSMAP = new HashMap<>();
	private static final ArrayList<Session> EXISTINGSESSIONS = new ArrayList<>();
	private static final ArrayList<Session> ALLSESSIONS = new ArrayList<>();
	private Customer customer;
	private Orders orders;
	private Table table;
	private int sessionNumber;
	
	/**
	 * Sets session number same as table number to facilitate access to Session object using Table number and vice-versa
	 * @param customer who creates the session
	 */
	public Session(Customer customer) {
		this.customer = customer;
		this.orders = new Orders();
		this.table = this.tableGenerator();
		this.sessionNumber = this.table.getTableNum();
		ALLSESSIONS.add(this);
		EXISTINGSESSIONS.add(this);
		SESSIONSMAP.put(this.sessionNumber, this);
		Collections.sort(EXISTINGSESSIONS);
		
	}
	
	/**
	 * Generates table for new customer automatically
	 * Changes availability of Table object
	 * @return Table object to session
	 */
	private Table tableGenerator() {
		
		Table table = null;
		int numOfPerson = customer.getNumOfPerson();
		
		for (Table t : Table.getEmptyTables()) {
			if (t.getChairNum() >= numOfPerson) {
				table = t;
				break;
			}
		}
		if (table != null) {
			table.setAvailability();
		}
		return table;
	}

	/**
	 * @return Customer of the Session
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * @return Order object of the Session where ordered items are kept
	 */
	public Orders getOrders() {
		return orders;
	}

	public Table getTable() {
		return table;
	}

	/**
	 * Session number is same as table number
	 */
	public int getSessionNumber() {
		return sessionNumber;
	}
	
	/**
	 * Adds new item to the order list of session
	 * Allows adding item using directly Session object, removing the necessity to get the Order object of the Session
	 * Sorts existing Sessions so that they are displayed in correct order for Managers
	 * @return true if successfully added
	 */
	public boolean addNewItem(AnyItem item) {
		boolean added = orders.addNewItem(item);
		Collections.sort(EXISTINGSESSIONS);
		return added;
	}
	
	/**
	 * Opposite of addNewItem(AnyItem item) method
	 * Sorts again the existing Sessions reason explained in addNewItem method
	 * @return true if successfully removed
	 */
	public boolean removeItem(AnyItem item) {
		boolean removed = orders.removeItem(item);
		Collections.sort(EXISTINGSESSIONS);
		return removed;
	}
	
	
	public static ArrayList<Session> getAllSessionsList(){
		return new ArrayList<Session>(ALLSESSIONS);
	}
	
	/**
	 * Used when trying to access Session objects from a Table object using the table number which is same as Session number
	 */
	public static HashMap<Integer,Session> getSessionsMap(){
		return new HashMap<Integer,Session>(SESSIONSMAP);
	}

	/**
	 * Sorts according to Customer ages, if same, to total price of their orders
	 */
	@Override
	public int compareTo(Session o) {
		if (this.customer.getAge() > o.customer.getAge()) return 1;
		if (this.customer.getAge() < o.customer.getAge()) return -1;
		else {
			if (this.orders.getTotalPrice() > o.orders.getTotalPrice()) return 1;
			if (this.orders.getTotalPrice() < o.orders.getTotalPrice()) return -1;
			else return 0;
		}
	}
	
	/**
	 * Ends the session, make table available for other customers, updates current sessions
	 */
	public void endSession() {
		this.table.setAvailability();
		SESSIONSMAP.remove(sessionNumber);
		EXISTINGSESSIONS.remove(this);
	}
	
	/**
	 * @return total price of orders of the Session
	 */
	public double getTotalPrice() {
		return this.orders.getTotalPrice();
	}
	
	public static ArrayList<Session> getExistingSessions(){
		return new ArrayList<Session>(EXISTINGSESSIONS);
	}
	
	/**
	 * checks whether there exists suitable table for a new customer
	 * used when creating an instance of the method by using createSession method
	 * @return true if there is a suitable table
	 */
	private static boolean isAnyTableAvailable(int num) {
		
		ArrayList<Table> tablesList = Table.getEmptyTables();
		for (Table t : tablesList) {
			if (t.getChairNum()>= num) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * used for creating new Session by assuring there is an available table
	 * @param customer object of the Session
	 * @return created Session object
	 */
	//if no table available returns null
	public static Session createSession(Customer customer) {
		
		boolean isAvailable = isAnyTableAvailable(customer.getNumOfPerson());
		if (isAvailable) {
			Session newSession = new Session(customer);
			return newSession;
		}
		return null;
	}
	
	/**
	 * used in displayAllSessions method to prevent confuse
	 */
	private String displayASession() {
		
		return String.format("%s%s%.2f", completeTo(customer.getName(),46,true),
							completeTo(String.valueOf(sessionNumber),53,true), getTotalPrice());
	}
	
	/**
	 * provides string to frame while displaying all sessions
	 * @return a String displaying all Session objects with customer name + table/session number + total price
	 */
	public static String displayAllSessions() {
		
		String toReturn = "";
		for (Session s : EXISTINGSESSIONS) {
			toReturn = toReturn.concat(s.displayASession()+"\n");
		}
		toReturn = toReturn.trim();
		return toReturn;
	}
	
	/**
	 * provides useful string to frame while displaying current sessions
	 * @return a String object displaying sessions orders with format: Item name + quantity + total price for this item
	 */
	public String displayCurrentSessionOrders() {
		String toReturn = "";
		HashMap<AnyItem,Integer> ordersMap = new HashMap<>();
		for (AnyItem item :orders.getOrderList()) {
			if (ordersMap.containsKey(item)) {
				ordersMap.put(item, ordersMap.get(item)+1);
			}
			else ordersMap.put(item, 1);
		}
		for (Entry<AnyItem,Integer> entry : ordersMap.entrySet()) {
			toReturn = toReturn + String.format("%-45s%-50s%s\n", entry.getKey().getName(),String.valueOf(entry.getValue()),
										String.valueOf(entry.getValue()*entry.getKey().getPrice()));
		}
		toReturn = toReturn.trim();
		
		return toReturn;
	}
	
	//just used for setting the size of strings but not very successfull in gui
	//meaning that displaying was a bit unordered
	private static String completeTo(String s, int size, boolean appendLeft) {
		
		int many = size - s.length();
		
		String toAppend = "";
		for (int i = 0 ; i < many ; i++) {
			toAppend += " ";
		}
		String toReturn = s;
		if(appendLeft) toReturn = toReturn + toAppend;
		else toReturn = toAppend + toReturn;
		return toReturn;
	}
	
	/**
	 * changes table of the session 
	 * updates SESSIONMAP where current sesssions are kept as a map with their table/session numbers
	 * @param newTable destination table
	 */
	public void changeTable(Table newTable) {
		
		table.setAvailability();
		SESSIONSMAP.remove(sessionNumber);
		table = newTable;
		table.setAvailability();
		sessionNumber = newTable.getTableNum();
		SESSIONSMAP.put(this.sessionNumber, this);
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
