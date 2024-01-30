package menu;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import items.AnyItem;
import manager.Manager;
import java.util.Map.Entry;

/**
 * Instances of this class are like categories and they keep related Item objects
 * @param <E> type of the contained objects
 */
public class MenuItem<E extends AnyItem> {

	private static HashMap<Integer,String> modificationHistory = new HashMap<>();
	private static boolean permission = false;
	private static int modifierId = 0;
	private String name;
	private ArrayList<E> itemList = new ArrayList<>();
	private Path imagePath;

	public MenuItem(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * checks for the permission adds the Item object then updates modification history kept by id's of managers as keys and modification as value
	 */
	public void addItem(E item) {
		if (permission) {
			boolean duplicate = false;
			for (E anItem : itemList) {
				if (anItem.getName() == item.getName()) duplicate = true;
			}
			if (!duplicate) {
				itemList.add(item);
				if (modificationHistory.containsKey(modifierId)) {
					String prevValue = modificationHistory.get(modifierId);
					modificationHistory.put(modifierId, prevValue+"\n"+ "Added a new item to the Menu Item: "+item.getName());
				}
				else modificationHistory.put(modifierId, "Added a new item to the Menu Item: "+item.getName());
				System.out.println("Added "+item.getName()+" successfully.");
			}
			else System.out.println("Item already exists in the Menu Item.");
		}
		else System.out.println("No permission to modify the Menu Item!");
	}
	
	
	/**
	 * checks for the permission removes the item then updates modification history kept by id's of managers as keys and modification as value
	 */
	public void removeItem(E item) {
		if (permission) {
			if (itemList.contains(item)) {
				itemList.remove(item);
				modificationHistory.put(modifierId, "Removed an item: "+item.getName());
				System.out.println("Removed "+item.getName()+" succesfully.");
			}
			else System.out.println("Item does not exsist in the item list of menu item.");
		}
		else {
			System.out.println("No permission!");
		}
	}
	
	/**
	 * @return a copy of the modificationHistory to prevent unauthorized modifications
	 */
	public static HashMap<Integer, String> getModificationHistory() {
		HashMap<Integer,String> toReturn = new HashMap<>();
		toReturn.putAll(modificationHistory);
		return toReturn;
	}
	
	/**
	 * @return list of items contained in the MenuItem object
	 */
	public ArrayList<E> getItemList() {
		return new ArrayList<E>(itemList);
	}
	
	/**
	 * sets the location of the photo of the MenuItem object
	 * @param strPath path for the photo
	 */
	public void setImagePath(String strPath) {
		imagePath = Paths.get(strPath);
	}
	
	public Path getImagePath() {
		return imagePath;
	}
	
	/** 
	 * gives access to modify MenuItems if id and password are correct
	 * sets permission true which will be looked when adding new items
	 * sets modifier id to keep track of which manager made which modifications
	 * @param id of Manager who will modify
	 * @param password to make password check
	 */
	public static void requestPersmission(int id, String password) {
		if (Manager.getManagersMap().get(id).passwordValidator(password)) {
			modifierId = id;
			permission = true;
			System.out.println("Menu Items are ready to be modified.\nDon't forget to end your session!");
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
	 * @return a String displaying all modifications on all MenuItem objects
	 */
	public static String displayModificationHistory() {
		
		HashMap<Integer,Manager> managersMap = Manager.getManagersMap();
		String toReturn = "";
		
		for (Entry<Integer,String> histEntry : modificationHistory.entrySet()) {
			Manager theManager = managersMap.get(histEntry.getKey());
			String name = theManager.getName();
			toReturn = toReturn + name+":\n"+histEntry.getValue()+"\n";
		}
		toReturn = toReturn.trim();
		return toReturn;
	}

}
