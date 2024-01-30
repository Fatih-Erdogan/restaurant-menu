package frames;

import manager.Manager;
import menu.Menu;
import menu.MenuItem;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;


public class ModifyOrDisplayFrame extends MyFrame {

	private JButton modifyButton;
	private JButton displayButton;
	private JButton backButton;
	
	/**
	 * sets the name of the frame
	 * invokes the method to set buttons
	 */
	public ModifyOrDisplayFrame() {
		super("Modify or Display");
		setButtons();
	}
	
	/**
	 * sets actionListener of back button to dispose the frame, end the manager session and terminate permissions to modify menu and MenuItem objects
	 * sets actionListener of modify button and display buttons to dispose and create ModifyForManagerFrame and DisplayForManagerFrame respectively
	 */
	private void setButtons() {
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			Menu.endManagerSession();
			MenuItem.endManagerSession();
			new ManagerVerificationFrame();
		});
		
		modifyButton = new JButton();
		modifyButton.setBounds(250,160,400,400);
		modifyButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		modifyButton.setForeground(Color.black);
		modifyButton.setText("Modify");
		modifyButton.setFocusable(false);
		modifyButton.setBackground(Color.DARK_GRAY);
		modifyButton.addActionListener(e -> 
		{
			dispose();
			new ModifyForManagerFrame();
		});
		
		
		displayButton = new JButton();
		displayButton.setBounds(855,160,400,400);
		displayButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		displayButton.setForeground(Color.black);
		displayButton.setText("Display");
		displayButton.setFocusable(false);
		displayButton.setBackground(Color.DARK_GRAY);
		displayButton.addActionListener(e -> 
		{
			dispose();
			new DisplayForManagerFrame();
		});
		
		
		this.add(backButton);
		this.add(modifyButton);
		this.add(displayButton);
	}
	
	
}
