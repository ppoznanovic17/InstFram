package view.parametar;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Parametar;

public class ParametarCreator {
	
	
	public static  JPanel getPanelParametar(Parametar p) {
		JPanel pnl= new JPanel();
    	pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		if(p.getTip().equals("AUTOR")){
			pnl= new Autor(p.getSadrzaj());
		}
		if(p.getTip().equals("CUSTOM")){
			
			if(p.getLabelText()!="") {
				pnl.add(new JLabel(p.getLabelText()));
			
			}
			JComponent panelcuga= new JPanel();
			panelcuga= razlaganjeCustomParametra(p);
			if(panelcuga!=null) {
			pnl.add(panelcuga);
			
			}
			}
		if(p.getTip().equals("DESKTOP_SHORTCUT")){
			pnl= new DesktopShortcut();
		}
		if(p.getTip().equals("LOGO")){
			pnl= new Logo(p.getPath());
		}
		if(p.getTip().equals("LOOK_AND_FEEL")){
			pnl= new LookAndFeel();
		}
		if(p.getTip().equals("NAZIV")){
			pnl= new Naziv(p.getSadrzaj());
		}
		if(p.getTip().equals("POKRETANJE_NAKON_INSTALACIJE")){
			pnl= new PokreniNakonInstalacije();
			}
		if(p.getTip().equals("USLOVI_KORISCENJA")){
			pnl= new UsloviKoriscenja(p.getSadrzaj());
		}
		
		return pnl;

	}
	
	private static JComponent razlaganjeCustomParametra(Parametar p) {
		if(p.getComponent()!=null) {
		if(p.getComponent().equals("button")) {
			JButton btn = new JButton();
			btn.setText(p.getComponentText());
			return btn;
		}
		if(p.getComponent().equals("checkbox")) {
			JCheckBox btn = new JCheckBox();
			btn.setText(p.getComponentText());
			return btn;
		}
		
		if(p.getComponent().equals("label")) {
			JLabel btn = new JLabel();
			btn.setText(p.getComponentText());
			return btn;
		}
		if(p.getComponent().equals("textarea")) {
			JTextArea btn = new JTextArea();
			btn.setText(p.getComponentText());
			return btn;
		}
		if(p.getComponent().equals("textfield")) {
			JTextField btn = new JTextField();
			btn.setText(p.getComponentText());
			return btn;
		}
		}
		return null;
		
	}
	
}
