package frames;

import manager.Manager;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;

import customer.Session;


public class DisplayCustomersFrame extends MyFrame {

	private JButton backButton;
	private JLabel headersLabel;
	private JLabel frameInfoLabel;
	
	/**
	 * sets the frame name 
	 * invokes private method setting buttons and labels
	 */
	public DisplayCustomersFrame() {
		super("Display Customers");
		setButtons();
		setLabels();
	}
	
	/**
	 * back button disposes the frame and creates DisplayForManagerFrame
	 */
	private void setButtons() {
		
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new DisplayForManagerFrame();
		});
		this.add(backButton);
	}
	
	/**
	 * takes a String displaying the current customers then it split it into  labels and place the labels appropriately
	 */
	private void setLabels() {
		
		frameInfoLabel = new JLabel("Current Customers");
		frameInfoLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		frameInfoLabel.setBounds(500,120,600,100);
		frameInfoLabel.setForeground(Color.black);
		
		headersLabel = new JLabel(String.format("%-20s%-25s%s", "NAME","TABLE NUMBER","TOTAL PRICE"));
		headersLabel.setFont(new Font("Abadi", Font.PLAIN,30));
		headersLabel.setBounds(330,230,900,40);
		headersLabel.setForeground(Color.black);
		
		String theStr = Session.displayAllSessions();
		String[] strArray = theStr.split("\\n");
		
	
		int currentPosition = 300;
		for (String str : strArray) {
			JLabel newLabel = new JLabel(str);
			newLabel.setFont(new Font("Abadi", Font.PLAIN,25));
			newLabel.setBounds(330,currentPosition,850,30);
			currentPosition+=40;
			this.add(newLabel);
		}
		

		this.add(headersLabel);
		this.add(frameInfoLabel);
	}
}
