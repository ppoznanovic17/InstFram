package view.parametar;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PokreniNakonInstalacije extends JPanel {

	
	
	private JCheckBox box;
	
	public PokreniNakonInstalacije() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		box= new JCheckBox("Pokreni nakon instalacije");
	
		
		this.add(box);
		
	}
	public JCheckBox getBox() {
		return box;
	}

}
