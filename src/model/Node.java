package model;

import java.io.Serializable;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;

import javax.swing.tree.TreeNode;

import view.workspace.WorkZone;

public class Node extends Observable  implements TreeNode, Serializable {
	
	private String name;
	private ArrayList<Node> childrens;
	private Node parent;
	private  int brojDece=0;
	protected transient boolean menjan;
	private static int i=0;
	private int code;
	private transient boolean selektovan;
	public Node(String name, Node parent) {
		childrens= new ArrayList<>();
		menjan=false;
		this.name=name;
		this.parent=parent;
		code=i++;
		selektovan=false;
	
	}
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public boolean isMenjan() {
		return menjan;
	}
	
	public void obavestiObzervere() {
		setChanged();
		notifyObservers(this);
		this.selektovan=false;
	}
	
	public void setSelektovan(){
		this.selektovan=true;
		obavestiObzervere();
		
	}
	
	public void setParent(Node parent){
			this.parent=parent;
	}
		
	
	
	/*public void promeniNaziv(Node n){
		boolean sadrzi=false;
		if(n instanceof Modul) {
			if(this.getDeca().size()>0) {
				if(this.getDeca().contains(n)) n.setName(n.getName()+"*");
			}
		}
		if(n instanceof Parametar) {
			if(this.getDeca().size()>0) {
				for(Node node: this.getDeca()) {
					if(node instanceof Parametar) sadrzi=node.equals(n);
					if(node instanceof Modul) {
						for(Node p: node.getDeca()) {
							sadrzi=p.equals(n) ;
						}
					}
				}
			}
		}
	}*/
	
	public Node getLastAddedChildren(){
	
		return childrens.get(childrens.size()-1);
	}
	
	public void setMenjan(boolean menjan) {
		this.menjan = menjan;
	}
	@Override
	public Enumeration<Node> children() {
		return (Enumeration<Node>) this.childrens;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int x) {
		return childrens.get(x);
	}

	@Override
	public int getChildCount() {
		return childrens.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return childrens.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return this.parent;
	}

	@Override
	public boolean isLeaf() {
		if(this.getChildCount()==0) return true;
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Node){
			if(((Node) obj).getName()==this.name){
				if(this.getClass().equals(obj.getClass())) {
								return true;	}
			}
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		
		return name;
	}
	public Node getRoditelj(){
		return this.parent;
		}
	
	
	
	public void addNewNode(Node t){
		this.childrens.add(t);
		brojDece++;
		menjan=true;
		if(parent!=null) parent.menjan=true;
	}
	
	public void obrisiDete(Node n) {
		if(this.getDeca().contains(n)) this.childrens.remove(n);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Node> getDeca() {
		return childrens;
	}
	
	public void setDeca(ArrayList<Node> childrens) {
		this.childrens = childrens;
	}
	
}
