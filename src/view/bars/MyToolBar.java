package view.bars;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import actions.ActionManager;

public class MyToolBar extends JToolBar {
	
	private static MyToolBar instance= null;
	
	
	
	private MyToolBar() {
		
		super(SwingConstants.HORIZONTAL);
		
		add(ActionManager.getActionManager().getNewNode());
		add(ActionManager.getActionManager().getDelete());
		addSeparator();
		addSeparator();
		add(ActionManager.getActionManager().getOpen());
		
		addSeparator();
		addSeparator();
		add(ActionManager.getActionManager().getSave());
		add(ActionManager.getActionManager().getSaveAs());
		addSeparator();
		addSeparator();
		add(ActionManager.getActionManager().getExport());
		addSeparator();
		addSeparator();
		addSeparator();
		add(ActionManager.getActionManager().getCopy());
		add(ActionManager.getActionManager().getCut());
		add(ActionManager.getActionManager().getPaste());
		addSeparator();
		addSeparator();
		add(ActionManager.getActionManager().getUndo());
		add(ActionManager.getActionManager().getRedo());
		addSeparator();
		addSeparator();
		add(ActionManager.getActionManager().getEdit());
		addSeparator();
		addSeparator();
		addSeparator();
		addSeparator();
		addSeparator();
		addSeparator();
		add(ActionManager.getActionManager().getPrw());
		// u can move toolbar when u open app
		setFloatable(true);
		
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		setVisible(true);
		
	}
	
	public static MyToolBar getInstance() {
		instance= new MyToolBar();
		instance.setVisible(true);
		return instance;
	}

}

