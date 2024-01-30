package frames;

import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

import manager.Manager;
import menu.Menu;
import menu.MenuItem;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;



/**
 *  manager panel access frame
 *  once signed in you have full access to manager properties
 */
public class ManagerVerificationFrame extends MyFrame {

	private JButton backButton;
	private JButton enterButton;
	private JTextField idField;
	private JPasswordField passwordField;
	private JLabel frameInfoLabel;
	private JLabel idLabel;
	private JLabel passwordLabel;
	
	
	/**
	 * sets the frame name
	 * invokes methods setting buttons, fields and labels
	 */
	public ManagerVerificationFrame() {
		super("Manager Verification");
		setButtons();
		setFields();
		setLabels();
	}
	
	/**
	 * sets id and password fields by using a JTextField and a PasswordFied object respectively
	 */
	private void setFields() {
		
		idField = new JTextField();
		idField.setBounds(700,360,300,40);
		idField.setFont(new Font("Abadi", Font.PLAIN,20));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(700,510,300,40);
		passwordField.setFont(new Font("Abadi", Font.PLAIN,20));
		passwordField.setEchoChar('*');
		
		
		this.add(idField);
		this.add(passwordField);
	}
	
	/**
	 * sets actionListener of the enter button to take the given input from id and password labels
	 * after controlling them by passwordValidator method of Manager object gives the permission to modify menu and MenuItem objects and allows access to all manager properties
	 * if id and password check is successful, disposes frame and creates ModifyOrDisplayFrame, else stays in the same frame
	 * sets actionListener of the back button to dispose and create MainMenuFrame
	 */
	private void setButtons() {
		
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new MainMenuFrame();
		});
		
		
		enterButton = new JButton();
		enterButton.setBounds(700,640,110,55);
		enterButton.setBackground(Color.gray);
		enterButton.setOpaque(true);
		ImageIcon enterIcon = new ImageIcon("enter.png");
		enterIcon = resizeIcon(enterIcon,110,55);
		enterButton.setIcon(enterIcon);
		enterButton.addActionListener(e -> 
		{
			try {
				String idStr = idField.getText();
				char[] passwordArray = passwordField.getPassword();
				String password = new String(passwordArray);
				int id = Integer.parseInt(idStr.trim());
				
				if (Manager.getManagersMap().containsKey(id) && Manager.getManagersMap().get(id).passwordValidator(password)) {
					Manager theManager = Manager.getManagersMap().get(id);
					JOptionPane.showMessageDialog(null, "Hello "+theManager.getName()+"! Ready to display or modify.");
					Menu.requestPersmission(theManager.getId(), password);
					MenuItem.requestPersmission(theManager.getId(), password);
					dispose();
					new ModifyOrDisplayFrame();
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid ID or password!");

				}
			}
			// catches if user entered a string instead of number in id part
			catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null,"Invalid ID! It must be an integer.");
			}
			
			
		});
		
		
		this.add(backButton);
		this.add(enterButton);
	}
	
	
	private void setLabels() {
		
		frameInfoLabel = new JLabel();
		frameInfoLabel.setText("Manager Verification");
		frameInfoLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		frameInfoLabel.setForeground(Color.black);
		frameInfoLabel.setBounds(500,150,600,100);
		
		idLabel = new JLabel();
		idLabel.setText("ID:");
		idLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		idLabel.setForeground(Color.black);
		idLabel.setBounds(590,350,60,60);
		
		passwordLabel = new JLabel();
		passwordLabel.setText("PASSWORD:");
		passwordLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		passwordLabel.setForeground(Color.black);
		passwordLabel.setBounds(400,500,250,60);
		
		
		
		this.add(frameInfoLabel);
		this.add(idLabel);
		this.add(passwordLabel);
		
	}

	
	
	
}
