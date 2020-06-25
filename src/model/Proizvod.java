package model;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import cut_copy_paste.NodeSelection;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class Proizvod extends Node {

	//private String file;
	private transient File f;
	protected transient boolean cuvan;
	protected transient boolean menjan;
	private String instaliraSe;
	private String instaliraSeIme;
	private  ArrayList<Node> svi;
	public Proizvod(String name, Node parent,String s,String ime) {
		super(name, parent);
		cuvan=false;
		instaliraSe=s;
		instaliraSeIme= ime;
		svi= new ArrayList<Node>();
	}
	
	
	
	public ArrayList<Node> getSvi() {
		return svi;
	}
	public File getFile() {
		return f;
	}
	public  void dodajUSve(Node n) {
		svi.add(n);
	}

	public void setFile(String file) {
		f= new File(file);
	}


	public boolean isCuvan() {
		return cuvan;
	}
	
	public void setCuvan(boolean cuvan) {
		this.cuvan = cuvan;
	}
	@Override
	public void addNewNode(Node t) {
		if(t instanceof Modul  || t instanceof Parametar)
		super.addNewNode(t);
		menjan=true;
		
		
	}
	public boolean isMenjan() {
		return menjan;
	}


	public String getInstaliraSe() {
		return instaliraSe;
	}


	public void setInstaliraSe(String instaliraSe) {
		this.instaliraSe = instaliraSe;
	}
	public String getInstaliraSeIme() {
		return instaliraSeIme;
	}
	
	/*public void paste() {
		Transferable clipboardContent = MainFrame.getInstance().getClipboard().getContents(MainFrame.getInstance());
		
		if((clipboardContent !=null)) {
			try {
			
				ArrayList<Parametar> tempElements = (ArrayList<Parametar>)
						clipboardContent.getTransferData(NodeSelection.dataFlavor);
				for(Parametar p: tempElements) {
					p.setName(p.getName()+"*");
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
	}*/
}
