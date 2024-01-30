package frames;

import java.awt.Color;
import java.awt.Font;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import items.AnyItem;
import menu.Menu;
import menu.MenuItem;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import manager.Manager;

public class CreateMenuItemFrame extends MyFrame {

	private JButton backButton;
	private JButton enterButton;
	private JTextField nameField;
	private JTextField pathField;
	private JLabel frameInfoLabel;
	private JLabel nameLabel;
	private JLabel pathLabel;
	
	/**
	 * sets name of the field 
	 * invokes private button fild and label setter methods
	 */
	public CreateMenuItemFrame() {
		super("Create Menu Item");
		setButtons();
		setFields();
		setLabels();
		
	}
	
	private void setFields() {
		
		nameField = new JTextField();
		nameField.setBounds(690,360,300,40);
		nameField.setFont(new Font("Abadi", Font.PLAIN,20));
		
		pathField = new JTextField();
		pathField.setBounds(690,510,300,40);
		pathField.setFont(new Font("Abadi", Font.PLAIN,20));
		
		
		
		this.add(nameField);
		this.add(pathField);
	}
	
	/**
	 * back button disposes and creates ModifyForManagerFrame
	 * enter button takes the input given in the name and path fields and if the path location exists in the
	 * computer attempts to create a MenuItem object then disposes and creates ModifyForManagerFrame
	 */
	private void setButtons() {
		
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new ModifyForManagerFrame();
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
			String name = nameField.getText();
			String pathStr = pathField.getText();
			
			Path path = Paths.get(pathStr);
			if (Files.exists(path)) {
				MenuItem<? extends AnyItem> newMenuItem = new MenuItem<>(name);
				newMenuItem.setImagePath(pathStr);
				Menu.getMenuList(0).addMenuItem(newMenuItem);
				JOptionPane.showMessageDialog(null, "Menu item successfully "
											+ "created and added to the menu.");
				dispose();
				new ModifyForManagerFrame();
			}
			else {
				JOptionPane.showMessageDialog(null, "Path couldn't found");
			}
			
			
		});
		
		this.add(backButton);
		this.add(enterButton);
	
	}
	
	private void setLabels() {
		
		frameInfoLabel = new JLabel();
		frameInfoLabel.setText("Menu Item Creation");
		frameInfoLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		frameInfoLabel.setForeground(Color.black);
		frameInfoLabel.setBounds(500,150,600,100);
		
		nameLabel = new JLabel();
		nameLabel.setText("NAME:");
		nameLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		nameLabel.setForeground(Color.black);
		nameLabel.setBounds(510,350,150,60);
		
		pathLabel = new JLabel();
		pathLabel.setText("PATH:");
		pathLabel.setFont(new Font("Abadi", Font.PLAIN,40));
		pathLabel.setForeground(Color.black);
		pathLabel.setBounds(510,500,250,60);
		
		
		
		this.add(frameInfoLabel);
		this.add(nameLabel);
		this.add(pathLabel);
	
	}
	
	
	
}
