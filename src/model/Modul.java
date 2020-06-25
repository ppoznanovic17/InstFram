package model;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import cut_copy_paste.NodeSelection;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class Modul extends Node  {

	private transient boolean menjan;
	//private File file;
	private String path="";
	public Modul(String name, Node parent) {
		super(name, parent);
		//file=f;
		menjan=false;
		
	}
		
	
	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	@Override
	public void addNewNode(Node t) {
		if(t instanceof Parametar)
		super.addNewNode(t);
		else{
			
		}
		menjan=true;
		
	}
	
	public void paste() {
		Transferable clipboardContent = MainFrame.getInstance().getClipboard().getContents(MainFrame.getInstance());
		
		if((clipboardContent !=null)) {
			try {
			
				ArrayList<Parametar> tempElements = (ArrayList<Parametar>)
						clipboardContent.getTransferData(NodeSelection.dataFlavor);
				for(Parametar p: tempElements) {
					this.getDeca().add(p);
					p.setParent(this);
					
				}
				
			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(WorkZone.getInstance().getjTree());
		}
	}
}
