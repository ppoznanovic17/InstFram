package view.tree;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import controller.TreeSelection;
import model.Parametar;
import model.SoftverskaKompanija;
import view.workspace.MyTab;
import view.workspace.WorkZone;


public class MyJTree extends JTree  {

	public MyJTree() {
		
		addTreeSelectionListener(new TreeSelection());
		setCellRenderer(new TreeCellRenderer());
		setCellEditor(new TreeCellEditor(this, new DefaultTreeCellRenderer()));
		setEditable(true);
		
		this.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	        	
	            if (e.getClickCount() == 2) {
	            	TreePath tp= WorkZone.getInstance().getjTree().getPathForLocation(e.getX(), e.getY());
	            	try{
	            	if(tp.getLastPathComponent() instanceof Parametar) {
	            	Parametar p= (Parametar) tp.getLastPathComponent();
	            	MyTab.getInstance().Do(p);
	            	}else {
	            		return;
	            	}}catch (Exception e1) {
						return;
					}
	            }
	        }
	    });
	}

	
	

		
	}
	