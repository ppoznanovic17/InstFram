package commands;

import model.Node;

public interface AbstractCommand {

	public void UndoCommand();
	public void RedoCommand();
	
}
