package controller;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import model.Node;
import model.Parametar;
import view.workspace.MyTab;
import view.workspace.WorkZone;



public class TreeSelection implements TreeSelectionListener {

	
	
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path= e.getPath();
		for(int i=0; i<path.getPathCount(); i++){ 
				Node d=(Node)path.getLastPathComponent();
				d.addObserver(WorkZone.getInstance());
				d.setSelektovan();
				/*if(d instanceof Parametar){
					MyTab.getInstance().Do(d);
				}*/	
				
			break;
		}		
	}

}
