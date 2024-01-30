package frames;

import manager.Manager;
import menu.Menu;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.awt.Color;

public class DisplayStockInfoFrame extends MyFrame {

	private JButton backButton;
	private ArrayList<JLabel> stocksList = new ArrayList<>();
	
	
	/**
	 * sets frame name
	 * invokes private methods setting buttons and labels
	 */
	public DisplayStockInfoFrame() {
		super("Stock Info");
		setButtons();
		setLabels();
	}

	/**
	 * back button disposes the frame and creates DisplayForManagerFrame without argument
	 */
	private void setButtons() {
		backButton = generateBackButton();
		backButton.addActionListener(e -> 
		{
			dispose();
			new DisplayForManagerFrame();
		});
		
		this.add(backButton);
	}
	

	private void setLabels() {
		
		JLabel frameInfoLabel = new JLabel(String.format("%-30s%-30s%s", "Item Name","Stock","Status"));
		frameInfoLabel.setFont(new Font("Abadi", Font.BOLD,25));
		frameInfoLabel.setForeground(Color.black);
		frameInfoLabel.setBounds(60,50,700,26);
		this.add(frameInfoLabel);
		
		JLabel frameInfoLabel2 = new JLabel(String.format("%-25s%-30s%s", "Item Name","Stock","Status"));
		frameInfoLabel2.setFont(new Font("Abadi", Font.BOLD,25));
		frameInfoLabel2.setForeground(Color.black);
		frameInfoLabel2.setBounds(840,50,700,26);
		this.add(frameInfoLabel2);
		
		
		
		setStockLabels();
		for (JLabel label : stocksList) {
			this.add(label);
		}
	
	}
	
	/**
	 * takes a String displaying stock information then it splits into labels and displays them
	 */
	private void setStockLabels() {
		
		String theInfo = Menu.displayStockInfo();
		String[] stocksArray = theInfo.split("\\n");
		int itemNum = stocksArray.length;
		int count = 0;

		while (count < 20 && count < itemNum) {
			JLabel newLabel = new JLabel();
			newLabel.setText(stocksArray[count]);
			newLabel.setFont(new Font("Abadi", Font.PLAIN,20));
			newLabel.setBounds(60,80+(count*30),800,26);
			stocksList.add(newLabel);
			count++;
		}
		while (count > 19 && count < 40 && count < itemNum) {
			JLabel newLabel = new JLabel();
			newLabel.setText(stocksArray[count]);
			newLabel.setFont(new Font("Abadi", Font.PLAIN,20));
			newLabel.setBounds(840,80+((count-20)*30),900,26);
			stocksList.add(newLabel);
			count++;
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
