package actions;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import view.workspace.MyTab;



public class CloseAllTabsAction extends MyAbsAction {

	public CloseAllTabsAction() {
		putValue(SMALL_ICON, loadIcon("images/toolbar/closeall.png"));
		putValue(NAME, "Close all tabs");
		putValue(SHORT_DESCRIPTION, "Close all tabs");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int input = JOptionPane.showConfirmDialog(null, 
                "Da li zelite da zatvorite sve tabove?", "Izaberite opciju ",JOptionPane.YES_NO_OPTION);

		
		if(input==0){
			MyTab.getInstance().getTabbedPane().removeAll();
		}else{
			
		}
		
	}
	
}
