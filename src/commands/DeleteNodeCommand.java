package commands;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import message.Message;
import model.Node;
import model.Parametar;
import model.Proizvod;
import view.workspace.MainFrame;
import view.workspace.MyTab;
import view.workspace.WorkZone;

public class DeleteNodeCommand implements AbstractCommand {

	private Node roditelj;
	private Node dete;
	private int  pozicija;
	private ArrayList<Node> deca;
	public DeleteNodeCommand(Node n,Node m) {
		roditelj=n;
		dete=m;
		deca= roditelj.getDeca();
		pozicija=roditelj.getDeca().indexOf(dete);
	}
	
	@Override
	public void UndoCommand() {
		roditelj.getDeca().add(pozicija,dete);
		SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
		/*for(Node n:WorkZone.getInstance().getRoot().getDeca()) {
			
		}*/
	
	}

	@Override
	public void RedoCommand() {
	
			if(dete instanceof Proizvod) {
				
				WorkZone.getInstance().getRoot().getDeca().remove(dete);
			}
			roditelj.obrisiDete(dete);
			
		
	SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());	
	}

}
