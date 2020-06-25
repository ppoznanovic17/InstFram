package view.parametar;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UsloviKoriscenja extends JPanel {
	

	private JTextField txt;
	
	public UsloviKoriscenja(String s) {

		
		txt= new JTextField(s);
		txt.setText(s);
		txt.setEnabled(false);
	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.add(txt);
		
		
	}
	
	
	
	
}
