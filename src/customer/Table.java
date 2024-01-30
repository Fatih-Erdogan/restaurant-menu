package customer;

import java.util.ArrayList;
import java.util.Collections;

public class Table implements Comparable<Table> {
	
	private static ArrayList<Table> allTables = new ArrayList<>();
	private static int tableCount = 1;
	private int tableNum;
	private int chairNum;
	private boolean available = true;
	
	public Table(int chairNum) {
		
		this.tableNum = tableCount;
		this.chairNum = chairNum;
		updateTableCount();
		allTables.add(this);
		Collections.sort(allTables);
	}
	
	public int getChairNum() {
		return chairNum;
	}
	
	public int getTableNum() {
		return tableNum;
	}
	
	/**
	 * changes status of availability when invoked
	 */
	public void setAvailability() {
		if (available) available = false;
		else available = true;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * @return a list of all tables
	 */
	public static ArrayList<Table> getAllTables(){
		return allTables;
	}
	
	/**
	 * @return a list of available tables
	 */
	public static ArrayList<Table> getEmptyTables(){
		ArrayList<Table> emptyTables = new ArrayList<>();
		for (Table t : allTables) {
			if (t.isAvailable()) emptyTables.add(t);
		}
		return emptyTables;
	}
	
	/**
	 * keeps track of table numbers useful for giving numbers to tables when creating them
	 */
	private static void updateTableCount() {
		tableCount++;
	}
	
	/**
	 * sorts table list according to chair numbers
	 */
	@Override
	public int compareTo(Table o) {
		
		if (this.getChairNum() > o.getChairNum()) return 1;
		if (this.getChairNum() < o.getChairNum()) return -1;
		else return 0;
	}
	
	
	
	
	
}
