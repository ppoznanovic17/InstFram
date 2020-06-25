package view.tree;

import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import model.Modul;
import model.Parametar;
import model.Proizvod;
import model.SoftverskaKompanija;



public class TreeCellRenderer extends DefaultTreeCellRenderer {

	// valjda je dobro
	
	
  public TreeCellRenderer() {
		
		// TODO Auto-generated constructor stub
	}
	
  public Component getTreeCellRendererComponent(
          JTree tree,
          Object value,
          boolean sel,
          boolean expanded,
          boolean leaf,
          int row,
          boolean hasFocus) {
              super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
              
              
              
          if (value instanceof SoftverskaKompanija) {  
        		  setIcon(new ImageIcon("images/kompanija.png"));
      }
          if (value instanceof Modul) {  
    		  setIcon(new ImageIcon("images/modul.png"));
      }
          if (value instanceof Parametar) {  
    		  setIcon(new ImageIcon("images/parametar.png"));
      }
          if (value instanceof Proizvod) {  
    		  setIcon(new ImageIcon("images/proizvod.png"));
      }
            	  
              return this;
                
}
}