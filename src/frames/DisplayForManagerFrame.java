package frames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import manager.Manager;

public class DisplayForManagerFrame extends MyFrame {

	private JButton backButton;
	private JButton displayStockInfoButton;
	private JButton displayCustomersButton;
	private JButton displayAllSalesAndOrders;
	private JButton displayModificationHistory;
	
	/**
	 * sets name of the frame
	 * invokes the private button setter method
	 */
	public DisplayForManagerFrame() {
		super("Display for Manager");
		setButtons();
	}
	
	/**
	 * back button disposes the frame and create ModifyOrDisplayFrame without arguments
	 * display stock info button disposes frame and create DisplayStockInfoFrame without arguments
	 * display customers button disposes frame and create DisplayCustomersFrame without arguments
	 * display all sales and orders button disposes frame and create AllSalesAndOrdersFrame without arguments
	 * display modification history button disposes frame and create DisplayModificationHistoryFrame without arguments
	 */ 
	private void setButtons() {
		
		backButton = generateBackButton();
		backButton.setBounds(1370,670,90,60);
		backButton.addActionListener(e -> 
		{
			dispose();
			new ModifyOrDisplayFrame();
		});
		
		displayStockInfoButton = new JButton();
		displayStockInfoButton.setBounds(125,70,560,300);
		displayStockInfoButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		displayStockInfoButton.setForeground(Color.black);
		displayStockInfoButton.setText("Stock Info");
		displayStockInfoButton.setFocusable(false);
		displayStockInfoButton.setBackground(Color.DARK_GRAY);
		displayStockInfoButton.addActionListener(e -> 
		{
			dispose();
			new DisplayStockInfoFrame();
		});
		
		
		
		displayCustomersButton = new JButton();
		displayCustomersButton.setBounds(750,70,560,300);
		displayCustomersButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		displayCustomersButton.setForeground(Color.black);
		displayCustomersButton.setText("Display Customers");
		displayCustomersButton.setFocusable(false);
		displayCustomersButton.setBackground(Color.DARK_GRAY);
		displayCustomersButton.addActionListener(e -> 
		{
			dispose();
			new DisplayCustomersFrame();
		});
		
		
		
		
		displayAllSalesAndOrders = new JButton();
		displayAllSalesAndOrders.setBounds(125,430,560,300);
		displayAllSalesAndOrders.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		displayAllSalesAndOrders.setForeground(Color.black);
		displayAllSalesAndOrders.setText("Sales and Orders");
		displayAllSalesAndOrders.setFocusable(false);
		displayAllSalesAndOrders.setBackground(Color.DARK_GRAY);
		displayAllSalesAndOrders.addActionListener(e -> 
		{
			dispose();
			new AllSalesAndOrdersFrame();
		});
		
		
		
		
		displayModificationHistory = new JButton();
		displayModificationHistory.setBounds(750,430,560,300);
		displayModificationHistory.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		displayModificationHistory.setForeground(Color.black);
		displayModificationHistory.setText("Modification History");
		displayModificationHistory.setFocusable(false);
		displayModificationHistory.setBackground(Color.DARK_GRAY);
		displayModificationHistory.addActionListener(e -> 
		{
			dispose();
			new DisplayModificationHistoryFrame();
		});
		
		
		
		
		this.add(backButton);
		this.add(displayStockInfoButton);
		this.add(displayCustomersButton);
		this.add(displayAllSalesAndOrders);
		this.add(displayModificationHistory);
	}
	
	
	
	
	
	
	
	
	
}
