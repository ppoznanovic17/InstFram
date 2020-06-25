package view.parametar;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LookAndFeel extends JPanel{
	
	

	private JCheckBox box;
	private JComboBox<String> cmb;
	
	
	public LookAndFeel() {
		box= new JCheckBox("Look and Feel");
		JLabel lbl= new JLabel("Ukoliko je polje Look and Feel stiklirano, izaberite\n boju koju zeite da primenite");
		cmb= new JComboBox<String>();
		cmb.addItem("plavo");
		cmb.addItem("roze");
		JPanel cmbpnl= new JPanel();
		cmbpnl.setPreferredSize(new Dimension(50,15));
		cmbpnl.add(cmb);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
	
		this.add(box);
		this.add(lbl);
		this.add(cmbpnl);
		
	}
	
	public JCheckBox getBox() {
		return box;
	}
	public JComboBox<String> getCmb() {
		return cmb;
	}
	
}
