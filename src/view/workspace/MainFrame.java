package view.workspace;

import java.awt.BorderLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import message.Message;
import model.Parametar;
import view.bars.MyMenuBar;
import view.bars.MyToolBar;
import view.bars.StatusBar;

public class MainFrame extends JFrame implements ClipboardOwner {

	private static MainFrame instance=null;
	private Clipboard clipboard;
	private String tipPaste;
	private String korinisk;
	private ArrayList<Parametar> original= new ArrayList<Parametar>();
	private ArrayList<Integer>  indexi= new ArrayList<Integer>();
	public MainFrame() {
		
		 clipboard=new Clipboard("InstaFram clipboard"); 
		
		setSize(1200,900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("InstaFram");
		
		MyToolBar tb= MyToolBar.getInstance();
		tb.setFloatable(true);
		add(tb, BorderLayout.NORTH);
		
		setJMenuBar(MyMenuBar.getInstance());
		add(new StatusBar() ,BorderLayout.SOUTH);
		
		add(WorkZone.getInstance());
		
		
		this.addWindowListener(new WindowAdapter() {
	    	
		      public void windowOpened(WindowEvent e) {
		    	  
		    	  System.out.println("Aplikacija uspesno inicijalizovana!");		    	   
		      }

		      public void windowClosing(WindowEvent e) {
		        if (Message.getMessage().gasenjeInstaFrama()
		        		== JOptionPane.YES_OPTION) {

		        		
		        		
		        		System.exit(1);

		         
		        }else{
		        	setVisible(true);
		        }
		      }
		});
		setVisible(true);
	}
	public ArrayList<Parametar> getOriginal() {
		return original;
	}
	public void setOriginal(ArrayList<Parametar> original) {
		this.original = original;
	}
	public static MainFrame getInstance() {
		if(instance==null) instance= new MainFrame();
		return instance;
	}
	public void setIndexi(ArrayList<Integer> indexi) {
		this.indexi = indexi;
	}
	public ArrayList<Integer> getIndexi() {
		return indexi;
	}
	public void setKorinisk(String korinisk) {
		this.korinisk = korinisk;
	}
	public String getKorinisk() {
		return korinisk;
	}
	public Clipboard getClipboard() {
		return clipboard;
	}
	public String getTipPaste() {
		return tipPaste;
	}
	public void setTipPaste(String tipPaste) {
		this.tipPaste = tipPaste;
	}
	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub
		
	}
}
