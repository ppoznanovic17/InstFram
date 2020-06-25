package view.bars;

import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import actions.ActionManager;

public class SecondToolBar extends JToolBar {

	private static SecondToolBar instance= null;
	
	private SecondToolBar(){
		
		super(SwingConstants.HORIZONTAL);
		
		add(ActionManager.getActionManager().getCloseTab());
		addSeparator();
		add(ActionManager.getActionManager().getClsAllTabs());
		
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setVisible(true);
		setFloatable(true);
	}
	
	public static SecondToolBar getInstance() {
		if(instance == null) instance= new SecondToolBar();
		instance.setVisible(true);
		return instance;
	}
	
}
