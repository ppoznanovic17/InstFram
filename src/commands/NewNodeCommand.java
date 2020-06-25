package commands;

import javax.swing.SwingUtilities;

import actions.ActionManager;
import model.Node;
import view.workspace.MyTab;
import view.workspace.WorkZone;

public class NewNodeCommand implements AbstractCommand{

	private Node naKoga;
	private Node dodajeSe;
	//public static int i=0;
	
	public NewNodeCommand(Node parent,Node dodajeSe) {
		naKoga=parent;
		dodajeSe.setName(dodajeSe.getName());
		this.dodajeSe= dodajeSe;
		//i++;
	}
	
	@Override
	public void UndoCommand() {
		naKoga.obrisiDete(dodajeSe);
		//MyTab.getInstance().obrisi(dodajeSe);
		SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
		
	}

	@Override
	public void RedoCommand() {
		naKoga.addNewNode(dodajeSe);
		SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
		
		
	}

}
