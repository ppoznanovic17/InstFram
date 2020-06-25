package view.workspace;

import java.awt.Dimension;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import actions.ActionManager;
import model.Node;
import model.Parametar;
import view.parametar.ParametarCreator;


public class MyTab extends JTabbedPane {

	private static MyTab instance=null;
	

	private JTabbedPane tabbedPane;
	private static ArrayList<Parametar> parametri;
	
	
	private MyTab() {
		parametri=new ArrayList<>();
		
		tabbedPane=new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(800, 330));
		tabbedPane.setVisible(true);
		
		
		
	}
	
	public void Do(Object arg) {
		if(arg instanceof Parametar){
		Parametar t= (Parametar) arg;
		boolean have=false;
		for(int i=0; i<tabbedPane.getTabCount();i++){
			if(tabbedPane.getTitleAt(i).equals(t.getName())){
				tabbedPane.setSelectedIndex(i);
				have=true;
				break;
			}
		}
		if(have==false){
		tabbedPane.addTab(t.getName(),ParametarCreator.getPanelParametar(t));
		tabbedPane.setFocusable(true);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
		parametri.add(t);
		}}
	}
		
	public void obrisi(Node n) {
		int a=-1;
		if(parametri.contains(n)) {
			a= parametri.indexOf(n);
			tabbedPane.remove(a);
		}
		
	}
	
	public static MyTab getInstance() {
		if(instance==null) instance= new MyTab();
		return instance;
	}

	

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	public static ArrayList<Parametar> getParametre() {
		return parametri;
	}
	
	
}