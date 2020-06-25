package view.parametar;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Naziv extends JPanel {

	
	private JLabel name;
	
	
	public Naziv(String n) {
		String ime= n;
		name= new JLabel();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		name.setText(ime);
		this.add(name);
		
		
	}
	
	
}
