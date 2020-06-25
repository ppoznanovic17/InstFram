package actions;

import java.awt.event.ActionEvent;

import commands.CommandManager;

public class UndoAction extends MyAbsAction {

	public UndoAction() {
		putValue(SMALL_ICON, loadIcon("images/toolbar/undo.png"));
		putValue(NAME, "Undo ");
		putValue(SHORT_DESCRIPTION, "Undo");	
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int pointer= CommandManager.getCommandMannager().getCurrentCommand();
		if(pointer==-1){
			return;
		}
		CommandManager.getCommandMannager().getCommands().get(pointer).UndoCommand();
		CommandManager.getCommandMannager().smanjiPointer();
		
	}

}
