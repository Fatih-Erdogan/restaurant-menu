package frames;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import customer.Orders;
import customer.Session;
import manager.Manager;
import items.AnyItem;
import java.util.HashMap;
import java.util.Map.Entry;

public class DisplaySalesFrame extends MyFrame {
	
	private JButton backButton;
	
	/**
	 * set name of field
	 * invokes private methods setting buttons and labels
	 */
	public DisplaySalesFrame() {
		super("Display Sales");
		setButtons();
		setLabels();
	}
	
	/**
	 * back button disposes and creates AllSalesAndOrdersFrame without argument
	 */
	private void setButtons() {
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new AllSalesAndOrdersFrame();
		});
		this.add(backButton);
	}
	
	/**
	 * uses displayAllSales method to take the String containing sale information then splits it into labels and displays it
	 */
	private void setLabels() {
		String toDisplay = displayAllSales();
		String[] displayArray = toDisplay.split("\\n");
		int counter = 0;
		for(String s : displayArray) {
			JLabel label = new JLabel(s);
			label.setFont(new Font("Abadi", Font.PLAIN,25));
			label.setForeground(Color.black);
			label.setBounds(200,200+(counter*32),1200,30);
			counter++;
			this.add(label);
		}
		
		double totalPrice = 0;
		for(Session s : Session.getAllSessionsList()) {
			totalPrice += s.getTotalPrice();
		}
		
		
		JLabel totalLabel = new JLabel("TOTAL: "+String.valueOf(totalPrice)+" TL");
		totalLabel.setFont(new Font("Abadi", Font.PLAIN,30));
		totalLabel.setBounds(170,700,300,35);
		this.add(totalLabel);
		
		JLabel header = new JLabel(String.format("%-35s%-38s%s","ITEM","QUANTITY","PRICE"));
		header.setFont(new Font("Abadi", Font.PLAIN,30));
		header.setBounds(200,100,900,35);
		this.add(header);
		
	}
	
	/**
	 * takes every Order element and count every item then make a String of format item name + quantity + price
	 * @return String object containing sales information
	 */
	private static String displayAllSales() {
		
		String toReturn = "";
		HashMap<AnyItem,Integer> itemMap = new HashMap<>();
		ArrayList<AnyItem> allOrdersList = Orders.getAllOrders();
		for(AnyItem i : allOrdersList) {
			if(itemMap.containsKey(i)) {
				itemMap.put(i, itemMap.get(i)+1);
			}
			else itemMap.put(i, 1);
		}
		
		for (Entry<AnyItem,Integer> entry : itemMap.entrySet()) {
			String toAppend = String.format("%-45s%-50s%s",
							entry.getKey().getName(),
							String.valueOf(entry.getValue()),
							String.valueOf(entry.getKey().getPrice()));
		
			toReturn = toReturn+toAppend+"\n";
		}
		toReturn = toReturn.trim();
		return toReturn;
	}
	
	
}
