package frames;

import customer.*;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DisplayAvailableTablesForCustomersFrame extends MyFrame {

	private Session session;
	private JButton backButton;
	private JLabel frameInfoLabel;
	private ArrayList<JButton> tableButtons = new ArrayList<>();
	
	/**
	 * sets name of the frame
	 * invokes private button and label setter methods
	 */
	public DisplayAvailableTablesForCustomersFrame(Session s) {
		
		super("Display Available Tables");
		this.session = s;
		setButtons();
		setLabels();

	}
	
	/**
	 * sets actionListener of back button to dispose and create ChangeTableOrDisplayMenuItemsFrame by giving Session object as parameter
	 * assumes a fixed numbers of 10 tables
	 * invokes private generateTableButton method to create a button for each table
	 * sets the location of every single table button in the frame
	 */
	private void setButtons() {
		
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new ChangeTableOrDisplayMenuItemsFrame(session);
		});
		
		this.add(backButton);
		for (Table t : Table.getAllTables()) {
			tableButtons.add(generateTableButton(t));
		}
		
		tableButtons.get(0).setBounds(160,200,200,150);
		tableButtons.get(1).setBounds(480,200,200,150);
		tableButtons.get(2).setBounds(800,200,200,150);
		tableButtons.get(3).setBounds(1120,200,200,150);
		tableButtons.get(4).setBounds(160,400,200,150);
		tableButtons.get(5).setBounds(480,400,200,150);
		tableButtons.get(6).setBounds(800,400,200,150);
		tableButtons.get(7).setBounds(1120,400,200,150);
		tableButtons.get(8).setBounds(480,600,200,150);
		tableButtons.get(9).setBounds(800,600,200,150);
		
		
		
		
		
		for (JButton tButton : tableButtons) {
			this.add(tButton);
		}
		
	}
	
	/**
	 * creates a JButton with appropriate background color (according to availability) and sets actionListener according to availability
	 * if table is available and suitable in terms of chair number, then changes the Session object's Table object then disposes and creates DisplayMenuItemsFrame by giving Session obj as parameter 
	 * if table is not available, it displays a warning message
	 * @return JButton object representing a table
	 */
	private JButton generateTableButton(Table t) {
		
		JButton button = new JButton();
		button.setText(t.getChairNum()+" Chairs");
		button.setForeground(Color.black);
		button.setFont(new Font("Abadi", Font.PLAIN,25));
		button.setOpaque(true);
		button.setFocusable(false);
		
		
		
		
		if (session.getTable()==t) {
			button.setBackground(Color.orange);
			button.addActionListener(e -> 
			{
				JOptionPane.showMessageDialog(null, "This is your table.");
			});
		}
		
		else if (t.isAvailable() && session.getCustomer().getNumOfPerson() <= t.getChairNum()) {
			button.setBackground(Color.green);
			button.addActionListener(e -> 
			{
				session.changeTable(t);
				JOptionPane.showMessageDialog(null, "Table succesfully changed!\n New table number is: "+session.getSessionNumber());
				dispose();
				new DisplayMenuItemsFrame(session);	
			});
		}
		
		else if (t.isAvailable() && session.getCustomer().getNumOfPerson() > t.getChairNum()) {
			button.setBackground(Color.red);
			button.addActionListener(e -> 
			{
				JOptionPane.showMessageDialog(null, "Not enough chair!");
			});
			
		}	
		
		else if (!t.isAvailable()) {
			button.setBackground(Color.red);
			button.addActionListener(e -> 
			{
				JOptionPane.showMessageDialog(null, "Table is not available!");
			});
		}
		
		
			
		return button;
	}
	
	private void setLabels() {
		
		frameInfoLabel = new JLabel("Tables");
		frameInfoLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		frameInfoLabel.setBounds(650,65,600,100);
		frameInfoLabel.setForeground(Color.black);
		
		this.add(frameInfoLabel);
		
	}
	
	
	
	
	
	
	
	
	
}
