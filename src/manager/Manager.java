package manager;
import java.util.HashMap;
import java.security.SecureRandom;

public class Manager {

	private static final HashMap<Integer,Manager> MANAGERSMAP = new HashMap<>();
	private final int id;
	private String password;
	private String name;
	
	/**
	 * when a manager created a random unique id to no name confusions
	 * Manager is capable of making modifications in Menu
	 * 0 is the default id for MenuItems and Menu meaning that nobody is currently modifying them so id 0 is not allowed
	 */
	public Manager(String name, String password) {
		
		SecureRandom rand = new SecureRandom();
		Integer id;
		do {
			id = rand.nextInt(1000);
			
		} while (MANAGERSMAP.keySet().contains(id) || (id == 0));
	
		this.id = id;
		this.name = name;
		this.password = password;
	
		MANAGERSMAP.put(id,this);
	}
	
	
	/**
	 * @return a copy of the MANAGERSLIST to prevent unauthorized modifications
	 */
	public static HashMap<Integer, Manager> getManagersMap() {
		HashMap<Integer,Manager> toReturn = new HashMap<>();
		toReturn.putAll(MANAGERSMAP);
		return toReturn;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * allows manager to change password but not used in GUI part
	 */
	public void setPassword(String oldPassword, String newPassword) {
		
		if (password.equals(oldPassword)) {
			this.password = newPassword;
			System.out.println("Password modified successfully!");
		}
		else {
			System.out.println("Incorrect old password, couldn't changed the password!");
		}
	}
	
	/**
	 * used for verifying whether the password is correct for giving the permission to modify Menu or MenuItem objects
	 * @return true if entered password is correct
	 */
	//modify menu or MenuItems objects
	public boolean passwordValidator(String password) {
		return this.password.equals(password);
	}
	
	
	
	
}
