package frames;

import javax.swing.JButton;
import manager.Manager;

public class RemoveItemFrame extends MyFrame {

	private Manager manager;
	private JButton backButton;
	
	public RemoveItemFrame(Manager manager) {
		super("Remove Item");
		this.manager = manager;
	}
	
}
