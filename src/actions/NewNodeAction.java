package actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.security.auth.login.CredentialExpiredException;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import commands.CommandManager;
import commands.NewNodeCommand;
import message.Message;
import model.Modul;
import model.Node;
import model.Parametar;
import model.Proizvod;
import model.SoftverskaKompanija;

import view.workspace.MainFrame;
import view.workspace.WorkZone;
import commands.CommandManager;
public class NewNodeAction extends MyAbsAction {

	public NewNodeAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/toolbar/new.png"));
		putValue(NAME, "New node");
		putValue(SHORT_DESCRIPTION, "New node");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		Node n= (Node) WorkZone.getInstance().getjTree().getLastSelectedPathComponent();
		
		view();
		//SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
	}
	private void view(){
		
		Node n= (Node) WorkZone.getInstance().getjTree().getLastSelectedPathComponent();
		JFrame f= new JFrame("Kreiranje");
		
		if(n==null){
			Message.getMessage().nijedanResursNijeSelektovan();
			return;
		}
		
		
		/********************************************************************************/
		JButton btnProizvod= new JButton("Kreiraj Proizvod");
		btnProizvod.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	JFileChooser fc= new JFileChooser();
				String path="";
				Proizvod p;
			    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    if (fc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
			    	path=	fc.getSelectedFile().getAbsolutePath();
			    }	
			    JFileChooser fc2= new JFileChooser();
			    String path2="";
			    fc2.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    String ime="";
			    if (fc2.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
			    	path2=	fc2.getSelectedFile().getAbsolutePath();
			    	ime=fc2.getSelectedFile().getName();
			    }	
			    	String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite ime koje zelite da dodelite fajlu.");
			    	if(name!=null){
			    		File f= new File(path+File.separator+name+".ins");
			    		 p= new Proizvod(name, WorkZone.getInstance().getRoot(),path2,ime);
			    		 
			    		 p.setCuvan(true);
			    		p.setFile(path+File.separator+name+".ins");
			    		p.setParent(n);
			    		CommandManager.getCommandMannager().addCommand(new NewNodeCommand(n, p));
			    		
			    		FileOutputStream fos;
			    		ObjectOutputStream oos;
			    		
						try {
							fos = new FileOutputStream(f);
				    		oos= new ObjectOutputStream(fos);
				    		oos.writeObject(p);
				    		oos.close();
				    		fos.close();

						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    		
			    		
		    	
		    	
			    	}
			    	

		    f.setVisible(false);	
		    }
		    
	    }
		    );
		/********************************************************************************/
		
		JButton btnModul= new JButton("Kreiraj Modul");
		btnModul.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	f.setVisible(false);
		    	JFrame frame= new JFrame("Kreiranje Modula");
		    	frame.setLocationRelativeTo(null);
		    	frame.setMinimumSize(new Dimension(300, 300));
		    	JPanel panel= new JPanel();
		    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		    	
		    	
		    	
		    	JLabel name= new JLabel("Ime");
		    	JPanel namep= new JPanel();
		    	namep.add(name);
		    	JTextArea ta= new JTextArea();
		    	JButton btn= new JButton("Napravi");
		    	JPanel btnp= new JPanel();
		    	btnp.add(btn);
		    	panel.add(namep);
		    	panel.add(ta);
		    	panel.add(btnp);
		    	frame.add(panel);
		    	frame.setVisible(true);
		    	
		    	
		    	btn.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	if(ta.getText().equals("")) {
				    		Message.getMessage().prazanTextField();
				    		return;
				    	}
				    	frame.setVisible(false);
				    
				    	String ime=ta.getText();
				    	Modul k= new Modul(ime,n);
				    	
				    	CommandManager.getCommandMannager().addCommand(new NewNodeCommand(n, k));
				    	SwingUtilities.updateComponentTreeUI(WorkZone.getInstance().getjTree());
				    	
				    	
				    }
			    });
		    	
		    	
		    }
	    });
		/********************************************************************************/
		JButton btnParametar= new JButton("Kreiraj Parametar");
		btnParametar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	f.setVisible(false);
		    	JFrame frame= new JFrame("Kreiranje Parametra");
		    	frame.setLocationRelativeTo(null);
		    	frame.setMinimumSize(new Dimension(300, 300));
		    	JPanel panel= new JPanel();
		    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		    	
		    	JLabel name= new JLabel("Ime");
		    	JTextArea ta= new JTextArea();
		    	JButton btn= new JButton("Napravi");
		    	
		    	
		    	JPanel namep= new JPanel();
		    	namep.add(name);
		    	
		    	JComboBox<String> cmb= new JComboBox<>();
		    	cmb.addItem("AUTOR");
		    	cmb.addItem("CUSTOM");
		    	cmb.addItem("DESKTOP_SHORTCUT");
		    	cmb.addItem("LOGO");
		    	cmb.addItem("LOOK_AND_FEEL");
		    	cmb.addItem("NAZIV");
		    	cmb.addItem("POKRETANJE_NAKON_INSTALACIJE");
		    	cmb.addItem("USLOVI_KORISCENJA");
		    	
		    	JPanel btnp= new JPanel();
		    	btnp.add(btn);
		    	
		    	
		    	
		    	panel.add(namep);
		    	panel.add(ta);
		    	panel.add(cmb);
		    	panel.add(btnp);
		    	frame.add(panel);
		    	frame.setVisible(true);
		    	
		    	btn.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	Parametar k;
				    	frame.setVisible(false);
				    	if(cmb.getSelectedItem().toString().equals("CUSTOM")) {
				    		String ime=ta.getText();
				    		 k= new Parametar(ime,n,cmb.getSelectedItem().toString());
				    		String labela = JOptionPane.showInputDialog(frame, "Ako zelite da vas parametar sadrzi labelu, unesite teskt koji zelite da vasa\nlabela sadrzi. Ako zelite da vas parametar ne sadrzi\nlabelu, pritisnite 'cancle' ");
				    		if (labela == null) {
				    		   k.addLabelText("");
				    		}
					    	k.addLabelText(labela);
					    	
					    	JFrame frm= new JFrame();
					    	JPanel panel= new JPanel();
					    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
					    	JButton btn1= new JButton("Napravi");
					    	JComboBox<String> cmb1= new JComboBox<>();
					    	cmb1.addItem("button");
					    	cmb1.addItem("checkbox");
					    	cmb1.addItem("label");
					    	cmb1.addItem("textarea");
					    	cmb1.addItem("textfield");
					    	
					    	
					    	JPanel btnp= new JPanel();
					    	btnp.add(btn1);
					    	panel.add(cmb1);
					    	panel.add(btnp);
					    	frm.add(panel);
					    	frm.setSize(new Dimension(300,200));
					    	frm.setLocationRelativeTo(null);
					    	frm.setVisible(true);
					    	btn1.addActionListener(new ActionListener() {
							    @Override
							    public void actionPerformed(ActionEvent e) {
							    	frm.setVisible(false);
							    	k.addComponent(cmb1.getSelectedItem().toString());
							    	String txt = JOptionPane.showInputDialog(frm, "Unesite sadrzaj komponente koju ste izabrali.");
							    	if (txt == null) {
							    		   k.addComponentName("");
							    		}
							    	k.addComponentName(txt);
							    	System.out.println(k.getComponent());
							    	System.out.println(k.getComponentText());
							    }
						    });
					    	
					    	
				    	}else if(cmb.getSelectedItem().toString().equals("LOGO")){
				    		JFileChooser fc3= new JFileChooser();
						    String path3="";
						    fc3.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif"));
						    if (fc3.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
						    	path3=	fc3.getSelectedFile().getAbsolutePath();
						    }	
						    String ime=ta.getText();
					    	
					    	 k= new Parametar(ime,n,cmb.getSelectedItem().toString());
					    	 k.setPath(path3);
				    	}
				    	else {
				    		String ime=ta.getText();
					    	
					    	 k= new Parametar(ime,n,cmb.getSelectedItem().toString());
				    	}
				    	
				    	frame.setVisible(false);
				    	
				    	if(n instanceof Proizvod) {
				    		((Proizvod) n).dodajUSve(k);
				    	}else if(n instanceof Modul) {
				    		((Proizvod) n.getRoditelj()).dodajUSve(k);
				    	}
				    	
				    	
				    	CommandManager.getCommandMannager().addCommand(new NewNodeCommand(n, k));
				    	SwingUtilities.updateComponentTreeUI(WorkZone.getInstance().getjTree());
				    	
				    	
				    }
			    });
		    	
		    	
		    }
	    });
		/********************************************************************************/
		
		
		

		f.setLocationRelativeTo(null);
		
		f.setMinimumSize(new Dimension(300, 300));
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(200, 200));
		p.setLayout(new BorderLayout());
		
		if(n instanceof SoftverskaKompanija){
			p.add(btnProizvod);
		}
		if(n instanceof Proizvod){
			p.add(btnModul,BorderLayout.NORTH);
			p.add(btnParametar,BorderLayout.SOUTH);
			p.add(new JLabel("             Kreiraj modul( \u2191 ) ili parametar( \u2193 )"), BorderLayout.CENTER);
		}
		if(n instanceof Modul){
			p.add(btnParametar,BorderLayout.CENTER);
		}
		if(n instanceof Parametar){
			return;
		}
		
		if(n instanceof SoftverskaKompanija){}else{
		
		
		n.setMenjan(true);
		}
		
		//n.addObserver(WorkZone.getInstance());
		//n.notyObservers();
		p.setMinimumSize(new Dimension(150, 200));
		
		f.add(p);
		f.setVisible(true);
		
	
	}
	
	}
