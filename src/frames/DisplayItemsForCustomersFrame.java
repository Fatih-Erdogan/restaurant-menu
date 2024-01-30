package frames;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import customer.Session;
import items.AnyItem;
import menu.MenuItem;
import menu.Menu;
import java.awt.Color;

public class DisplayItemsForCustomersFrame extends MyFrame {

	private JButton backButton;
	private Session session;
	private MenuItem<? extends AnyItem> menuItem;
	private ArrayList<JButton> buttonList = new ArrayList<>();
	private ArrayList<JLabel> labelList = new ArrayList<>();
	
	/**
	 * sets name of the frame
	 * invokes private button setter method
	 * @param menuItemName - name of the MenuItem obj whose content will be displayed
	 */
	public DisplayItemsForCustomersFrame(String menuItemName, Session session) {
		super("Display Items");
		this.session = session;
		this.menuItem = findMenuItem(menuItemName);
		setButtons();
	}
	
	/**
	 * finds the MenuItem object by using the name provided
	 * @param name - name of the MenuItem
	 * @return the MenuItem object to be displayed
	 */
	private MenuItem<? extends AnyItem> findMenuItem(String name) {
		
		Menu.getMenuList(0).getMenuItemsList();
		for (MenuItem<? extends AnyItem> mi : Menu.getMenuList(0).getMenuItemsList()) {
			mi = ((MenuItem<AnyItem>) mi);
			if (mi.getName() == name) {
				return mi;
			}
		}
		return null;
	}
	
	
	/**
	 * sets back button to dispose and create DisplayMenuItemsFrame with providing Session object as parameter
	 * adds buttons and labels to the frame
	 */
	private void setButtons() {
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new DisplayMenuItemsFrame(session);
		});
		
		this.add(backButton);
		
		generateButtonsAndLabels();
		for (JButton b : buttonList) this.add(b);
		for (JLabel l : labelList) this.add(l);
		
		
		
	}
	
	/**
	 * displays items in the given MenuItem
	 * if an item doesn't have stock, it will be grayed out and plus button will disappear
	 * creates a button which is used as total price calculator
	 * plus and minus buttons adds or removes items to/from the Session
	 */
	private void generateButtonsAndLabels() {
		
		int count = 0;
		int many = menuItem.getItemList().size();
		if (many == 0) many = 1;
		int step = 500/many > 55 ? 500/many : 55;
		for (AnyItem item : menuItem.getItemList()) {
			
			JLabel nameLabel = new JLabel(item.getName());
			nameLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,40));
			nameLabel.setForeground(item.isAvailable() ? Color.green : Color.DARK_GRAY);
			nameLabel.setBounds(200,160+(count*step),300,45);
			
			JLabel priceLabel = new JLabel(String.valueOf(item.getPrice()));
			priceLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,40));
			priceLabel.setBounds(800,160+(count*step),300,45);
			
			JButton plus = new JButton();
			ImageIcon plusIcon = new ImageIcon("images/plus.png");
			plus.setBounds(1200,160+(count*step),40,40);
			plusIcon = resizeIcon(plusIcon,39,39);
			plus.setIcon(plusIcon);
			plus.setVisible(item.isAvailable() ? true : false);
			plus.setBackground(Color.gray);
			
			JButton minus = new JButton();
			ImageIcon minusIcon = new ImageIcon("images/minus.png");
			minus.setBounds(1255,160+(count*step),40,40);
			minusIcon = resizeIcon(minusIcon,40,40);
			minus.setIcon(minusIcon);
			minus.setVisible(session.getOrders().getOrderList().contains(item) ? true : false);
			minus.setBackground(Color.gray);
			minus.addActionListener(e -> 
			{
				session.removeItem(item);
				minus.setVisible(session.getOrders().getOrderList().contains(item) ? true : false);
				plus.setVisible(item.isAvailable() ? true : false);
				nameLabel.setForeground(item.isAvailable() ? Color.green : Color.DARK_GRAY);
			});
			
			plus.addActionListener(e -> 
			{
				session.addNewItem(item);
				plus.setVisible(item.isAvailable() ? true : false);
				minus.setVisible(session.getOrders().getOrderList().contains(item) ? true : false);
				nameLabel.setForeground(item.isAvailable() ? Color.green : Color.DARK_GRAY);
			});
			
			
			buttonList.add(plus);
			buttonList.add(minus);
			labelList.add(nameLabel);
			labelList.add(priceLabel);
			count++;
		}
		
		JLabel frameLabel = new JLabel(String.format("%-32s%s", "ITEM NAME", "PRICE"));
		frameLabel.setFont(new Font("Abadi", Font.PLAIN,45));
		frameLabel.setForeground(Color.BLACK);
		frameLabel.setBounds(200,80,800,50);
		
		
		JLabel totalPrice = new JLabel(String.valueOf(session.getTotalPrice())+" TL");
		totalPrice.setFont(new Font("Abadi", Font.PLAIN,45));
		totalPrice.setForeground(Color.black);
		totalPrice.setBounds(1200,90,200,50);
		
		
		JButton receiptButton = new JButton();
		ImageIcon receiptIcon = new ImageIcon("images/receipt.png");
		receiptButton.setBounds(1100,90,50,50);
		receiptIcon = resizeIcon(receiptIcon,50,50);
		receiptButton.setIcon(receiptIcon);
		receiptButton.setBackground(Color.gray);
		receiptButton.addActionListener(e -> 
		{
			double realTotalPrice = session.getTotalPrice();
			totalPrice.setText(String.valueOf(realTotalPrice+" TL"));
		});
		
		
		
		
		
		this.add(totalPrice);
		this.add(receiptButton);
		this.add(frameLabel);
	}
	
	
	
	
	
	
	
	
	
	
}
