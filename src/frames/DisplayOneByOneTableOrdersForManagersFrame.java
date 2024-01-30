package frames;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import customer.Session;
import manager.Manager;

public class DisplayOneByOneTableOrdersForManagersFrame extends MyFrame {

	private Session session;
	private JButton backButton;
	private ArrayList<JLabel> labelList = new ArrayList<>();
	private JButton endSessionButton;
	
	public DisplayOneByOneTableOrdersForManagersFrame(Session session) {
		
		super("Display Customer");
		this.session = session;
		display();
		setButtons();
		
	}
	
	private void display() {
		
		String toDisplay = session.displayCurrentSessionOrders();
		String[] theArray = toDisplay.split("\\n");
		 
		int counter = 0;
		for(String s : theArray) {
			JLabel label = new JLabel(s);
			label.setFont(new Font("Abadi", Font.PLAIN,25));
			label.setForeground(Color.black);
			label.setBounds(200,200+(counter*32),1200,30);
			counter++;
			labelList.add(label);
		}
		
		for (JLabel label : labelList) this.add(label);
		
		JLabel header = new JLabel(String.format("%-35s%-38s%s","ITEM","QUANTITY","PRICE"));
		header.setFont(new Font("Abadi", Font.PLAIN,30));
		header.setBounds(200,100,900,35);
		this.add(header);
		
		JLabel totalLabel = new JLabel("TOTAL: "+String.valueOf(session.getTotalPrice())+" TL");
		totalLabel.setFont(new Font("Abadi", Font.PLAIN,30));
		totalLabel.setBounds(170,700,300,35);
		this.add(totalLabel);
	}
	
	private void setButtons() {
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new DisplayOrdersWithTablesFrame();
		});
		this.add(backButton);
		
		endSessionButton = new JButton();
		endSessionButton.setBounds(1200,100,200,50);
		endSessionButton.setBackground(Color.DARK_GRAY);
		endSessionButton.setFont(new Font("Abadi", Font.PLAIN,20));
		endSessionButton.setFocusable(false);
		endSessionButton.setText("END SESSION");
		endSessionButton.setOpaque(true);
		endSessionButton.setForeground(Color.black);
		endSessionButton.addActionListener(e -> 
		{
			session.endSession();
			dispose();
			new DisplayOrdersWithTablesFrame();
		});
		this.add(endSessionButton);
		
	}
	
}
