package view.parametar;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DesktopShortcut extends JPanel {

	
	private JCheckBox box;
	
	public DesktopShortcut() {
		box= new JCheckBox("Desktop Shortcut");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(box);
		
		
	}
	public JCheckBox getBox() {
		return box;
	}

}
