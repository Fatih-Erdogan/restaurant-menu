package menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import items.*;
import manager.Manager;



/**
 * keeps categories (MenuItems) which keep item objects
 */
public class Menu {

	private static final ArrayList<Menu> MENULIST = new ArrayList<>();
	private final ArrayList<MenuItem<? extends AnyItem>> menuItems = new ArrayList<>();
	private static int modifierId = 0;
	private static boolean permission = false;
	private final HashMap<Integer,String> modificationHistory = new HashMap<>();
	
	
	public Menu() {
		System.out.println("Menu created!\nReady to add menu items.");
		MENULIST.add(this);
	}
	
	/**
	 * checks for the permission adds the MenuItem then updates modification history kept by id's of managers as keys and modification as value
	 * @param m category (MenuItem) to add to the Menu object
	 */
	public void addMenuItem(MenuItem<? extends AnyItem> m) {
		if (permission) {
			menuItems.add(m);
			if (modificationHistory.containsKey(modifierId)) {
				String prevValue = modificationHistory.get(modifierId);
				modificationHistory.put(modifierId, prevValue+"\n"+ "Added a new Menu Item to the menu: "+m.getName());
			}
			else modificationHistory.put(modifierId, "Added a new Menu Item to the menu: "+m.getName());
			System.out.println("Added "+m.getName()+" succesfully.");
		}
		else {
			System.out.println("No permission!");
		}
	}
	
	/**
	 * checks for the permission removes the MenuItem then updates modification history kept by id's of managers as keys and modification as value
	 * @param m category (MenuItem) to remove from the Menu object
	 */
	public void removeMenuItem(MenuItem<? extends AnyItem> m) {
		if (permission) {
			if (menuItems.contains(m)) {
				menuItems.remove(m);
				if (modificationHistory.containsKey(modifierId)) {
					String prevValue = modificationHistory.get(modifierId);
					modificationHistory.put(modifierId, prevValue+"\n"+ "Removed a menu item: "+m.getName());
				}
				else modificationHistory.put(modifierId, "Removed a menu item: "+m.getName());
				System.out.println("Removed "+m.getName()+" succesfully.");
			}
			else System.out.println("Menu item does not exsist in the menu.");
		}
		else {
			System.out.println("No permission!");
		}
	}
	/**
	 * @return a copy of MenuItem objects list kept in Menu instance
	 */
	public ArrayList<MenuItem<? extends AnyItem>> getMenuItemsList(){
		return new ArrayList<MenuItem<? extends AnyItem>>(menuItems);
	}
	
	/**
	 * @return a copy of the modificationHistory to prevent unauthorized modifications
	 */
	public HashMap<Integer, String> getModificationHistory() {
		HashMap<Integer,String> toReturn = new HashMap<>();
		toReturn.putAll(modificationHistory);
		return toReturn;
	}
	
	/** 
	 * gives access to modify menu if id and password are correct
	 * sets permission true which will be looked when adding new menu items
	 * sets modifier id to keep track of which manager made which modifications
	 * @param id of Manager who will modify
	 * @param password to make password check
	 */
	public static void requestPersmission(int id, String password) {
		if (Manager.getManagersMap().get(id).passwordValidator(password)) {
			modifierId = id;
			permission = true;
			System.out.println("Menu ready to be modified.\nDon't forget to end your session!");
		}
	}
	
	/**
	 * ends manager session which means no further modifications will be made by signed manager
	 */
	public static void endManagerSession() {
		modifierId = 0;
		permission = false;
		System.out.println("Session ended.");
	}
	
	/**
	 * allows access to the menu item without any other reference 
	 * @return Menu instance
	 */
	public static Menu getMenuList(int idx) {
		return MENULIST.get(idx);
	}
	
	/**
	 * @return a formatted String object displaying stock information of all items contained in the Menu by MenuItem objects
	 */
	public static String displayStockInfo() {
		String toReturn = "";
		Menu theMenu = MENULIST.get(0);
		ArrayList<MenuItem<? extends AnyItem>> menuItemList = theMenu.getMenuItemsList();
		for (MenuItem<? extends AnyItem> m : menuItemList) {
			MenuItem<AnyItem> mm =(MenuItem<AnyItem>) m;
			for (AnyItem i : mm.getItemList()) {
				String name = i.getName();
				Integer stock = i.getStock();
				String availability = i.getStock() > 0 ? "Available" : "Not Available";
				toReturn = toReturn + String.format("%s%s%s", completeTo(name,40,true),
								completeTo(String.valueOf(stock),40,true),availability)+"\n";
			}
		}
		toReturn = toReturn.trim();
		return toReturn;
	}
	
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
	 * @return a String displaying all modifications on all Menu objects
	 */
	public static String displayModificationHistory() {
		
		HashMap<Integer,Manager> managersMap = Manager.getManagersMap();
		String toReturn = "";
		
		for (Entry<Integer,String> histEntry : Menu.getMenuList(0).getModificationHistory().entrySet()) {
			Manager theManager = managersMap.get(histEntry.getKey());
			String name = theManager.getName();
			toReturn = toReturn + name+":\n"+histEntry.getValue()+"\n";
		}
		toReturn = toReturn.trim();
		return toReturn;
	}
	
	
	
	
	
}
