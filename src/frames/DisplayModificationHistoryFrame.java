package frames;

import manager.Manager;
import menu.Menu;
import menu.MenuItem;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;

public class DisplayModificationHistoryFrame extends MyFrame {

	private JButton backButton;
	private ArrayList<JLabel> historyLabels = new ArrayList<>();
	private JLabel frameInfoLabel;
	
	/**
	 * sets name of the frame
	 * invokes private button and label setter methods
	 */
	public DisplayModificationHistoryFrame() {
		super("Modification History");
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
		
		frameInfoLabel = new JLabel("Modification History");
		frameInfoLabel.setFont(new Font("Harlow Solid Italic", Font.PLAIN,60));
		frameInfoLabel.setBounds(500,40,600,100);
		frameInfoLabel.setForeground(Color.black);
		
		generateLabels();
		for (JLabel label : historyLabels) {
			this.add(label);
		}
		this.add(frameInfoLabel);
	}
	
	/**
	 * takes two Strings displaying the modification histories then splits them to labels and displays them on the frame
	 */
	private void generateLabels() {
		
		String menuHist = Menu.displayModificationHistory();
		String itemHist = MenuItem.displayModificationHistory();
		
		String[] menuHistArray = menuHist.split("\\n");
		String[] itemHistArray = itemHist.split("\\n");
		
		ArrayList<String> totalHist = new ArrayList<>();
		for (String s : menuHistArray) totalHist.add(s);
		for (String s : itemHistArray) totalHist.add(s);
		int count = 0;
		for(String aHist : totalHist) {
			
			JLabel newLabel = new JLabel(aHist);
			newLabel.setFont(new Font("Abadi", Font.PLAIN,20));
			if (count < 20) newLabel.setBounds(115,140+(count*30),800,26);
			else if (count < 40) newLabel.setBounds(845,140+((count-20)*30),800,26);
			historyLabels.add(newLabel);
			count++;
			
			
			
		}

		
	}
	
	
	
	
	
	
	
	
	
}
