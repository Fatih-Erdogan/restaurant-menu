package frames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import items.AnyItem;
import manager.Manager;
import menu.Menu;
import menu.MenuItem;


public class ModifyForManagerFrame extends MyFrame {

	private JButton newMenuItemButton;
	private JButton newItemButton;
	private JButton removeMenuItemButton;
	private JButton removeItemButton;
	private JButton backButton;
	
	
	/**
	 * sets frame name
	 * invokes private button setter method
	 */
	public ModifyForManagerFrame() {
		super("Modify For Manager");
		setButtons();
	}
	
	
	/**
	 * back button disposes the frame and create ModifyOrDisplayFrame without arguments
	 * new menu item button disposes the frame and create CreateMenuItemFrame
	 * new item button disposes the frame and create CreateItemFrame
	 * remove menu item button takes the name of the menu item to remove as input via JOptionPane and look for the item in menu object
	 * if it finds it it removes the MenutItem and display a message, if not displays a warning message
	 * remove item button does the same thing as remove menu item button for an object extending AnyItem object
	 */
	private void setButtons() {
		
		backButton = generateBackButton();
		backButton.setBounds(1370,670,90,60);
		backButton.addActionListener(e -> 
		{
			dispose();
			new ModifyOrDisplayFrame();
		});
		
		newMenuItemButton = new JButton();
		newMenuItemButton.setBounds(125,70,560,300);
		newMenuItemButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		newMenuItemButton.setForeground(Color.black);
		newMenuItemButton.setText("Create New Menu");
		newMenuItemButton.setFocusable(false);
		newMenuItemButton.setBackground(Color.DARK_GRAY);
		newMenuItemButton.addActionListener(e -> 
		{
			dispose();
			new CreateMenuItemFrame();

		});
		
		
		
		newItemButton = new JButton();
		newItemButton.setBounds(750,70,560,300);
		newItemButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		newItemButton.setForeground(Color.black);
		newItemButton.setText("Create New Item");
		newItemButton.setFocusable(false);
		newItemButton.setBackground(Color.DARK_GRAY);
		newItemButton.addActionListener(e -> 
		{
			dispose();
			new CreateItemFrame();
		});
		
		
		
		
		removeMenuItemButton = new JButton();
		removeMenuItemButton.setBounds(125,430,560,300);
		removeMenuItemButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		removeMenuItemButton.setForeground(Color.black);
		removeMenuItemButton.setText("Remove Menu Item");
		removeMenuItemButton.setFocusable(false);
		removeMenuItemButton.setBackground(Color.DARK_GRAY);
		removeMenuItemButton.addActionListener(e -> 
		{
			String menuItemName = JOptionPane.showInputDialog("Name of menu item to remove:");

			MenuItem<AnyItem> toRemove = null;
			for (MenuItem<? extends AnyItem> m : Menu.getMenuList(0).getMenuItemsList()) {
				 if (m.getName().equals(menuItemName)) {
					 toRemove = ((MenuItem<AnyItem>) m);
					 break;
				 }
			}
			
			if (toRemove == null) JOptionPane.showMessageDialog(null, "Menu item is not in the list!");
			else {
				Menu.getMenuList(0).removeMenuItem(toRemove);
				JOptionPane.showMessageDialog(null, menuItemName+" removed succesfully.");
			}
			
		});
	
		removeItemButton = new JButton();
		removeItemButton.setBounds(750,430,560,300);
		removeItemButton.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		removeItemButton.setForeground(Color.black);
		removeItemButton.setText("Remove Item");
		removeItemButton.setFocusable(false);
		removeItemButton.setBackground(Color.DARK_GRAY);
		removeItemButton.addActionListener(e -> 
		{
			String itemName = JOptionPane.showInputDialog("Name of item to remove:");
			itemName = itemName.trim();
			System.out.println(itemName);
			AnyItem toRemove = null;
			
			for (MenuItem<? extends AnyItem> m : Menu.getMenuList(0).getMenuItemsList()) {
				for (AnyItem i : m.getItemList()) {
					if (i.getName().equals(itemName)) {
						toRemove = i;
						break;
					}
				}
				if (toRemove != null) {
					((MenuItem<AnyItem>)m).removeItem(toRemove);
					JOptionPane.showMessageDialog(null, itemName+" removed succesfully.");
					break;
				}
			}
			if(toRemove == null) JOptionPane.showMessageDialog(null, "Couldn't removed.");
		});
		
		
		this.add(backButton);
		this.add(newMenuItemButton);
		this.add(newItemButton);
		this.add(removeMenuItemButton);
		this.add(removeItemButton);
	}
	
	
	
}
