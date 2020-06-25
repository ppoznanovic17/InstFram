package commands;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import cut_copy_paste.NodeSelection;
import model.Node;
import model.Parametar;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class PasteCommand implements AbstractCommand {

	private Node n;
	private ArrayList<Parametar> parametri; 
	private String tip;
	private int a=0;
	public PasteCommand(Node n,String t) {
		this.n=n;
		tip=t;
	}
	
	@Override
	public void UndoCommand() {
		for(int i=parametri.size()-1;i>parametri.size()-1-a;i--) {
			n.getDeca().remove(i);
		}
		SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
	
	
	}

	@Override
	public void RedoCommand() {
		paste(n);
		
		SwingUtilities.updateComponentTreeUI(WorkZone.getInstance().getjTree());
	}

	public void paste(Node n) {
		Transferable clipboardContent = MainFrame.getInstance().getClipboard().getContents(MainFrame.getInstance());
		
		if((clipboardContent !=null)) {
			try {
				ArrayList<Parametar> para= new ArrayList<Parametar>();
				ArrayList<Parametar> tempElements = (ArrayList<Parametar>)
						clipboardContent.getTransferData(NodeSelection.dataFlavor);
				a=tempElements.size();
				parametri= tempElements;
				for(Parametar p: tempElements) {
					Parametar par= new Parametar(p.getName(), n, p.getTip());
					n.getDeca().add(par);
					p.setParent(n);
					
				}
				
				
			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
