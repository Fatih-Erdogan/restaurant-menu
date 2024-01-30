package frames;

import java.awt.Color;
import java.awt.Image;
import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.JFrame;




/**
 * All other frames are subclass of this frame 
 * General constant implementations here to reusebility
 */
public class MyFrame extends JFrame {

	/**
	 * sets name of the frame
	 * stops the code when closed the tab
	 * sets size and location and background of the window
	 * @param name - frame name
	 */
	public MyFrame(String name) {
		
		super(name);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(1540,830);
		this.setVisible(true);
		this.setLocation(0,0);
		this.getContentPane().setBackground(Color.gray);
	}
	
	/**
	 * in some frames images are need to be resized so I invoke this method to resize them
	 * @param icon - icon to be modified
	 * @param width - new width
	 * @param height - new height
	 * @return resized icon
	 */
	protected static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon resizedIcon = new ImageIcon(resizedImg);
		return resizedIcon;
	}
	
	/**
	 * Almost every frame has a back button at the same place so no need to create it every single time
	 * @return back button
	 */
	protected static JButton generateBackButton() {
		JButton backButton = new JButton();
		backButton.setBounds(1300,670,90,60);
		backButton.setOpaque(true);
		backButton.setBackground(Color.gray);
		ImageIcon backIcon = new ImageIcon("images/back1.png");
		backButton.setIcon(resizeIcon(backIcon,90,60));
		return backButton;
	}
	
}
