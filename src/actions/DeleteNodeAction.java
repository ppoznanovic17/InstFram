package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import commands.CommandManager;
import commands.DeleteNodeCommand;
import message.Message;
import model.Node;
import model.Parametar;
import model.Proizvod;
import view.workspace.MainFrame;
import view.workspace.MyTab;
import view.workspace.WorkZone;

public class DeleteNodeAction extends MyAbsAction {

	
	
	
	public DeleteNodeAction() {
		
		putValue(SMALL_ICON, loadIcon("images/toolbar/exit.png"));
		putValue(NAME, "Delete Thing");
		putValue(SHORT_DESCRIPTION, "Delete node");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		Node n= (Node) WorkZone.getInstance().getjTree().getLastSelectedPathComponent();
		if(n==null) return;
		/*if(n instanceof Proizvod){
			WorkZone.getInstance().getRoot().getDeca().remove(n);
			return;
		}*/
		Node parent = n.getRoditelj();
		if(n!=null && parent!=null){
			
			if(n.getChildCount()>0){
				int option = Message.getMessage().brisanjeCvoraKojiImaPotomkeUpozorenje();
					if (option == JOptionPane.YES_OPTION) {
						for(Node p: n.getDeca()) {
						if(MyTab.getInstance().getParametre().contains(p)) {
						int index = MyTab.getInstance().getParametre().indexOf(p);
						MyTab.getInstance().obrisi(p);
						MyTab.getInstance().getParametre().remove(index);
						}}
		CommandManager.getCommandMannager().addCommand(new DeleteNodeCommand(parent,n));
					}}else{
						if(MyTab.getInstance().getParametre().contains(n)) {
							int index = MyTab.getInstance().getParametre().indexOf(n);
							MyTab.getInstance().obrisi(n);
							MyTab.getInstance().getParametre().remove(index);
							}
						CommandManager.getCommandMannager().addCommand(new DeleteNodeCommand(parent,n));}
					}
		
		parent.setMenjan(true);
		
    	SwingUtilities.updateComponentTreeUI(WorkZone.getInstance().getjTree());

}
}
