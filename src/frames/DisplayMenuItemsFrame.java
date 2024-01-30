package frames;

import customer.*;
import menu.Menu;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import menu.MenuItem;
import menu.Menu;
import items.Soup;
import items.AnyItem;

public class DisplayMenuItemsFrame extends MyFrame {

	private JButton backButton;
	private Session session;
	private ArrayList<JButton> menuButtons = new ArrayList<>();
	private ArrayList<JLabel> labelsList = new ArrayList<>();
	
	
	/**
	 * sets the frame name
	 * invokes private button setter method
	 */
	public DisplayMenuItemsFrame(Session s) {
		super("Display Menu Items");
		this.session = s;
		setButtons();
	}
	
	/**
	 * sets actionListener of back button to dispose and create OrderOverviewForCustomerFrame by giving Session object as parameter
	 * generates an imaged button for every MenuItem and sets actionListener of these buttons to create DisplayItemsForCustomersFrame by a name and Session parameters
	 * name parameter is used for finding which MenuItem's button created the isplayItemsForCustomersFrame frame to display related items
	 */
	private void setButtons() {
		
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new OrderOverviewForCustomerFrame(session);
		});
		
		this.add(backButton);
		
		generateMenuButtons();
		for (JButton button : menuButtons) this.add(button);
		for (JLabel label : labelsList) this.add(label);
	}
	
	private void generateMenuButtons() {
		
		ArrayList<MenuItem<? extends AnyItem>> menuItemsList = Menu.getMenuList(0).getMenuItemsList();
		
		int count = 0;
		int count2 = 0;
		
		
		
		for (MenuItem<? extends AnyItem> m : menuItemsList) {
			m = ((MenuItem<AnyItem>) m);
			JButton button = new JButton();
			button.setBounds(230+(count*400),70+(count2*320),300,200);
			
			ImageIcon mmIcon = new ImageIcon(m.getImagePath().toString());
			mmIcon = resizeIcon(mmIcon,333,198);
			button.setIcon(mmIcon);
			button.setVisible(true);
			
			String name = m.getName();
			
			button.addActionListener(e -> 
			{
				dispose();
				new DisplayItemsForCustomersFrame(name, session);
			});
			
			
			
			menuButtons.add(button);
			
			JLabel label = new JLabel(m.getName());
			label.setFont(new Font("Harlow Solid Italic", Font.PLAIN,40));
			label.setBounds((380+(count*400))-(9*(m.getName().length())),(70+(count2*320))+230,300,45);
			labelsList.add(label);
			
			count++;
			count = count%3;
			count2 = count == 0 ? count2+1 : count2;
		}
		
		
		
	}
	
	
	
}
