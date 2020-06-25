package actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import view.workspace.MyTab;



public class CloseTabAction extends MyAbsAction {

	
	
	public CloseTabAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_C, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("images/toolbar/tab.png"));
		putValue(NAME, "Close tab");
		putValue(SHORT_DESCRIPTION, "Close tab");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int index = MyTab.getInstance().getTabbedPane().getSelectedIndex();
		 MyTab.getInstance().getTabbedPane().remove(index);
			MyTab.getParametre().remove(index);
			 
			SwingUtilities.updateComponentTreeUI(MyTab.getInstance());
		
		
	}
}
