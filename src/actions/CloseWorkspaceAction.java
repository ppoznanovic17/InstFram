package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;



public class CloseWorkspaceAction extends MyAbsAction {

	private static final int JButton = 0;

	public CloseWorkspaceAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/toolbar/close.png"));
		putValue(NAME, "Close file");
		putValue(SHORT_DESCRIPTION, "Close file");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
