package frames;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import items.*;
import manager.Manager;
import menu.Menu;
import menu.MenuItem;

public class CreateItemFrame extends MyFrame {

	private JButton backButton;
	private JButton enterButton;
	
	private JTextField nameField;
	private JTextField priceField;
	private JTextField stockField;
	private JTextField typeField;
	private JLabel nameLabel;
	private JLabel priceLabel;
	private JLabel stockLabel;
	private JLabel frameInfoLabel;
	private JLabel typeLabel;
	
	
	/**
	 * sets name of the frame
	 * calls private label field and button setter methods
	 */
	public CreateItemFrame() {
		super("Create Item");
		setLabels();
		setTextFields();
		setButtons();
		
	}
	
	private void setLabels() {
		
		frameInfoLabel = new JLabel();
		frameInfoLabel.setText("Item Creation");
		frameInfoLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		frameInfoLabel.setForeground(Color.black);
		frameInfoLabel.setBounds(570,130,600,100);
		
		nameLabel = new JLabel();
		nameLabel.setText("NAME:");
		nameLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		nameLabel.setForeground(Color.black);
		nameLabel.setBounds(532,260,150,60);
		
		priceLabel = new JLabel();
		priceLabel.setText("PRICE:");
		priceLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		priceLabel.setForeground(Color.black);
		priceLabel.setBounds(524,350,250,60);
		
		stockLabel = new JLabel();
		stockLabel.setText("STOCK:");
		stockLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		stockLabel.setForeground(Color.black);
		stockLabel.setBounds(510,440,250,60);
		
		typeLabel = new JLabel();
		typeLabel.setText("TYPE:");
		typeLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		typeLabel.setForeground(Color.black);
		typeLabel.setBounds(548,530,250,60);
		
		
		
		
		
		
		this.add(frameInfoLabel);
		this.add(nameLabel);
		this.add(priceLabel);
		this.add(stockLabel);
		this.add(typeLabel);
		
		
	}
	
	private void setTextFields() {
		
		nameField = new JTextField();
		nameField.setBounds(690,270,300,40);
		nameField.setFont(new Font("Abadi", Font.PLAIN,20));
		
		priceField = new JTextField();
		priceField.setBounds(690,365,300,40);
		priceField.setFont(new Font("Abadi", Font.PLAIN,20));
		
		stockField = new JTextField();
		stockField.setBounds(690,450,300,40);
		stockField.setFont(new Font("Abadi", Font.PLAIN,20));
		
		typeField = new JTextField();
		typeField.setBounds(690,540,300,40);
		typeField.setFont(new Font("Abadi", Font.PLAIN,20));
		
		
		this.add(nameField);
		this.add(priceField);
		this.add(stockField);
		this.add(typeField);
		
	}
	
	/**
	 * back button disposes the frame and create ModifyForManagerFrame
	 * enter button takes the input given in the text fields and attempt to create an item
	 */
	private void setButtons() {
		
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new ModifyForManagerFrame();
		});
		this.add(backButton);
		
		enterButton = new JButton();
		enterButton.setBounds(700,650,110,55);
		enterButton.setBackground(Color.gray);
		enterButton.setOpaque(true);
		ImageIcon enterIcon = new ImageIcon("enter.png");
		enterIcon = resizeIcon(enterIcon,110,55);
		enterButton.setIcon(enterIcon);
		enterButton.addActionListener(e -> 
		{
			try {
				
				String name = nameField.getText();
				name = name.trim();
				double price = Double.parseDouble(priceField.getText().trim());
				int stock = Integer.parseInt(stockField.getText());
				String type = typeField.getText();
				if (name.length() == 0 || type.length() == 0) throw new IllegalArgumentException();
				
				AnyItem newItem = null;
				
				ArrayList<MenuItem<? extends AnyItem>> menuItemList = Menu.getMenuList(0).getMenuItemsList();
				
				boolean added = false;
				
				
				
				
				switch (type) {
					
				case "Soup":
					newItem = new Soup(name,price,stock);
					for (int i = 0 ; i < menuItemList.size() ; i++) {
						if (menuItemList.get(i).getName().equals("Soups")) {
							((MenuItem<AnyItem>) menuItemList.get(i)).addItem(newItem);
							added = true;
						}
					}
					break;
				case "Dessert":
					newItem = new Dessert(name,price,stock);
					for (int i = 0 ; i < menuItemList.size() ; i++) {
						if (menuItemList.get(i).getName().equals("Desserts")) {
							((MenuItem<AnyItem>) menuItemList.get(i)).addItem(newItem);
							added = true;
						}
					}
					break;
				case "Drink":
					newItem = new Drink(name,price,stock);
					for (int i = 0 ; i < menuItemList.size() ; i++) {
						if (menuItemList.get(i).getName().equals("Drinks")) {
							((MenuItem<AnyItem>) menuItemList.get(i)).addItem(newItem);
							added = true;
						}
					}
					break;
				case "Main Dish":
					newItem = new MainDish(name,price,stock);
					for (int i = 0 ; i < menuItemList.size() ; i++) {
						if (menuItemList.get(i).getName().equals("Main Dishes")) {
							((MenuItem<AnyItem>) menuItemList.get(i)).addItem(newItem);
							added = true;
						}
					}
					break;
				case "Starter":
					newItem = new Starter(name,price,stock);
					for (int i = 0 ; i < menuItemList.size() ; i++) {
						if (menuItemList.get(i).getName().equals("Starters")) {
							((MenuItem<AnyItem>) menuItemList.get(i)).addItem(newItem);
							added = true;
						}
					}
					break;
				}
				
				if (added) {
					JOptionPane.showMessageDialog(null, "Item added succesfully!");
					dispose();
					new ModifyForManagerFrame();
				}
				else JOptionPane.showMessageDialog(null, "Failed! The category "+type+
														" couldn't found in menu items.");
				
				
				
				
			}
			catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Invalid price or stock!");
			}
			catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(null, "Enter a valid name or type!");
			}
			
			
			
		});
		
		
		
		
		
		
		this.add(enterButton);
	}
	
}
