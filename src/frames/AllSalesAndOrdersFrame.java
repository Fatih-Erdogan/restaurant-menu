package frames;

import manager.Manager;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import customer.Session;


public class AllSalesAndOrdersFrame extends MyFrame {
	private JButton backButton;
	private JButton salesButton;
	private JButton ordersWithTablesButton;
	
	
	/**
	 * sets name of the frame
	 * invokes button setter method
	 */
	public AllSalesAndOrdersFrame() {
		super("All Sales and Orders");
		setButtons();
	}
	
	/**
	 * sales button disposes frame and creates DisplaySalesFrame
	 * orders button disposes frame and creates DisplayOrdersWithTablesFrame
	 */
	private void setButtons() {
		
		salesButton = new JButton();
		salesButton.setBounds(250,160,400,400);
		salesButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,50));
		salesButton.setForeground(Color.black);
		salesButton.setText("Display Sales");
		salesButton.setFocusable(false);
		salesButton.setOpaque(true);
		salesButton.setBackground(Color.DARK_GRAY);
		salesButton.addActionListener(e -> 
		{
			dispose();
			new DisplaySalesFrame();
		});
		
		
		
		
		ordersWithTablesButton = new JButton();
		ordersWithTablesButton.setBounds(855,160,400,400);
		ordersWithTablesButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,50));
		ordersWithTablesButton.setForeground(Color.black);
		ordersWithTablesButton.setText("Display Orders");
		ordersWithTablesButton.setFocusable(false);
		ordersWithTablesButton.setBackground(Color.DARK_GRAY);
		ordersWithTablesButton.addActionListener(e -> 
		{
			dispose();
			new DisplayOrdersWithTablesFrame();
		});
		
		
		
		
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new MainMenuFrame();
		});
		
		
		
		
		this.add(salesButton);
		this.add(ordersWithTablesButton);
		this.add(backButton);
		
		
		
		
		
	}
	
	
}
