package frames;

import java.awt.Color;
import java.awt.Font;
import customer.*;
import javax.swing.JButton;

public class ChangeTableOrDisplayMenuItemsFrame extends MyFrame {

	private JButton backButton;
	private JButton changeTableButton;
	private JButton displayMenuButton;
	private Session session;
	
	/**
	 * invokes private button setter function
	 */
	public ChangeTableOrDisplayMenuItemsFrame(Session s) {
		super("Change Table or Display Menu Items");
		this.session = s;
		setButtons();
	}
	

	/**
	 * sets actionListener of back button to dispose the frame and create newOrExistingFrame
	 * sets actionListener of the change table button and display menu button to dispose and create DisplayAvailableTablesForCustomersFrame and DisplayMenuItemsFrame respectively
	 * and gives the Session object as parameter in both case
	 */
	private void setButtons() {
		
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new NewOrExistingFrame();
		});
		
		
		changeTableButton = new JButton();
		changeTableButton.setBounds(855,160,400,400);
		changeTableButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,50));
		changeTableButton.setForeground(Color.black);
		changeTableButton.setText("Change Table");
		changeTableButton.setFocusable(false);
		changeTableButton.setBackground(Color.DARK_GRAY);
		changeTableButton.addActionListener(e ->
		{
			dispose();
			new DisplayAvailableTablesForCustomersFrame(session);
		});
		
		
		displayMenuButton = new JButton();
		displayMenuButton.setBounds(250,160,400,400);
		displayMenuButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,50));
		displayMenuButton.setForeground(Color.black);
		displayMenuButton.setText("Display Menu");
		displayMenuButton.setFocusable(false);
		displayMenuButton.setOpaque(true);
		displayMenuButton.setBackground(Color.DARK_GRAY);
		displayMenuButton.addActionListener(e -> 
		{
			dispose();
			new DisplayMenuItemsFrame(session);
		});
		
		
		
		this.add(backButton);
		this.add(changeTableButton);
		this.add(displayMenuButton);
		
	}
}
