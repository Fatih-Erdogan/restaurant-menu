package frames;

import javax.swing.JButton;
import manager.Manager;

public class RemoveMenuItemFrame extends MyFrame {

	private Manager manager;
	private JButton backButton;
	
	public RemoveMenuItemFrame(Manager manager) {
		super("Remove Menu Item");
		this.manager = manager;
		
	}
}
