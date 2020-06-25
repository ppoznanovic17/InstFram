package view.bars;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.BevelBorder;

import actions.AboutAction;
import actions.ActionManager;




public class MyMenuBar extends JMenuBar {

	private static MyMenuBar instance= new MyMenuBar();
	
	private MyMenuBar(){
	
		
		
		JMenu file= new JMenu("File");
		JMenu view= new JMenu("View");
		JMenu tools= new JMenu("Tools");
		JMenu par= new JMenu("Parametrizacija");
		JMenu pomoc= new JMenu("Help");
		JMenu about= new JMenu("About");
		
		
		
		
		// elements for **JMenu Export**
		JMenuItem pdf = new JMenuItem("ExpToPdf");
		JMenuItem excel = new JMenuItem("ExpToExcel");
		JMenuItem word = new JMenuItem("ExpToWord");
		
		// import export 
		JMenu importx = new JMenu("Export");
		JMenu export= new JMenu("Import");
		
		//adding elements to file
		
		file.add(ActionManager.getActionManager().getOpen());
		file.add(ActionManager.getActionManager().getClose());
		file.addSeparator();
		file.add(ActionManager.getActionManager().getSave());
		file.add(ActionManager.getActionManager().getSaveAs());
		file.add(ActionManager.getActionManager().getDelete());
		file.addSeparator();
		file.add(export);
		file.add(importx);
		file.addSeparator();
		about.add(ActionManager.getActionManager().getAboutaction());
		
		//adding elements to Export
		export.add(pdf);
		export.add(excel);
		export.add(word);
		
		//ading elements to MyMenuBar
		this.add(file);
		this.add(view);
		this.add(tools);
		this.add(par);
		this.add(pomoc);
		this.add(about);
		
		
		
		
	
	}
	
	public static MyMenuBar getInstance(){
		if(instance== null) instance= new MyMenuBar();
		
		return instance;
		}
	}