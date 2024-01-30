package frames;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


import java.awt.Font;

public class MainMenuFrame extends MyFrame {

	private JLabel managerLabel;
	private JLabel customerLabel;
	private JButton managerButton;
	private JButton customerButton;
	
	/**
	 * invokes private label and button setter methods and sets name of the frame
	 */
	public MainMenuFrame() {
		
		super("Main Menu");
		setLabels();
		setButtons();
	}

	/**
	 * Adds manager and customer labels to frame
	 */
	private void setLabels() {

		managerLabel = new JLabel("Manager");
		managerLabel.setBounds(375,600,150,50);
		managerLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,40));
		
		
		customerLabel = new JLabel("Customer");
		customerLabel.setBounds(995,600,150,50);
		customerLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,40));
		
		this.add(managerLabel);
		this.add(customerLabel);
	}
	
	/**
	 * sets buttons and their icons
	 * sets actionListener of back button to dispose this frame and create ManagerVerificationFrame
	 * sets actionListener of manager button and customer button to dispose this frame and create ManagerVerificationFrame and NewOrExistingFrame respectively
	 */
	private void setButtons() {
		
		managerButton = new JButton();
		managerButton.setBounds(250,160,400,400);
		managerButton.setOpaque(true);
		managerButton.setBackground(Color.gray);
		
		ImageIcon managerIcon = new ImageIcon("images/manager.png");
		managerIcon = resizeIcon(managerIcon,350,350);
		managerButton.setIcon(managerIcon);
		managerButton.setVisible(true);
		managerButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				new ManagerVerificationFrame();
			}
		});
			
		
		
		
		customerButton = new JButton();
		customerButton.setBounds(855,160,400,400);
		customerButton.setOpaque(true);
		customerButton.setBackground(Color.gray);
		
		
		//sets the icon
		ImageIcon customerIcon = new ImageIcon("images/customer.png");
		customerIcon = resizeIcon(customerIcon,350,350);
		customerButton.setIcon(customerIcon);
		customerButton.setVisible(true);
		
		customerButton.addActionListener(e -> 
		{
			dispose();
			new NewOrExistingFrame();
			
		});
		
		
		this.add(managerButton);
		this.add(customerButton);
	}
	
	
	

	
	
	
	
	
	
	
}
