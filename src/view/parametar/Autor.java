package view.parametar;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Autor extends JPanel {
	
	private JLabel name;
	
	
	public Autor(String n) {
		String ime= n;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		name= new JLabel();
		name.setText(ime);
		this.add(name);
		
		
	}
	

}
