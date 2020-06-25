package view.bars;

import java.awt.Color;
import java.awt.FlowLayout;


import pomocni.MojTajmer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class StatusBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final JLabel timeLabel = new JLabel();	
	
	JPanel panel = null;
	
	public StatusBar() {
		
		createStatusBar();

	}
public void createStatusBar() {
		
		
		panel = new JPanel();
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setSize(450,50);
		//this.setBorder(BorderFactory.createEtchedBorder());
		this.setBackground(Color.white);
		
	
		 
		
		//-------------------(Definisanje strukture status Bara aplikacije)------------------------------------
		JPanel sbp01 = createStatusElement(140,12, Color.yellow, "Status:",	" <Ready>" );
						
		JPanel sbp02 = createStatusElement(120,12, Color.white, "Korisnik:"," <         Ko je prijavljen za rad>        " );
				
		JPanel sbp03 = createStatusElement(120,12, Color.orange, "Akcija:", "   <Naziv komandne akcije>    " );
		
		JPanel sbp04 = new MojTajmer();									//------(Formiraj tajmersku komponentu)
				
		//----------------(Formiranje kompozitnog panela)---------------------------
		JSplitPane jsp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sbp03, sbp01);
		JSplitPane jsp3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sbp02, jsp2);
		JSplitPane pane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sbp04, jsp3);
		//---------------------------------------------------------------------------
		this.add(pane1); 
	}
	
	private JPanel createStatusElement(int duz, int vis, Color boja, String labela, String polje) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setSize(duz,vis);
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBackground(boja);
		panel.add(new JLabel(labela));
		panel.add(new JLabel(polje));
		return panel;
	}
}