package actions;

import java.awt.event.ActionEvent;

import commands.CommandManager;

public class RedoAction extends MyAbsAction {

	public RedoAction() {
		putValue(SMALL_ICON, loadIcon("images/toolbar/redo.png"));
		putValue(NAME, "Redo ");
		putValue(SHORT_DESCRIPTION, "Redo");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CommandManager.getCommandMannager().povecajPointer();
		int pointer= -1;
		if(CommandManager.getCommandMannager().getCurrentCommand()>=CommandManager.getCommandMannager().getCommands().size()) {
			CommandManager.getCommandMannager().smanjiPointer();
			return;
		}
		
		pointer= CommandManager.getCommandMannager().getCurrentCommand();
		CommandManager.getCommandMannager().getCommands().get(pointer).RedoCommand();
		
		
	}

}
