package frames;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import customer.Session;
import customer.Table;
import manager.Manager;

public class DisplayOrdersWithTablesFrame extends MyFrame {

	//private Manager manager;
	private JButton backButton;
	private JLabel frameInfoLabel;
	private ArrayList<JButton> tableButtons = new ArrayList<>();
	
	
	
	public DisplayOrdersWithTablesFrame() {
		super("Orders With Tables");
		//this.manager = manager;
		setLabels();
		setButtons();
	}
	
	
	private void setLabels() {
		
		frameInfoLabel = new JLabel("Tables");
		frameInfoLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		frameInfoLabel.setBounds(650,65,600,100);
		frameInfoLabel.setForeground(Color.black);
		
		this.add(frameInfoLabel);
		
	}
	
	/**
	 * displays only the tables with customers and when clicked on the button it disposes the frame and creates
	 * DisplayOneByOneTableOrdersForManagersFrame by giving Session obj as parameter
	 */
	private void setButtons() {
		
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new AllSalesAndOrdersFrame();
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
	
	
	private JButton generateTableButton(Table t) {
		
		JButton button = new JButton();
		button.setText("TABLE: "+String.valueOf(t.getTableNum()));
		button.setForeground(Color.black);
		button.setFont(new Font("Abadi", Font.PLAIN,25));
		button.setOpaque(true);
		button.setFocusable(false);
		
		
		if (t.isAvailable()) button.setVisible(false);
		
		if (!t.isAvailable()) {
			Session theSession = Session.getSessionsMap().get(t.getTableNum());
			button.addActionListener(e -> 
			{
				dispose();
				new DisplayOneByOneTableOrdersForManagersFrame(theSession);
					
			});
		}
		return button;
	
	
	}
	
	
	
	
	
	
	
	
}
