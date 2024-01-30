package frames;

import java.awt.Color;
import java.awt.Font;
import customer.Customer;
import customer.Session;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CustomerCreationFrame extends MyFrame {
	
	private JButton enterButton;
	private JButton backButton;
	private JTextField nameField;
	private JTextField ageField;
	private JTextField personNumField;
	private JLabel nameLabel;
	private JLabel ageLabel;
	private JLabel personNumLabel;
	private JLabel frameInfoLabel;
	
	/**
	 * invokes private methods setting buttons labels and text fields
	 */
	public CustomerCreationFrame() {
		
		super("Customer Creation");
		setButtons();
		setLabels();
		setTextFields();
	}
	
	/**
	 * sets the actionListener of back button to dispose and create NewOrExistingFrame
	 * sets the actionListener of enter button to take the input given in  the name age and person number fields then attempt to create a new Session
	 * if a new Session is successfully created, it displays the table number with a JOptionPane and creates ChangeTableOrDisplayMenuItemsFrame by giving the created Session as parameter
	 * if Session creation failed it shows related warning and stays in the frame
	 */
	private void setButtons() {
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new NewOrExistingFrame();
		});
		
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
				String ageStr = ageField.getText();
				ageStr = ageStr.trim();
				String personNumStr = personNumField.getText();
				personNumStr = personNumStr.trim();
				int age = Integer.parseInt(ageStr);
				int personNum = Integer.parseInt(personNumStr);
				
				Customer newCustomer = new Customer(name,age,personNum);
				Session newSession = Session.createSession(newCustomer);
				if (newSession == null) {
					JOptionPane.showMessageDialog(null,"No available table!");
				}
				
				else if (newSession != null) {
					JOptionPane.showMessageDialog(null,"Wellcome "+newSession.getCustomer().getName()+"!\n"
													+ "Your table number is: "+newSession.getSessionNumber());
					dispose();
					new ChangeTableOrDisplayMenuItemsFrame(newSession);
				}
			}
			
			catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "Invalid age or person number!");
			}
			catch (NullPointerException npe) {
				System.out.println("No available table, session couldn't created.");
			}	
			
		});
		
		
		this.add(enterButton);
		this.add(backButton);
	}
	
	private void setLabels() {
		
		frameInfoLabel = new JLabel();
		frameInfoLabel.setText("Customer Creation");
		frameInfoLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		frameInfoLabel.setForeground(Color.black);
		frameInfoLabel.setBounds(550,125,600,100);
		
		nameLabel = new JLabel();
		nameLabel.setText("Name:");
		nameLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		nameLabel.setForeground(Color.black);
		nameLabel.setBounds(460,285,120,55);
		
	
		
		
		ageLabel = new JLabel();
		ageLabel.setText("Age:");
		ageLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		ageLabel.setForeground(Color.black);
		ageLabel.setBounds(494,405, 90,55);
		
		
		
		personNumLabel = new JLabel();
		personNumLabel.setText("Number of Person:");
		personNumLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		personNumLabel.setForeground(Color.black);
		personNumLabel.setBounds(245,525,340,55);
		
	
		
		
		this.add(frameInfoLabel);
		this.add(nameLabel);
		this.add(ageLabel);
		this.add(personNumLabel);
	}
	
	private void setTextFields() {
		
		nameField = new JTextField();
		nameField.setBounds(665,295,300,40);
		nameField.setFont(new Font("Abadi", Font.PLAIN,20));
		
		ageField = new JTextField();
		ageField.setBounds(665,415,300,40);
		ageField.setFont(new Font("Abadi", Font.PLAIN,20));
		
		personNumField = new JTextField();
		personNumField.setBounds(665,535,300,40);
		personNumField.setFont(new Font("Abadi", Font.PLAIN,20));
		
		
		this.add(nameField);
		this.add(ageField);
		this.add(personNumField);
		
		
		
	}
	
	
	
	
	
}
