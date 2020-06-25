package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class MyAbsAction extends AbstractAction {

	public Icon loadIcon(String path){
		Icon icon= new ImageIcon(path);
		if(icon==null) System.out.println("RESOURSE: " + path + "doesnt exist");
		return icon;
		
	}

	

	
}
