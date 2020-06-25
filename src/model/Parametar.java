package model;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;

import view.parametar.Autor;
import view.parametar.DesktopShortcut;
import view.parametar.Logo;
import view.parametar.Naziv;
import view.parametar.PokreniNakonInstalacije;
import view.parametar.UsloviKoriscenja;

public class Parametar extends Node {
	
	private transient boolean menjan;
	private String path="";
	private String  tip;
	private String[] custom;
	private transient File f;
	private String sadrzaj;
	
	public Parametar(String name, Node parent,String t) {
		super(name, parent);
		menjan=false;
		custom= new String[3];
		custom[0]="";
		custom[1]="";
		custom[2]="";
		f= null;
		tip=t;
		sadrzaj=name;
		
	}
	
	public String getSadrzaj() {
		return sadrzaj;
	}
	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	@Override
	public void addNewNode(Node t) {
		return;
	}

	public String getPath() {
		return path;
	}
	public File getF() {
		return f;
	}
	

	public String getTip() {
		return tip;
	}

	
	public void setPath(String path) {
		this.path = path;
		f= new File(path);
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public void addLabelText(String s) {
		
		custom[0]=s;
	}
	public void addComponent(String s) {
		custom[1]=s;
	}
	public void addComponentName(String s) {
		custom[2]=s;
	}
	
	public String getLabelText() {
		if(custom[0]!=null)
		return this.custom[0];
		return "";
	}
	public String getComponent() {
		if(custom[1]!=null)
			return this.custom[1];
			return "";
	}
	public String getComponentText() {
		if(custom[2]!=null)
			return this.custom[2];
			return "";
	}
	
	public boolean isMenjan() {
		return menjan;
	}
	public static Parametar copy(Parametar p) {
		Parametar para = new Parametar(p.getName(), null, p.getTip());
		para.addLabelText(p.getLabelText());
		para.addComponent(p.getComponent());
		para.addComponentName(p.getComponentText());
		para.path=p.path;
		
		return para;
		
	}
}
