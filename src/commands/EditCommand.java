package commands;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Node;
import model.Parametar;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class EditCommand implements AbstractCommand{

	private String prosloIme;
	private Parametar p;
	private String novoIme;
	private String logoPath="";
	private String noviLogoPath="";
	private String labelaCust="";
	private String novaLabelaCust="";
	private String komponentaCust="";
	private String novaKomponentaCust="";
	
	public EditCommand(Parametar p) {
		this.p=p;
	}
	
	@Override
	public void UndoCommand() {
		if(p.getTip().equals("LOGO")) {
			p.setPath(logoPath);
		}else if(p.getTip().equals("CUSTOM")) {
			System.out.println(labelaCust);
			System.out.println(komponentaCust);
			p.addLabelText(labelaCust);
			p.addComponentName(komponentaCust);
		}else {
			System.out.println(labelaCust);
			System.out.println(komponentaCust);
			p.setSadrzaj(prosloIme);
		}
		SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
	}

	@Override
	public void RedoCommand() {
		
		String tip= p.getTip();
		if(tip.equals("LOGO")) {
			p.setPath(noviLogoPath);
		}else if(tip.equals("CUSTOM")) {
			System.out.println(novaLabelaCust);
			System.out.println(novaKomponentaCust);
			p.addLabelText(novaLabelaCust);
			p.addComponentName(novaKomponentaCust);
			SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
		}else {
			p.setSadrzaj(novoIme);
		}
		SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
	}

	public void setNoviLogoPath(String noviLogoPath) {
		this.noviLogoPath = noviLogoPath;
	}

	public void setNovaLabelaCust(String novaLabelaCust) {
		this.novaLabelaCust = novaLabelaCust;
	}

	public void setNovaKomponentaCust(String novaKomponentaCust) {
		this.novaKomponentaCust = novaKomponentaCust;
	}

	public void setProsloIme(String prosloIme) {
		this.prosloIme = prosloIme;
	}

	public void setP(Parametar p) {
		this.p = p;
	}

	public void setNovoIme(String novoIme) {
		this.novoIme = novoIme;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public void setLabelaCust(String labelaCust) {
		this.labelaCust = labelaCust;
	}

	public void setKomponentaCust(String komponentaCust) {
		this.komponentaCust = komponentaCust;
	}

}
