package model;

import java.util.ArrayList;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class TreeModel extends DefaultTreeModel {

	private SoftverskaKompanija kompanija;
	protected static ArrayList<Node> allNodes;
	
	
	public TreeModel(SoftverskaKompanija k) {
		super(k);
		allNodes= new ArrayList<>();
	}
	
	public void addOnRoot(Node n) {
		((Node) root).addNewNode(n);

	}
	
	
}
