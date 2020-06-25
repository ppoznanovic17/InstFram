package cut_copy_paste;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import actions.MyAbsAction;
import commands.CommandManager;
import commands.PasteCommand;
import model.Modul;
import model.Node;
import model.Proizvod;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class Paste extends MyAbsAction{

	public Paste() {
		putValue(SMALL_ICON, loadIcon("images/toolbar/paste.png"));
		putValue(NAME, "Paste");
		putValue(SHORT_DESCRIPTION, "Paste");
		}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Node n= (Node) WorkZone.getInstance().getjTree().getLastSelectedPathComponent();
		if(n instanceof Modul) {
			CommandManager.getCommandMannager().addCommand(new PasteCommand(n,MainFrame.getInstance().getTipPaste()));
		}if(n instanceof Proizvod ) {
			CommandManager.getCommandMannager().addCommand(new PasteCommand(n,MainFrame.getInstance().getTipPaste()));
		}
		else {
			return;
		}
		
		
	}

}
