package frames;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import customer.Session;


public class NewOrExistingFrame extends MyFrame {

	private JButton newButton;
	private JButton existingButton;
	private JButton backButton;
	
	
	public NewOrExistingFrame() {
		
		super("New or Existing");
		setButtons();
		
		
	}
	
	/**
	 * sets the actionListener of back button to dispose the frame and create MainMenuFrame
	 * sets the actionListener of new customer button to dispose the frame and create CustomerCreationFrame
	 * sets the actionListener of existing customer button to take table number input from user by JOptionPane
	 * if it is a valid table number creates ChangeTableOrDisplayMenuItemsFrame by giving the Session object containing that table number as a parameter
	 */
	private void setButtons() {
		
		newButton = new JButton();
		newButton.setBounds(250,160,400,400);
		newButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,50));
		newButton.setForeground(Color.black);
		newButton.setText("New Customer");
		newButton.setFocusable(false);
		newButton.setOpaque(true);
		newButton.setBackground(Color.DARK_GRAY);
		newButton.addActionListener(e -> 
		{
			dispose();
			new CustomerCreationFrame();
		});
		
		
		
		
		existingButton = new JButton();
		existingButton.setBounds(855,160,400,400);
		existingButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,49));
		existingButton.setForeground(Color.black);
		existingButton.setText("Exsisting Customer");
		existingButton.setFocusable(false);
		existingButton.setBackground(Color.DARK_GRAY);
		existingButton.addActionListener(e -> 
		{
			
			String sessionNumStr = JOptionPane.showInputDialog("Please write your table number:");
			
			try {
				int sessionNum = Integer.parseInt(sessionNumStr);
				
				if (Session.getSessionsMap().containsKey(sessionNum)) {
					Session theSession = Session.getSessionsMap().get(sessionNum);
					String custName = theSession.getCustomer().getName();
					JOptionPane.showMessageDialog(null, "Wellcome Back "+custName+".");
					dispose();
					new ChangeTableOrDisplayMenuItemsFrame(theSession);
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Session couldn't found!");
				}
				
				
			}
			catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Invalid table number!");
			}
			
		});
		
		
		
		
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new MainMenuFrame();
		});
		
		
		
		
		this.add(newButton);
		this.add(existingButton);
		this.add(backButton);
		
		
		
	}
}
